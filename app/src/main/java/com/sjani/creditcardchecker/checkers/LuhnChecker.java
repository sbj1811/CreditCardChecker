package com.sjani.creditcardchecker.checkers;

/**
 * Checks credit card numbers for Luhn validation
 */
public class LuhnChecker implements Checkers {
    @Override
    public boolean check(String stringToCheck) {
        int sum = 0;
        boolean alternate = false;
        for (int i = stringToCheck.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(stringToCheck.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
