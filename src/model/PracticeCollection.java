package model;

import javax.imageio.stream.IIOByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PracticeCollection extends NumberCollection {
    //JOEL WHOLE CLASS

    private static PracticeCollection _practiceCollection = PracticeCollection.instance();
    private HashMap<Integer, String> _questionsMap;
    private HashMap<Integer, String> _questionAnswersMap;

    //Singleton Constructor
    public static PracticeCollection instance() {
        if (_practiceCollection == null) {
            _practiceCollection = new PracticeCollection();
        }
        return _practiceCollection;
    }

    //Private Constructor
    private PracticeCollection() {
        //anything that needs to constructed
        initialize();
    }

    public void setCollection(int level) {

        _questionsMap = new HashMap<>();
        _questionAnswersMap = new HashMap<>();
        ArrayList<Integer> randNumbers = new ArrayList();

        Random randomGenerator = new Random();

        for (int i = 0; i < 10; i++) {
            int randomInt = randomGenerator.nextInt(level) + 1;
            while (randNumbers.contains(randomInt)) {
                randomInt = randomGenerator.nextInt(level) + 1;
            }
            randNumbers.add(randomInt);
            String maoriName = getMaoriName(randomInt);
            _questionsMap.put(i, "" + randomInt);
            _questionAnswersMap.put(i, maoriName);
        }
    }

    public HashMap<Integer, String> getCurrentQuestionMap() {
        return _questionsMap;
    }

    public HashMap<Integer, String> getCurrentAnswerMap() {
        return _questionAnswersMap;
    }

}
