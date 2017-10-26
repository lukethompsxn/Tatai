package model;

import javafx.scene.control.Alert;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class CustomCollection extends NumberCollection {
    private static CustomCollection _customCollection;
    private HashMap<Integer, String> _questionsMap;
    private HashMap<Integer, String> _questionAnswersMap;
    private HashMap<String, HashMap<Integer, String>> _storedQuestions = new HashMap<>();
    private HashMap<String, HashMap<Integer, String>> _storedAnswers = new HashMap<>();
    private String _currentMap;
    private ArrayList<String> _currentItems = new ArrayList<>();

    //Singleton Constructor
    public static CustomCollection instance() {
        if (_customCollection == null) {
            _customCollection = new CustomCollection();
        }
        return _customCollection;
    }

    //Private Constructor
    private CustomCollection() {
        //anything that needs to constructed
        initialize();
    }

    /**
     * This method takes a question number, question, and answer and arguments. This adds the questions and the
     * corresponding number to a hashmap, likewise the same thing is done with the answer. This method is called
     * from custom add view which passes arguments everytime the user adds a question. The question and answer is
     * also added to a list in order to save them to file in a later method call.
     * @param questionNumber the number of the question being added
     * @param question the question being added
     * @param answer the answer being added
     */
    public void addCustomQuestion(int questionNumber, String question, int answer) {
        if (questionNumber == 0) {
            _questionsMap = new HashMap<>();
            _questionAnswersMap = new HashMap<>();
        }

        _questionsMap.put(questionNumber, question);
        _questionAnswersMap.put(questionNumber, getMaoriName(answer));
        _currentItems.add(question);
        _currentItems.add(Integer.toString(answer));
    }

    /**
     * This method is called from main before the program is launched. The purpose of this method is to read from
     * the stored custom questions file, importing the lists which are stored in the file. This will populate the
     * hashmaps inside the custom view controller so these lists can be displayed and played by the user.
     */
    public void importCustomLists() {
        _questionsMap = new HashMap<>();
        _questionAnswersMap = new HashMap<>();

        boolean toggle = true;
        boolean error = false;
        int questionNum = 0;
        int answerNum = 0;
        String temp;
        String name = "";

        File file = new File("data" + File.separator + "CustomQuestions.txt");
        Scanner scanner = null;
        if (file.exists()) {
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scanner.hasNextLine()) {
                temp = scanner.nextLine();

                if (temp.isEmpty()) {
                    // do nothing
                } else if (temp.startsWith("@")) {
                    name = temp.replaceFirst("@", "");
                    _questionsMap = new HashMap<>();
                    _questionAnswersMap = new HashMap<>();
                    questionNum = 0;
                    answerNum = 0;
                } else if (toggle) {
                    _questionsMap.put(questionNum, temp);
                    questionNum++;
                    toggle = !toggle;
                    error = true;
                } else {
                    try {
                        temp = temp.replaceAll("\\s+", "");
                        _questionAnswersMap.put(answerNum, getMaoriName(Integer.parseInt(temp)));
                        error = false;
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Please fix the Custom Question File");
                        alert.showAndWait();
                    }

                    answerNum++;
                    toggle = !toggle;
                }
                if (!temp.isEmpty()) {
                    _storedQuestions.put(name, _questionsMap);
                    _storedAnswers.put(name, _questionAnswersMap);
                }
            }
        }

    }

    //This method gets the currently selected question map
    public HashMap<Integer, String> getCurrentQuestionMap() {
        return _storedQuestions.get(_currentMap);
    }

    //This method gets the currently selected answer map
    public HashMap<Integer, String> getCurrentAnswerMap() {
        return _storedAnswers.get(_currentMap);
    }

    //This method gets the collection of currently stored lists
    public Set<String> getStoredNames() {
        return _storedQuestions.keySet();
    }

    //This method sets the _currentMap field to be the item currently selected item in the list view from custom view
    public void setCurrentList(String name) {
        _currentMap = name;
    }

    /**
     * This method is used to write data to file. It is used to both write the question and the answer to the file,
     * and also the list name to file. This is a helper method and is called from various places inside CustomCollection.
     * If this method is being used to write the list name to file, the second argument is passed as -1, which results
     * in it not being printed. When writing the question and answer to the question is passed as f1, and the anwer
     * is passed as f2. These will both be written to the file.
     * @param f1
     * @param f2
     * @param append
     */
    private void writeToFile(String f1, int f2, boolean append) {
        try {
            FileWriter fileWriter = new FileWriter("data" + File.separator + "CustomQuestions.txt", append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter toFile = new PrintWriter(bufferedWriter);
            toFile.println(f1);

            if (f2 != -1) {
                toFile.println(f2);
            }

            toFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to delete a list from both the stored hashmaps, and from the custom question file. The name
     * of the list which is to be deleted is passed as an arugment. This method makes a copy of the custom questions
     * file, clears the original, then copies from the duplicate until reaching the name which is being deleted. It
     * then consumes the list, before copying the rest of the file. Once this is done, the list is removed from the
     * stored maps.
     * from the
     * @param name name of list being deleted
     */
    public void deleteCustomList(String name) {
        String temp;

        try {
            Files.copy(new File("data" + File.separator + "CustomQuestions.txt").toPath(), new File("data" + File.separator + ".CustomQuestions.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        File old = new File("data" + File.separator + "CustomQuestions.txt");
        old.delete();

        File file = new File("data" + File.separator + ".CustomQuestions.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scanner.hasNextLine()) {
            temp = scanner.nextLine();
            if (!temp.equals("@" + name)) {
                writeToFile(temp, -1, true);
            }
            else {
                for (int i = 0; i < 2 * (_storedQuestions.get(name).size()); i++) {
                    temp = scanner.nextLine();
                }
            }
        }
        file.delete();
        _storedQuestions.remove(name);
        _storedAnswers.remove(name);

    }

    /**
     * This method is called after the user enters a for the list which they created. It first writes the name of the
     * list to file, followed by the questions and answers which have been stored in a list as they were added.
     * @param name the name of custom list being added
     */
    public void configureAdd(String name) {
        _storedQuestions.put(name, _questionsMap);
        _storedAnswers.put(name, _questionAnswersMap);
        writeToFile("@" + name, -1, true);
        for (String s : _currentItems) {
            writeToFile(s, -1, true);
        }
        _currentItems = new ArrayList<>();
    }
}
