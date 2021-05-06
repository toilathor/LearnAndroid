package com.lqt.duynguyenhairsalon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.lqt.duynguyenhairsalon.Model.DayCut;
import com.lqt.duynguyenhairsalon.Model.Adapters.DayCutAdapter;
import com.lqt.duynguyenhairsalon.R;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    private Spinner spinnerDay;
    private List<DayCut> dayCutList;
    private DayCutAdapter dayCutAdapter;
    private ImageView imageViewHome;
    private Button button_SelectService, button_Success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        AnhXa();

        ListDay();

        BookingListen();
    }

    /*
     * Hàm BookingListen sẽ cử lí các sự kiện chính trong BookinkActivity
     * */
    private void BookingListen() {
        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(BookingActivity.this, MainActivity.class));
            }
        });

        button_SelectService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingActivity.this, SelectServiceActivity.class));
            }
        });
        button_Success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                startActivity(new Intent(BookingActivity.this, MainActivity.class));
            }
        });
    }

    /*
     * Xử lí spinner Day
     * */
    private void ListDay() {
        dayCutList = new ArrayList<>();
        dayCutList.add(new DayCut("Thứ 2 3/5"));
        dayCutList.add(new DayCut("Thứ 3 4/5"));
        dayCutList.add(new DayCut("Thứ 4 5/5"));

        dayCutAdapter = new DayCutAdapter(this, R.layout.item_day_cut, dayCutList);

        spinnerDay.setAdapter(dayCutAdapter);
    }

    /*
     * ánh xạ các view
     * */
    private void AnhXa() {
        spinnerDay = (Spinner) findViewById(R.id.spinner_SelectDay);
        imageViewHome = (ImageView) findViewById(R.id.imageView_Home);
        button_SelectService = (Button) findViewById(R.id.button_SelectService);
        button_Success = (Button) findViewById(R.id.button_SuccessBook);
    }
}