package com.sjani.creditcardchecker;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
        activityMainBinding.cvvInfo.setOnClickListener(imageView -> {
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.info_dialog);
            String title = getString(R.string.cvv_dialog_title);
            dialog.setTitle(title);
            TextView text = (TextView) dialog.findViewById(R.id.cvv_dialog_text);
            String message = getString(R.string.cvv_info_text_line_1) + "\n\n" +
                    getString(R.string.cvv_info_text_line_2);
            text.setText(message);
            ImageView image = (ImageView) dialog.findViewById(R.id.cvv_dialog_image);

            Button dialogButton = (Button) dialog.findViewById(R.id.cvv_dialog_button);
            dialogButton.setOnClickListener(button -> dialog.dismiss());

            dialog.show();
        });
        setupSubmitbutton();
    }

    /**
     * Handles submit button click
     */
    private void setupSubmitbutton() {
        viewModel.getCreditCardData().observe(this, aBoolean -> {
            if (aBoolean) {
                Toast.makeText(MainActivity.this, "Success! Credit card saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Invalid: Please enter missing/correct credit card details!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
