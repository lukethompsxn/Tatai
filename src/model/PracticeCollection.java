package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Joel Clarke, Luke Thompson
 */
public class PracticeCollection extends NumberCollection {

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

    /**
     * This populates two maps of type Integer then String. The first is the _questionsMap where a random number is
     * added as a question based on the input level. The second map is _questionAnswerMap which is populated with with
     * the matching keys question translated to Maori.
     * @param level level of difficulty for practice
     */
    public void setCollection(int level) {

        _questionsMap = new HashMap<>();
        _questionAnswersMap = new HashMap<>();
        ArrayList<Integer> randNumbers = new ArrayList();

        Random randomGenerator = new Random();

        // Iterates 10 times
        for (int i = 0; i < 10; i++) {

            // Gets random int
            int randomInt = randomGenerator.nextInt(level) + 1;

            // Checks if the random int has already been been added to the map, if so a new int is generated
            while (randNumbers.contains(randomInt)) {
                randomInt = randomGenerator.nextInt(level) + 1;
            }
            randNumbers.add(randomInt);

            // Gets Maori translation for the randomly generated number
            String maoriName = getMaoriName(randomInt);

            // Questions and corresponding answer is added to the maps
            _questionsMap.put(i, "" + randomInt);
            _questionAnswersMap.put(i, maoriName);
        }
    }

    /**
     * This method returns a map containing all the current questions
     * @return hash map of
     */
    public HashMap<Integer, String> getCurrentQuestionMap() {
        return _questionsMap;
    }

    /**
     * This method returns a map containing all the current answers corresponding to the current map of answers.
     * @return
     */
    public HashMap<Integer, String> getCurrentAnswerMap() {
        return _questionAnswersMap;
    }

}
