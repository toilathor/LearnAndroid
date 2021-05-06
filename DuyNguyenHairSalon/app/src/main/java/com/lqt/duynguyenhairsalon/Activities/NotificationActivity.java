package com.lqt.duynguyenhairsalon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lqt.duynguyenhairsalon.R;

public class NotificationActivity extends AppCompatActivity {

    private ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        AnhXa();

        listenNotification();
    }

    private void listenNotification() {
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
    }
}