package com.lqt.duynguyenhairsalon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lqt.duynguyenhairsalon.R;

public class DescriptionTaskActivity extends AppCompatActivity {

    //View
    ImageView imageViewBack;

    //Adapter

    //Param

    //List


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_task);

        AnhXa();

        DescriptionTaskListen();
    }

    private void DescriptionTaskListen() {
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        imageViewBack = (ImageView) findViewById(R.id.imageView_Back);
    }
}