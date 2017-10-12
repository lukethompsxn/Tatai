package model;

import java.util.HashMap;

public abstract class NumberCollection {
    //private int _totalScore;
    //private int _totalIterations;

    private HashMap<Integer, String> _mapOfNumbers = new HashMap<>();

    //public enum Mode { PRACTICE_EASY, PRACTICE_HARD, MATH_ADD, MATH_SUB, MATH_MULT, MATH_DIV, MATH_RANDOM, MATH_CUSTOM };
    //public enum Type { PRACTICE, MATH };
    //private Mode _currentMode = Mode.PRACTICE_EASY;
    //private Type _currentType = Type.PRACTICE;


    //Singleton Constructor
    //public static NumberCollection instance() {
    //   if (_numberCollection == null) {
    //        _numberCollection = new NumberCollection();
    //    }
    //    return _numberCollection;
    //}

    //Private Constructor
    //private NumberCollection() {
    //    //anything that needs to constructed
    //
    //    _mapOfNumbers.put(0, "");
    //    _mapOfNumbers.put(1, "tahi");
    //    _mapOfNumbers.put(2, "rua");
    //    _mapOfNumbers.put(3, "toru");
    //    _mapOfNumbers.put(4, "whaa");
    //    _mapOfNumbers.put(5, "rima");
    //    _mapOfNumbers.put(6, "ono");
    //    _mapOfNumbers.put(7, "whitu");
    //    _mapOfNumbers.put(8, "waru");
    //    _mapOfNumbers.put(9, "iwa");
    //    _mapOfNumbers.put(10, "tekau");
    //}

    public void initialize() {
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

    public String getMaoriName(int number) {
        String maoriNum = "";

        int length = String.valueOf(number).length();
        int[] digits = new int[length];

        int i = 0;
        int tmp = number;

        // While loop breaks up the input number into its digits
        while (tmp > 0) {
            if (tmp == 0) {
                digits[i] = 0;
                break;
            }
            else if (digits.length != 1) {
                digits[digits.length - (1 + i)] = tmp % 10;
            }
            else {
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
                }
                else if (i == 1) {
                    if (digits[1] != 0) {
                        maoriNum = maoriNum + " maa ";
                        maoriNum = maoriNum + _mapOfNumbers.get(currentDigit).toString();
                    }
                }
            }
            // Any single digit number
            else {
                return  _mapOfNumbers.get(number).toString();
            }

        }
        return maoriNum;
    }

    public abstract HashMap<Integer, String> getCurrentQuestionMap();

    public abstract HashMap<Integer, String> getCurrentAnswerMap();

    //public void updateStats(int score, int iterations) {
    //   _totalScore+=score;
    //    _totalIterations+=iterations;
    //}

    //public void setMode(Mode mode) {
    //    _currentMode = mode;
    //}

    //public Mode getMode() {
    //    return _currentMode;
    //}

    //public void setType(Type type) {
    //   _currentType = type;
    //}

    //public Type getType() {
    //    return _currentType;
    //}


}
