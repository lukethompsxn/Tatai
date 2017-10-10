package model;

import java.util.*;

public class MathsCollection {

    private HashMap<Integer, String> _questionsMap;
    private LinkedHashMap<Integer, String> _questionAnswersMap;
    private NumberCollection numberCollection = NumberCollection.instance();


    public void addition(int level, int questionNumber) {
        _questionsMap = new HashMap<>();
        ArrayList<Integer> answers = new ArrayList();

        Random randomGenerator = new Random();

        for (int i = 0; i < questionNumber; i++) {

            int randomIntZ = randomGenerator.nextInt(level) + 1;
            int randomIntY = randomGenerator.nextInt(randomIntZ) + 1;
            int intX = randomIntZ - randomIntY;

            while (answers.contains(randomIntZ)) {
                randomIntZ = randomGenerator.nextInt(level) + 1;
                randomIntY = randomGenerator.nextInt(randomIntZ) + 1;
                intX = randomIntZ - randomIntY;
            }

            answers.add(randomIntZ);
            String maoriName = numberCollection.getMaoriName(randomIntZ);

            _questionsMap.put(i, intX + " + " + randomIntY + " =");
            _questionAnswersMap.put(i, maoriName);
        }
    }
}
