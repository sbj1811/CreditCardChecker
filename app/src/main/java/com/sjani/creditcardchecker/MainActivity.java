package com.sjani.creditcardchecker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.sjani.creditcardchecker.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    CreditCardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBindings(savedInstanceState);
    }

    /**
     * Sets up binding for this activity
     *
     * @param savedInstanceState
     */
    private void setupBindings(Bundle savedInstanceState) {
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(CreditCardViewModel.class);
        String[] months = getResources().getStringArray(R.array.months);
        String[] years = getResources().getStringArray(R.array.year);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityMainBinding.setViewModel(viewModel);
        activityMainBinding.setSpinAdapterMonth(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, months));
        activityMainBinding.setSpinAdapterYear(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years));
        setupSubmitbutton();
    }

    /**
     * Handles submit button click
     */
    private void setupSubmitbutton() {
        viewModel.getCreditCardData().observe(this, isSuccess -> {
            if (isSuccess) {
                Toast.makeText(MainActivity.this, "Success! Credit card saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Invalid: Please enter missing/correct credit card details!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
