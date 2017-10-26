package model;

import java.util.HashMap;

/**
 * @author Joel Clarke, Luke Thompson
 */
public abstract class NumberCollection {

    private HashMap<Integer, String> _mapOfNumbers = new HashMap<>();

    /**
     * This method populates the map called _mapOfNumbers which contains all the numbers needed to construct Maori
     * words from 1 to 99.
     */
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

    /**
     * This method takes an input called number of type int and returns a String which is the Maori translation for
     * the given input number.
     * @param number number to be translated
     * @return Maori number, type String
     */
    String getMaoriName(int number) {
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
                return _mapOfNumbers.get(number);
            }
            // Number is >10 and < 20
            else if ((number > 10) && (number < 20)) {
                return _mapOfNumbers.get(10) + " maa " + _mapOfNumbers.get(digits[1]);
            }
            // Any other 2 digit number
            else if ((length2 == 2) && !((number > 10) && (number < 20))) {
                if (i == 0) {
                    maoriNum = maoriNum + _mapOfNumbers.get(currentDigit);
                    maoriNum = maoriNum + " " + _mapOfNumbers.get(10);
                }
                else if (i == 1) {
                    if (digits[1] != 0) {
                        maoriNum = maoriNum + " maa ";
                        maoriNum = maoriNum + _mapOfNumbers.get(currentDigit);
                    }
                }
            }
            // Any single digit number
            else {
                return  _mapOfNumbers.get(number);
            }

        }
        return maoriNum;
    }

    /**
     * These are abstract methods that all subclasses must implement so that they can all work in the same manner.
     * @return HashMap<Integer.String>
     */
    public abstract HashMap<Integer, String> getCurrentQuestionMap();
    public abstract HashMap<Integer, String> getCurrentAnswerMap();
}
