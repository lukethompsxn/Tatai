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

    public void importCustomLists() {
        _questionsMap = new HashMap<>();
        _questionAnswersMap = new HashMap<>();

        boolean toggle = true;
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
                } else {
                    try {
                        temp = temp.replaceAll("\\s+", "");
                        _questionAnswersMap.put(answerNum, getMaoriName(Integer.parseInt(temp)));
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

    public HashMap<Integer, String> getCurrentQuestionMap() {
        return _storedQuestions.get(_currentMap);
    }

    public HashMap<Integer, String> getCurrentAnswerMap() {
        return _storedAnswers.get(_currentMap);
    }

    public Set<String> getStoredNames() {
        return _storedQuestions.keySet();
    }

    public void setCurrentList(String name) {
        _currentMap = name;
    }

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

    public void deleteCustomList(String name) {
        String temp = "";

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
