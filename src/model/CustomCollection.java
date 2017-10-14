package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CustomCollection extends NumberCollection {

    private HashMap<Integer, String> _questionsMap;
    private HashMap<Integer, String> _questionAnswersMap;
    private HashMap<String, HashMap<Integer, String>> _storedQuestions = new HashMap<>();
    private HashMap<String, HashMap<Integer, String>> _storedAnswers = new HashMap<>();
    private static CustomCollection _customCollection;

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

        System.out.println(question + " " + answer);
        writeToFile(question, answer);
    }

    public void importCustomLists() {

    }

    public HashMap<Integer, String> getCurrentQuestionMap() {
        return _questionsMap;
    }

    public HashMap<Integer, String> getCurrentAnswerMap() {
        return _questionAnswersMap;
    }

    public void addToStoredMap(String name) {
        _storedQuestions.put(name, _questionsMap);
        _storedAnswers.put(name, _questionAnswersMap);
        writeToFile("*" + name, -1);
    }

    public Set<String> getStoredNames() {
        return _storedQuestions.keySet();
    }

    private void writeToFile(String f1, int f2) {
        try {
            FileWriter fileWriter = new FileWriter("data" + File.separator + ".CustomQuestions.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter toFile = new PrintWriter(bufferedWriter);
            System.out.println(f1 + " " + f2);
            toFile.println(f1);

            if (f2 != -1) {
                toFile.println(f2);
            }

            toFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
