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
import android.widget.TextView;
import android.widget.Toast;

import com.lqt.duynguyenhairsalon.Activities.Home.MainActivity;
import com.lqt.duynguyenhairsalon.R;
import com.lqt.duynguyenhairsalon.SharedPreferences.DataLocalManager;

public class LoginWithPhoneNumberActivity extends AppCompatActivity {

    //View
    private EditText editTextPhoneNumber, editTextPassword;
    private TextView textViewForgetPassword;
    private Button buttonLogin;
    private ImageView imageViewBack;

    //Param
    private String phoneNumber = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_phone_number);

        AnhXa();

        SetLogin();
    }

    private void SetLogin() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.equals(editTextPassword.getText().toString())) {
                    DataLocalManager.setPrefUserName(""+ phoneNumber);
                    DataLocalManager.setPrefIsLogged(true);
                    if (phoneNumber.equals("+84973271208")){
                        DataLocalManager.setPrefIsAdmin(true);
                    }
                    startActivity(new Intent(LoginWithPhoneNumberActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginWithPhoneNumberActivity.this, "Mã PIN không chính xác", Toast.LENGTH_SHORT).show();
                    editTextPassword.setText("");
                }
            }
        });
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextPassword.getText().toString().length() < 6) {
                    buttonLogin.setEnabled(false);
                    buttonLogin.setBackgroundResource(R.drawable.background_view_disible);
                    editTextPassword.setError("Mã PIN 6 số");
                } else {
                    buttonLogin.setEnabled(true);
                    buttonLogin.setBackgroundResource(R.drawable.background_topup);
                    editTextPassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        editTextPhoneNumber = (EditText) findViewById(R.id.editText_Phone);
        editTextPassword = (EditText) findViewById(R.id.editText_Password);
        buttonLogin = (Button) findViewById(R.id.button_Login);
        textViewForgetPassword = (TextView) findViewById(R.id.textView_ForgetPassword);
        imageViewBack = (ImageView) findViewById(R.id.imageView_Back);


        phoneNumber = getIntent().getStringExtra("PhoneNumber");
        password = getIntent().getStringExtra("Password");

        editTextPhoneNumber.setText(phoneNumber);
        editTextPassword.requestFocus();
    }
}