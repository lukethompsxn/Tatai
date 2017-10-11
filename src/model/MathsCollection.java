package model;

import java.util.*;

public class MathsCollection {

    private HashMap<Integer, String> _questionsMap;
    private LinkedHashMap<Integer, String> _questionAnswersMap;
    private NumberCollection numberCollection = NumberCollection.instance();


    public void arithmetic(int level, int questionNumber, NumberCollection.Mode mode) {
        _questionsMap = new HashMap<>();
        ArrayList<Integer> answers = new ArrayList();
        List<Integer> divisors;

        Random randomGenerator = new Random();

        for (int i = 0; i < questionNumber; i++) {

            int intZ;
            int intY;
            int intX;

            intZ = randomGenerator.nextInt(level) + 1;

            if (mode == NumberCollection.Mode.MATH_ADD) {
                intY = randomGenerator.nextInt(intZ) + 1;
                intX = intZ - intY;

                while (answers.contains(intZ)) {
                    intZ = randomGenerator.nextInt(level) + 1;
                    intY = randomGenerator.nextInt(intZ) + 1;
                    intX = intZ - intY;
                }
                _questionsMap.put(i, intX + " + " + intY + " =");

            } else if (mode == NumberCollection.Mode.MATH_SUB){
                intX = randomGenerator.nextInt(level) + intZ + 1;
                intY = intX - intZ;

                while (answers.contains(intZ)) {
                    intZ = randomGenerator.nextInt(level) + intZ + 1;
                    intX = randomGenerator.nextInt(level) + 1;
                    intY = intX - intZ;
                }
                _questionsMap.put(i, intX + " - " + intY + " =");
            } else if (mode == NumberCollection.Mode.MATH_MULT){
                intX = randomGenerator.nextInt(level) + 1;
                intY = randomGenerator.nextInt(level) + 1;
                intZ = intX * intY;
                _questionsMap.put(i, intX + " x " + intY + " =");
            } else {
                divisors = new ArrayList<>();
                Random randomElement = new Random();
                int limit = intZ;
                System.out.println(limit);

                for (int j = 1; j <= limit; j++) {
                    if (intZ % j == 0) {
                        divisors.add(j);
                    }
                }

                intX = intZ;
                if (divisors.size() == 2) {
                    intY = divisors.get(randomElement.nextInt(divisors.size()));
                } else {
                    divisors.remove(new Integer(intX)); divisors.remove(new Integer(1));
                    intY = divisors.get(randomElement.nextInt(divisors.size()));
                }
                intZ = intX / intY;
                _questionsMap.put(i, intX + " / " + intY + " =");
            }


            answers.add(intZ);
            String maoriName = numberCollection.getMaoriName(intZ);

            System.out.println(_questionsMap.get(i) + " " + maoriName);
            _questionAnswersMap.put(i, maoriName);
        }
    }


}
