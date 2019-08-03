package com.sjani.creditcardchecker.bindings;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.squareup.picasso.Picasso;


public class CreditCardBindingAdapter {

    private static final String TAG = CreditCardBindingAdapter.class.getSimpleName();


    /**
     * Bind adapter for month spinner
     *
     * @param spinner
     * @param selectedExpMon
     * @param changeListener
     */
    @BindingAdapter(value = {"expMon", "expMonChanged"}, requireAll = false)
    public static void setExpMon(final AppCompatSpinner spinner,
                                 final String selectedExpMon,
                                 final InverseBindingListener changeListener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeListener.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                changeListener.onChange();
            }
        });

        spinner.setSelection(getIndexOfItemExpMon(spinner, selectedExpMon));

    }

    @InverseBindingAdapter(attribute = "expMon", event = "expMonChanged")
    public static String getExpMon(final AppCompatSpinner spinner) {
        return (String) spinner.getSelectedItem();
    }

    private static int getIndexOfItemExpMon(AppCompatSpinner spinner, String item) {
        Adapter adapter = (ArrayAdapter<String>) spinner.getAdapter();
        if (adapter != null && item != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                if ((adapter.getItem(i)).equals(item)) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * Bind adapter for year spinner
     *
     * @param spinner
     * @param selectedExpYr
     * @param changeListener
     */
    @BindingAdapter(value = {"expYr",
            "expYrChanged"}, requireAll = false)
    public static void setExpYr(final AppCompatSpinner spinner,
                                final String selectedExpYr,
                                final InverseBindingListener changeListener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeListener.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                changeListener.onChange();
            }
        });
        spinner.setSelection(getIndexOfItemExpYr(spinner, selectedExpYr));

    }

    @InverseBindingAdapter(attribute = "expYr",
            event = "expYrChanged")
    public static String getExpYr(final AppCompatSpinner spinner) {
        return (String) spinner.getSelectedItem();
    }

    private static int getIndexOfItemExpYr(AppCompatSpinner spinner, String item) {
        Adapter adapter = spinner.getAdapter();
        if (adapter != null && item != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                if ((adapter.getItem(i)).equals(item)) {
                    return i;
                }
            }
        }
        return 0;
    }

    @BindingAdapter("onFocus")
    public static void bindFocusChange(EditText editText, View.OnFocusChangeListener onFocusChangeListener) {
        if (editText.getOnFocusChangeListener() == null) {
            editText.setOnFocusChangeListener(onFocusChangeListener);
        }
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        int resourceId = view.getContext().getResources().getIdentifier("" + imageUrl, "drawable", view.getContext().getPackageName());
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.with(view.getContext())
                    .load(resourceId)
                    .into(view);
        }
    }


}
