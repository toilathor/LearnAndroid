package com.lqt.duynguyenhairsalon.Activities.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;
import com.lqt.duynguyenhairsalon.R;

public class LoginWithPhoneNumberActivity extends AppCompatActivity {

    //View
    private CountryCodePicker countryCodePicker;
    private ImageView imageViewCheck;
    private EditText editTextPhone;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_phone_number);

        AnhXa();

        SetCountryCodePicker();

        SetListenerActivity();
    }

    private void SetListenerActivity() {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginWithPhoneNumberActivity.this, EnterOTPActivity.class));
            }
        });
    }

    private void SetCountryCodePicker() {
        //Nối CountCode với EditText để nó check
        countryCodePicker.registerCarrierNumberEditText(editTextPhone);

        countryCodePicker.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
            @Override
            public void onValidityChanged(boolean isValidNumber) {
                if(isValidNumber == false){
                    imageViewCheck.setImageResource(R.drawable.remove);
                    buttonNext.setEnabled(false);
                    buttonNext.setBackgroundResource(R.drawable.background_view_disible);
                }else{
                    imageViewCheck.setImageResource(R.drawable.check);
                    buttonNext.setEnabled(true);
                    buttonNext.setBackgroundResource(R.drawable.background_topup);
                }
            }
        });

        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count != 0) imageViewCheck.setVisibility(View.VISIBLE);
                else imageViewCheck.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void AnhXa() {
        countryCodePicker = (CountryCodePicker) findViewById(R.id.countryCodePicker);
        imageViewCheck = (ImageView) findViewById(R.id.imageView_Check) ;
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        buttonNext = (Button) findViewById(R.id.button_Next);
    }
}