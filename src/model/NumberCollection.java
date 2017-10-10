package model;

import java.util.*;

public class NumberCollection {

    private List<Integer> _englishNumbers;
    private HashMap<Integer, String> _allNumbers = new HashMap<>();
    private static NumberCollection numberCollection;
    private HashMap<Integer, String> _mapOfNumbers = new HashMap<>();
    private int _currentLevel;
    private int _currentNumber;
    private int _iteration = 0;
    private int _score = 0;
    private Boolean _attempted = false;
    private String recordingResult;
    private Boolean _playbackEnabled = true;
    private int easyScore = 0;
    private int easyTotal = 0;
    private int hardScore = 0;
    private int hardTotal = 0;

    //Singleton Constructor
    public static NumberCollection instance() {
        if (numberCollection == null) {
            numberCollection = new NumberCollection();
        }
        return numberCollection;
    }

    //Private Constructor, defines a map with numbers and the Maori equivalent.
    private NumberCollection() {

        _mapOfNumbers.put(0, "");
        _mapOfNumbers.put(1, "tahi");
        _mapOfNumbers.put(2, "rua");
        _mapOfNumbers.put(3, "toru");
        _mapOfNumbers.put(4, "whaa");
        _mapOfNumbers.put(5, "rima");
        _mapOfNumbers.put(6, "ono");
        _mapOfNumbers.put(7, "whitu");
        _mapOfNumbers.put(8, "waru");
        _mapOfNumbers.put(9, "iwa");
        _mapOfNumbers.put(10, "tekau");
    }

    /* This method defines a set list of 10 numbers randomly generated based of a max value. These numbers are added
     * to a list and to a map where its corresponding Maori name is also added.
     */
    public void setCollection() {
        int level = getCurrentLevel();
        _englishNumbers = new ArrayList<>();
        ArrayList<Integer> randNumbers = new ArrayList();

        Random randomGenerator = new Random();

        for (int i = 0; i < 10; i++) {
            int randomInt = randomGenerator.nextInt(level) + 1;
            while (randNumbers.contains(randomInt)) {
                randomInt = randomGenerator.nextInt(level) + 1;
            }
            randNumbers.add(randomInt);
            String maoriName = getMaoriName(randomInt);
            _englishNumbers.add(randomInt);
            _allNumbers.put(randomInt, maoriName);
        }
    }

    // This method takes an integer as an input and returns a string representing the Maori name for the input
    public String getMaoriName(int number) {

        String maoriNum = "";
        int length = String.valueOf(number).length();
        int[] digits = new int[length];

        int i = 0;
        int tmp = number;

        // While loop breaks up the input number into its digit
        while (tmp > 0) {
            if (tmp == 0) {
                digits[i] = 0;
                break;
            } else if (digits.length != 1) {
                digits[digits.length - (1 + i)] = tmp % 10;
            } else {
                digits[i] = tmp % 10;
            }
            i++;
            tmp = tmp / 10;
        }

        for (i = 0; i < digits.length; i++) {
            int length2 = digits.length;
            int currentDigit = digits[i];

            // Number is 10
            if (number == 10) {
                return _mapOfNumbers.get(number).toString();
            }
            // Number is >10 and < 20
            else if ((number > 10) && (number < 20)) {
                return _mapOfNumbers.get(10) + " maa " + _mapOfNumbers.get(digits[1]).toString();
            }
            // Any other 2 digit number
            else if ((length2 == 2) && !((number > 10) && (number < 20))) {
                if (i == 0) {
                    maoriNum = maoriNum + _mapOfNumbers.get(currentDigit).toString();
                    maoriNum = maoriNum + " " + _mapOfNumbers.get(10);
                } else if (i == 1) {
                    if (digits[1] != 0) {
                        maoriNum = maoriNum + " maa ";
                        maoriNum = maoriNum + _mapOfNumbers.get(currentDigit).toString();
                    }
                }
            }
            // Any single digit number
            else {
                return _mapOfNumbers.get(number).toString();
            }

        }
        return maoriNum;
    }

    //Sets the current level field to an argument passed in, called from different scene controllers
    public void setCurrentLevel(int level) {
        _currentLevel = level;
    }

    //Gets the current level field, called from different scene controllers
    public int getCurrentLevel() {
        return _currentLevel;
    }

    public void setCurrentNumber(int number) {
        _currentNumber = number;
    }

    public int getCurrentNumber() {
        return _currentNumber;
    }

    //Sets the current iteration, called from question view controller and correct view controller
    public void setIteration(int iteration) {
        _iteration = iteration;
    }

    //Gets the current iteration, called from correct view controller and question view controller
    public int getIteration() {
        return _iteration;
    }

    //Sets the current score for the level, called from question view controller and summary view controller
    public void setScore(int score) {
        _score = score;
    }

    //Gets the current score for the level, called from question view controller and summary view controller
    public int getScore() {
        return _score;
    }

    // Returns the current number being tested in english
    public int getNumber() {
        return _englishNumbers.get(_iteration);
    }

    // Returns a number being tested in Maori
    public String returnMaoriName(int mapKey) {
        return _allNumbers.get(mapKey);
    }

    // Sets a field that holds the result of voice recognition
    public void setRecordingResult(String result) {
        recordingResult = result;
    }

    // Returns the result of the voice recognition
    public String getRecordingResult() {
        return recordingResult;
    }

    // Sets whether the current question has been attempted before
    public void setAttempted(Boolean bool) {
        _attempted = bool;
    }

    // Returns if the current question has been attempted
    public Boolean getAttempted() {
        return _attempted;
    }

    // Sets whether playback is enabled as per checkbox
    public void setPlaybackEnabled(Boolean bool) {
        _playbackEnabled = bool;
    }

    // Gets whether playback is enabled
    public Boolean getPlaybackEnabled() {
        return _playbackEnabled;
    }

    // Updates the statistics for every level/round
    public void updateStats() {
        if (_currentLevel == 10) {
            easyScore = easyScore + _score;
            easyTotal = easyTotal + _iteration;
        }
        else if (_currentLevel == 99) {
            hardScore = hardScore + _score;
            hardTotal = hardTotal + _iteration;
        }
    }

    // Returns the statistics for the current session
    public int returnStats(String level) {
        if (level.equals("easy")) {
            if (easyTotal != 0) {
                return (100 * easyScore / easyTotal);
            }
            else {
                return -1;
            }
        }
        else if (level.equals("hard")) {
            if (hardTotal != 0) {
                return (100 * hardScore / hardTotal);
            }
            else {
                return -1;
            }
        }
        return -1;
    }
}
