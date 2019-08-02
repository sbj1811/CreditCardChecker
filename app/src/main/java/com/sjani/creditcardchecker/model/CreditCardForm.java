package com.sjani.creditcardchecker.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.sjani.creditcardchecker.checkers.LuhnChecker;
import com.sjani.creditcardchecker.checkers.ValidCardChecker;

import java.util.regex.Pattern;

/**
 *  Credit Card Form Model
 */
public class CreditCardForm extends BaseObservable {

    private static final String TAG = CreditCardForm.class.getSimpleName();
    private CreditCard creditCard = new CreditCard();
    private MutableLiveData<Boolean> creditCardMutableLiveData = new MutableLiveData<>();

    /**
     * Checks Validity of the card
     * @return
     */
    @Bindable
    public boolean isValid() {
        boolean valid = isValidCardNumber() && isValidCvv() && isValidFirstName() && isValidLastName() && isValidFirstName() && isValidLastName();
        notifyChange();
        return valid;
    }

    /**
     * Returns if the card was valid
     */
    public void onClick() {
        if (isValid()) {
            creditCardMutableLiveData.setValue(true);
        } else {
            creditCardMutableLiveData.setValue(false);
        }
    }

    /**
     * Checks if card number is valid
     * @return
     */
    public boolean isValidCardNumber() {
        LuhnChecker luhnChecker = new LuhnChecker();
        ValidCardChecker validCardChecker = new ValidCardChecker();
        if (creditCard.getCreditCardNumber() != null && validCardChecker.check(creditCard.getCreditCardNumber())) {
            return luhnChecker.check(creditCard.getCreditCardNumber());
        }
        return false;
    }

    /**
     * Checks if cvv is valid
     * @return
     */
    public boolean isValidCvv() {
        if (creditCard.getCardType() != null && creditCard.getLastName() != null) {
            if (creditCard.getCardType().equals("american_express"))
                return Pattern.matches("[a-zA-Z]+", creditCard.getCvv()) == false && creditCard.getCvv().length() == 4;
            else
                return Pattern.matches("[a-zA-Z]+", creditCard.getCvv()) == false && creditCard.getCvv().length() == 3;
        }
        return false;
    }

    /**
     * Checks if first name is valid
     * @return
     */
    public boolean isValidFirstName() {
        return !creditCard.getFirstName().isEmpty();
    }

    /**
     * Checks if last name is valid
     * @return
     */
    public boolean isValidLastName() {
        return !creditCard.getLastName().isEmpty();
    }

    /**
     * Checks if expiry month is valid
     * @return
     */
    public boolean isValidExpiryMonth() {
        return creditCard.getExpiryMonth() != null && Integer.valueOf(creditCard.getExpiryMonth()) > 0 && Integer.valueOf(creditCard.getExpiryMonth()) < 13;
    }

    /**
     * Checks if expiry year is valid
     * @return
     */
    public boolean isValidExpiryYear() {
        return creditCard.getExpiryYear() != null && Integer.valueOf(creditCard.getExpiryMonth()) > 2013 && Integer.valueOf(creditCard.getExpiryMonth()) < 2026;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public MutableLiveData<Boolean> getCreditCardMutableLiveData() {
        return creditCardMutableLiveData;
    }

}
