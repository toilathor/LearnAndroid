package com.lqt.duynguyenhairsalon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lqt.duynguyenhairsalon.R;

public class HistoryCutActivity extends AppCompatActivity {

    ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_cut);

        AnhXa();

        ListenActivity();
    }

    private void ListenActivity() {
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