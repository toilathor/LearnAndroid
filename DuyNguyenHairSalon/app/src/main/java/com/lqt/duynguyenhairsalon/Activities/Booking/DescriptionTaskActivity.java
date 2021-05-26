package com.lqt.duynguyenhairsalon.Activities.Booking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lqt.duynguyenhairsalon.Model.mTask;
import com.lqt.duynguyenhairsalon.R;

import java.util.List;

public class DescriptionTaskActivity extends AppCompatActivity {

    //View
    private ImageView imageViewBack;
    private Button buttonSuccess;
    private TextView textViewName;
    private TextView textViewPhone;
    private TextView textViewDate;
    private CheckBox checkBoxChupAnhSauKhiCat;
    private CheckBox checkBoxYeuCauTuVan;
    private ListView listViewService;
    private TextView textViewSum;

    //Adapter
    private ArrayAdapter<String> serviceAdapter;

    //Param
    private boolean isSuccessful;
    private String data;

    //List

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_task);

        Intent intent = getIntent();

        isSuccessful = intent.getBooleanExtra("isSuccsessful", true);

        data = intent.getStringExtra("data");


        AnhXa();

        if (isSuccessful) {
            buttonSuccess.setVisibility(View.GONE);
        }

        SetData();

        DescriptionTaskListen();
    }

    private void SetData() {
        Gson gson = new Gson();
        mTask task = gson.fromJson(data, mTask.class);

        textViewName.setText(task.getUser().getName_User());
        textViewPhone.setText(task.getUser().getPhone_Number_User());
        textViewDate.setText("Đặt lịch: " + task.getDate_Task());
        if (task.getIs_Consulting() == 1) {
            checkBoxYeuCauTuVan.setChecked(true);
        } else {
            checkBoxYeuCauTuVan.setChecked(false);
        }

        if (task.getIs_Save_Photo() == 1) {
            checkBoxChupAnhSauKhiCat.setChecked(true);
        } else {
            checkBoxChupAnhSauKhiCat.setChecked(false);
        }

        textViewSum.setText("" + task.getSum_Money_Task() / 1000 + "K");

        serviceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, task.getList_Service());

        listViewService.setAdapter(serviceAdapter);
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
        buttonSuccess = (Button) findViewById(R.id.button_Success);
        textViewName = (TextView) findViewById(R.id.textView_Name);
        textViewPhone = (TextView) findViewById(R.id.textView_Phone);
        textViewDate = (TextView) findViewById(R.id.textView_Date);
        checkBoxYeuCauTuVan = (CheckBox) findViewById(R.id.checkbox_YeuCauTuVan);
        checkBoxChupAnhSauKhiCat = (CheckBox) findViewById(R.id.checkbox_ChupAnhSauKhiCat);
        listViewService = (ListView) findViewById(R.id.listView_Service);
        textViewSum = (TextView) findViewById(R.id.textView_Sum);
    }
}