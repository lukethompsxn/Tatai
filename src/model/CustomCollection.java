package model;

import java.util.HashMap;

public class CustomCollection extends NumberCollection {

    private HashMap<Integer, String> _questionsMap;
    private HashMap<Integer, String> _questionAnswersMap;
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
        System.out.println(_questionsMap);
        System.out.println(_questionAnswersMap);
        System.out.println("question#: " + questionNumber + " question: " + question + " answer: " + getMaoriName(answer));
    }

    public HashMap<Integer, String> getCurrentQuestionMap() {
        return _questionsMap;
    }

    public HashMap<Integer, String> getCurrentAnswerMap() {
        return _questionAnswersMap;
    }
}
