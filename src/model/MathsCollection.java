package model;

import java.util.*;

public class MathsCollection {

    private HashMap<Integer, String> _questionsMap;
    private LinkedHashMap<Integer, String> _questionAnswersMap;
    private NumberCollection numberCollection = NumberCollection.instance();


    public void addition(int level, int questionNumber, String type) {
        _questionsMap = new HashMap<>();
        ArrayList<Integer> answers = new ArrayList();

        Random randomGenerator = new Random();

        for (int i = 0; i < questionNumber; i++) {

            int intZ;
            int intY;
            int intX;

            intZ = randomGenerator.nextInt(level) + 1;

            if (type.equals("addition")) {
                intY = randomGenerator.nextInt(intZ) + 1;
                intX = intZ - intY;

                while (answers.contains(intZ)) {
                    intZ = randomGenerator.nextInt(level) + 1;
                    intY = randomGenerator.nextInt(intZ) + 1;
                    intX = intZ - intY;
                }
            }
            else if (type.equals("subtraction")){
                intX = randomGenerator.nextInt(level) + intZ + 1;
                intY = intX - intZ;

                while (answers.contains(intZ)) {
                    intZ = randomGenerator.nextInt(level) + intZ + 1;
                    intX = randomGenerator.nextInt(level) + 1;
                    intY = intX - intZ;
                }
            } else {
                intX = randomGenerator.nextInt(level) + 1;
                intY = randomGenerator.nextInt(level) + 1;
                intZ = intX * intY;
            }


            answers.add(intZ);
            String maoriName = numberCollection.getMaoriName(intZ);

            if (type.equals("addition")) {
                _questionsMap.put(i, intX + " + " + intY + " =");
            }
            else if (type.equals("subtraction")){
                _questionsMap.put(i, intX + " - " + intY + " =");
            }
            else {
                _questionsMap.put(i, intX + " x " + intY + " =");
            }
            System.out.println(_questionsMap.get(i) + " " + maoriName);
            _questionAnswersMap.put(i, maoriName);
        }
    }


}
