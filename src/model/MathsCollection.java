package model;

import java.util.*;

public class MathsCollection extends NumberCollection {
    private static MathsCollection _mathsCollection;
    private HashMap<Integer, String> _questionsMap;
    private HashMap<Integer, String> _questionAnswersMap;
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

    /**
     * This method populates two maps. Firstly a map called _questionsMap is populated which a set of math questions are added based
     * on the enum ModeDirector.Mode which can either be addition, subtraction, multiplication, division or random. Along
     * with this the map _questionAnswerMap is also populated where the key matches a key from _questionsMap and its value
     * corresponds to the respective answer of the question saved in the _questionsMap value.
     * @param level sets the level of difficulty of the question
     * @param questionNumber the number of questions that will be added to the maps
     * @param mode what mode of math questions are to be added
     * @param random is the random
     */
    public void arithmetic(int level, int questionNumber, ModeDirector.Mode mode, boolean random) {

        // If the mode is not random new maps are initialized to be populated
        if (!random) {
            _questionsMap = new HashMap<>();
            _questionAnswersMap = new HashMap<>();
        }

        ArrayList<Integer> answers = new ArrayList<>();
        List<Integer> divisors;

        Random randomGenerator = new Random();

        // Iterates through the total number of questions to be added, specified by the input questionNumber
        for (int i = 0; i < questionNumber; i++) {

            int intZ;
            int intY;
            int intX;

            intZ = randomGenerator.nextInt(level) + 1;

            // Mode is addition
            if (mode == ModeDirector.Mode.MATH_ADD) {
                intY = randomGenerator.nextInt(intZ) + 1;
                intX = intZ - intY;

                while ((answers.contains(intZ)) && (intZ == intY)) {
                    intZ = randomGenerator.nextInt(level) + 1;
                    intY = randomGenerator.nextInt(intZ) + 1;
                    intX = intZ - intY;
                }

                // Adds the question generated to the associated map
                if (random) {
                    _questionsMap.put(_randomQuestionCount, intX + " + " + intY);
                } else {
                    _questionsMap.put(i, intX + " + " + intY);
                }
            }
            // Mode is subtraction
            else if (mode == ModeDirector.Mode.MATH_SUB){
                intX = randomGenerator.nextInt(level) + 10;
                intY = randomGenerator.nextInt(level - 1) + 1;

                /* Checks the answer to the generated question will be in the allowed range, if not the process will be
                 * repeated until it is.
                 */
                while (((intX - intY) < 1) || ((intX - intY) > 100) || (intX < intY)) {
                    intX = randomGenerator.nextInt(level) + 10;
                    intY = randomGenerator.nextInt(level - 1) + 1;
                }

                intZ = intX - intY;

                // Adds question to the associated map
                if (random) {
                    _questionsMap.put(_randomQuestionCount, intX + " - " + intY);
                } else {
                    _questionsMap.put(i, intX + " - " + intY);
                }
            }
            // Mode is multiplication
            else if (mode == ModeDirector.Mode.MATH_MULT){
                intX = randomGenerator.nextInt(level) + 1;
                intY = randomGenerator.nextInt(level) + 1;

                // Checks that the equation will not be 10 x 10 and the left or right hand side of the equation will not be 1
                while (((intX == intY) && (intX == 10)) || (intX == 1) || (intY == 1)) {
                    intX = randomGenerator.nextInt(level) + 1;
                    intY = randomGenerator.nextInt(level) + 1;
                }

                intZ = intX * intY;

                // Adds question to the associated map
                if (random) {
                    _questionsMap.put(_randomQuestionCount, intX + " x " + intY);
                } else {
                    _questionsMap.put(i, intX + " x " + intY);
                }
            }
            // Mode is division
            else {
                divisors = new ArrayList<>();
                Random randomElement = new Random();
                int limit = intZ;

                // Gets all divisors of intZ
                for (int j = 1; j <= limit; j++) {
                    if (intZ % j == 0) {
                        if (j < 11) {
                            divisors.add(j);
                        }
                    }
                }

                intX = intZ;

                /*
                 * Checks if the number of divisors is greater than 3, if not the process above is repeated until one is
                 * achieved. This is to ensure that the equation being generated does not include 1 or itself.
                 */
                while (divisors.size() < 3) {
                    intZ = randomGenerator.nextInt(level) + 1;
                    limit = intZ;

                    divisors = new ArrayList<>();
                    for (int j = 1; j <= limit; j++) {
                        if (intZ % j == 0) {
                            if (j < 11) {
                                divisors.add(j);
                            }
                        }
                    }
                    intX = intZ;
                }

                // Removes 1 and itself from the divisors so again can't be part of the generated question
                divisors.remove(new Integer(intX));
                divisors.remove(new Integer(1));

                intY = divisors.get(randomElement.nextInt(divisors.size()));
                intZ = intX / intY;

                // Adds the question to the associated map
                if (random) {
                    _questionsMap.put(_randomQuestionCount, intX + " / " + intY);
                } else {
                    _questionsMap.put(i, intX + " / " + intY);
                }
            }

            answers.add(intZ);

            // Converts the answer of the current question to Maori of type String
            String maoriName = getMaoriName(intZ);

            // Adds Maori answer to associated map
            if (random) {
                _questionAnswersMap.put(_randomQuestionCount, maoriName);
            } else {
                _questionAnswersMap.put(i, maoriName);
            }
        }
    }

    /**
     * This method populates two maps similar to the arithmetic method above, however the way that this is done is
     * different. The method utilizes the arithmetic method and calls it a specific number of times based on the input
     * variable questionNumber. Each call to arithmetic has a randomly selected mode as a parameter and the number of
     * questions set to 1. This allows for different questions of random mode to be added to the maps one question at
     * a time.
     * @param questionNumber number of questions to add
     */
    public void randomArithmetic(int questionNumber) {

        _questionsMap = new HashMap<>();
        _questionAnswersMap = new HashMap<>();
        _randomQuestionCount = 0;

        Random random = new Random();

        // Iterates through the number of questions that need to be added to the map
        for (int i = 0; i < questionNumber; i++) {

            // Generates a random enum of ModeDirector.RandomModes, either add, sub, mult or div.
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

    /**
     * This method returns a map containing all the current questions
     * @return
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
