package model;

import java.util.*;

public class MathsCollection extends NumberCollection {

    private HashMap<Integer, String> _questionsMap;
    private HashMap<Integer, String> _questionAnswersMap;
    private static MathsCollection _mathsCollection;
    private  int _randomQuestionCount;

    //Singleton Constructor
    public static MathsCollection instance() {
        if (_mathsCollection == null) {
            _mathsCollection = new MathsCollection();
        }
        return _mathsCollection;
    }

    //Private Constructor
    private MathsCollection() {
        //anything that needs to constructed
        initialize();
    }

    public void arithmetic(int level, int questionNumber, ModeDirector.Mode mode, boolean random) {

        if (!random) {
            _questionsMap = new HashMap<>();
            _questionAnswersMap = new HashMap<>();
        }
        //_questionsMap = new HashMap<>();
        //_questionAnswersMap = new HashMap<>();
        ArrayList<Integer> answers = new ArrayList<>();
        List<Integer> divisors;

        Random randomGenerator = new Random();

        for (int i = 0; i < questionNumber; i++) {

            int intZ;
            int intY;
            int intX;

            intZ = randomGenerator.nextInt(level) + 1;

            if (mode == ModeDirector.Mode.MATH_ADD) {
                intY = randomGenerator.nextInt(intZ) + 1;
                intX = intZ - intY;

                while (answers.contains(intZ)) {
                    intZ = randomGenerator.nextInt(level) + 1;
                    intY = randomGenerator.nextInt(intZ) + 1;
                    intX = intZ - intY;
                }
                //_questionsMap.put(i, intX + " + " + intY);
                if (random) {
                    _questionsMap.put(_randomQuestionCount, intX + " + " + intY);
                } else {
                    _questionsMap.put(i, intX + " + " + intY);
                }
            } else if (mode == ModeDirector.Mode.MATH_SUB){
                intX = randomGenerator.nextInt(level) + 10;
                intY = randomGenerator.nextInt(level - 1) + 1;

                while (((intX - intY) < 1) || ((intX - intY) > 100) || (intX < intY)) {
                    intX = randomGenerator.nextInt(level) + 10;
                    intY = randomGenerator.nextInt(level - 1) + 1;
                }

                intZ = intX - intY;
                //_questionsMap.put(i, intX + " - " + intY);
                if (random) {
                    _questionsMap.put(_randomQuestionCount, intX + " - " + intY);
                } else {
                    _questionsMap.put(i, intX + " - " + intY);
                }
            } else if (mode == ModeDirector.Mode.MATH_MULT){
                intX = randomGenerator.nextInt(level) + 1;
                intY = randomGenerator.nextInt(level) + 1;

                while (((intX == intY) && (intX == 10)) || (intX == 1) || (intY == 1)) {
                    intX = randomGenerator.nextInt(level) + 1;
                    intY = randomGenerator.nextInt(level) + 1;
                }

                intZ = intX * intY;
                //_questionsMap.put(i, intX + " x " + intY);
                if (random) {
                    _questionsMap.put(_randomQuestionCount, intX + " x " + intY);
                } else {
                    _questionsMap.put(i, intX + " x " + intY);
                }
            } else {
                divisors = new ArrayList<>();
                Random randomElement = new Random();
                int limit = intZ;

                for (int j = 1; j <= limit; j++) {
                    if (intZ % j == 0) {
                        divisors.add(j);
                    }
                }

                intX = intZ;

                while (divisors.size() < 3) {
                    intZ = randomGenerator.nextInt(level) + 1;
                    limit = intZ;

                    divisors = new ArrayList<>();
                    for (int j = 1; j <= limit; j++) {
                        if (intZ % j == 0) {
                            divisors.add(j);
                        }
                    }
                    intX = intZ;
                }

                divisors.remove(new Integer(intX)); divisors.remove(new Integer(1));
                intY = divisors.get(randomElement.nextInt(divisors.size()));
                //if (divisors.size() == 2) {
                //    divisors.remove(new Integer(intX)); divisors.remove(new Integer(1));
                //    intY = divisors.get(randomElement.nextInt(divisors.size()));
                //} //else {
                //    divisors.remove(new Integer(intX)); divisors.remove(new Integer(1));
                //    intY = divisors.get(randomElement.nextInt(divisors.size()));
                //}
                intZ = intX / intY;
                //_questionsMap.put(i, intX + " / " + intY);
                if (random) {
                    _questionsMap.put(_randomQuestionCount, intX + " / " + intY);
                } else {
                    _questionsMap.put(i, intX + " / " + intY);
                }
            }


            answers.add(intZ);
            String maoriName = getMaoriName(intZ);

            if (random) {
                _questionAnswersMap.put(_randomQuestionCount, maoriName);
                System.out.println(_questionsMap.get(_randomQuestionCount) + " " + maoriName);
            } else {
                _questionAnswersMap.put(i, maoriName);
                System.out.println(_questionsMap.get(i) + " " + maoriName);
            }
            //_questionAnswersMap.put(i, maoriName);
        }
    }

    public void randomArithmetic(int questionNumber) {

        _questionsMap = new HashMap<>();
        _questionAnswersMap = new HashMap<>();
        _randomQuestionCount = 0;

        Random random = new Random();

        //int x = random.nextInt(ModeDirector.Mode.class.getEnumConstants().length);
        //ModeDirector.Mode mode = ModeDirector.Mode.class.getEnumConstants()[x];

        for (int i = 0; i < questionNumber; i++) {
            int x = random.nextInt(ModeDirector.RandomModes.class.getEnumConstants().length);
            ModeDirector.RandomModes mode = ModeDirector.RandomModes.class.getEnumConstants()[x];

            if (mode == ModeDirector.RandomModes.MATH_ADD) {
                arithmetic(99, 1, ModeDirector.Mode.MATH_ADD, true);
            } else if (mode == ModeDirector.RandomModes.MATH_SUB) {
                arithmetic(99, 1, ModeDirector.Mode.MATH_SUB, true);
            } else if (mode == ModeDirector.RandomModes.MATH_MULT) {
                arithmetic(10, 1, ModeDirector.Mode.MATH_MULT, true);
            } else {
                arithmetic(99, 1, ModeDirector.Mode.MATH_DIV, true);
            }
            _randomQuestionCount++;
        }
    }

    public HashMap<Integer, String> getCurrentQuestionMap() {
        return _questionsMap;
    }

    public HashMap<Integer, String> getCurrentAnswerMap() {
        return _questionAnswersMap;
    }
}
