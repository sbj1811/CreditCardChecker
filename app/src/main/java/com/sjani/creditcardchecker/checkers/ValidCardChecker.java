package com.sjani.creditcardchecker.checkers;

/**
 * Checks if credit card numbers are valid combination
 */
public class ValidCardChecker implements Checkers {
    @Override
    public boolean check(String stringToCheck) {
        if (stringToCheck.matches("^4[0-9]{12}(?:[0-9]{3})?$")) {
            return true;
        } else if (stringToCheck.matches("^3[47][0-9]{13}$")) {
            return true;
        } else if (stringToCheck.matches("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$")) {
            return true;
        } else if (stringToCheck.matches("^6(?:011|5[0-9]{2})[0-9]{12}$")) {
            return true;
        }
        return false;
    }
}
