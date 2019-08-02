package com.sjani.creditcardchecker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreditCard implements Serializable {

    @SerializedName("creditCardNumber")
    @Expose
    String creditCardNumber;
    @SerializedName("expiryMonth")
    @Expose
    String expiryMonth;
    @SerializedName("expiryYear")
    @Expose
    String expiryYear;
    @SerializedName("cvv")
    @Expose
    String cvv;
    @SerializedName("firstName")
    @Expose
    String firstName;
    @SerializedName("lastName")
    @Expose
    String lastName;
    @SerializedName("cardType")
    @Expose
    String cardType;
    @SerializedName("cardImage")
    @Expose
    String cardImage;

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", expiryMonth='" + expiryMonth + '\'' +
                ", expiryYear='" + expiryYear + '\'' +
                ", cvv='" + cvv + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardImage='" + cardImage + '\'' +
                '}';
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        if (creditCardNumber != null) this.setCardType();
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public void setCardType() {
        if (creditCardNumber.matches("^4[0-9]{12}(?:[0-9]{3})?$")) {
            this.cardType = "visa";
        } else if (creditCardNumber.matches("^3[47][0-9]{13}$")) {
            this.cardType = "american_express";
        } else if (creditCardNumber.matches("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$")) {
            this.cardType = "master_card";
        } else if (creditCardNumber.matches("^6(?:011|5[0-9]{2})[0-9]{12}$")) {
            this.cardType = "discover";
        } else {
            this.cardType = "null";
        }
        this.setCardImage(cardImageSelector(this.cardType));
    }

    private String cardImageSelector(String cardType) {
        if (cardType.equals("visa")) {
            return "icons8_visa_48";
        } else if (cardType.equals("american_express")) {
            return "icons8_american_express_48";
        } else if (cardType.equals("master_card")) {
            return "icons8_mastercard_48";
        } else if (cardType.equals("discover")) {
            return "icons8_discover_48";
        }
        return "";
    }
}
