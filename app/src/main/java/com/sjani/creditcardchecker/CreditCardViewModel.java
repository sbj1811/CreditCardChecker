package com.sjani.creditcardchecker;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sjani.creditcardchecker.bindings.CreditCardBindingAdapter;
import com.sjani.creditcardchecker.model.CreditCardForm;


public class CreditCardViewModel extends ViewModel {

    private static final String TAG = CreditCardViewModel.class.getSimpleName();
    private CreditCardForm creditCardForm;
    private View.OnFocusChangeListener onFocusCCNumber;
    private View.OnFocusChangeListener onFocusCvv;
    private View.OnFocusChangeListener onFocusFirstName;
    private View.OnFocusChangeListener onFocusLastName;
    private View.OnFocusChangeListener onFocusExpiryMonth;
    private View.OnFocusChangeListener onFocusExpiryYear;

    public void init() {
        creditCardForm = new CreditCardForm();
        onFocusCCNumber = (view, focused) -> {
            EditText et = (EditText) view;
            if (et.getText().length() > 0 && !focused) {
                creditCardForm.isValidCardNumber();
                ImageView im = view.getRootView().findViewById(R.id.card_type_image);
                CreditCardBindingAdapter.loadImage(im, creditCardForm.getCreditCard().getCardImage());
            }
        };

        onFocusCvv = (view, focused) -> {
            EditText et = (EditText) view;
            if (et.getText().length() > 0 && !focused) {
                creditCardForm.isValidCvv();
            }
        };

        onFocusFirstName = (view, focused) -> {
            EditText et = (EditText) view;
            if (et.getText().length() > 0 && !focused) {
                creditCardForm.isValidFirstName();
            }
        };

        onFocusLastName = (view, focused) -> {
            EditText et = (EditText) view;
            if (et.getText().length() > 0 && !focused) {
                creditCardForm.isValidLastName();
            }
        };

        onFocusExpiryMonth = (view, focused) -> {
            EditText et = (EditText) view;
            if (et.getText().length() > 0 && !focused) {
                creditCardForm.isValidExpiryMonth();
            }
        };

        onFocusExpiryYear = (view, focused) -> {
            EditText et = (EditText) view;
            if (et.getText().length() > 0 && !focused) {
                creditCardForm.isValidExpiryYear();
            }
        };
    }


    public MutableLiveData<Boolean> getCreditCardData() {
        creditCardForm.onClick();
        return creditCardForm.getCreditCardMutableLiveData();
    }


    public CreditCardForm getCreditCardForm() {
        return creditCardForm;
    }

    public View.OnFocusChangeListener getOnFocusCCNumber() {
        return onFocusCCNumber;
    }

    public View.OnFocusChangeListener getOnFocusCvv() {
        return onFocusCvv;
    }

    public View.OnFocusChangeListener getOnFocusFirstName() {
        return onFocusFirstName;
    }

    public View.OnFocusChangeListener getOnFocusLastName() {
        return onFocusLastName;
    }

    public View.OnFocusChangeListener getOnFocusExpiryMonth() {
        return onFocusExpiryMonth;
    }

    public View.OnFocusChangeListener getOnFocusExpiryYear() {
        return onFocusExpiryYear;
    }


    public String getImageUrl() {
        return creditCardForm.getCreditCard().getCardImage();
    }


}
