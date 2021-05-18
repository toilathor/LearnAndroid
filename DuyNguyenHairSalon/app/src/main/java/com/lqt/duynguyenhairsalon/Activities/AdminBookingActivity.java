package com.lqt.duynguyenhairsalon.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.tabs.TabLayout;
import com.lqt.duynguyenhairsalon.Model.Adapters.DayCutAdapter;
import com.lqt.duynguyenhairsalon.Model.Adapters.SelectServiceAdapter;
import com.lqt.duynguyenhairsalon.Model.Adapters.SelectSuccessAdapter;
import com.lqt.duynguyenhairsalon.Model.DayCut;
import com.lqt.duynguyenhairsalon.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdminBookingActivity extends AppCompatActivity {

    //Param
    private Calendar calendar;

    //List
    private List<DayCut> dayCutList;

    //Adapter
    private DayCutAdapter dayCutAdapter;
    private SelectSuccessAdapter successAdapter;

    //View
    private ImageView imageViewHome;
    private Spinner spinnerDay;
    private TabLayout tabLayoutSelectSuccess;
    private ViewPager2 viewPager2SelectSuccess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_booking);

        AnhXa();

        SetTabLayout();

        AdminBookingListen();

        ListDay();
    }

    private void AdminBookingListen() {
        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void SetTabLayout() {
        tabLayoutSelectSuccess.addTab(tabLayoutSelectSuccess.newTab().setText("Chưa hoàn thành"));
        tabLayoutSelectSuccess.addTab(tabLayoutSelectSuccess.newTab().setText("Đã hoàn thành"));

        successAdapter = new SelectSuccessAdapter(getSupportFragmentManager(), getLifecycle());

        viewPager2SelectSuccess.setAdapter(successAdapter);

        tabLayoutSelectSuccess.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2SelectSuccess.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        /*
         * Vuốt
         * */
        viewPager2SelectSuccess.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayoutSelectSuccess.selectTab(tabLayoutSelectSuccess.getTabAt(position));
            }
        });
    }


    private void ListDay() {
        calendar = Calendar.getInstance();

        dayCutList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            upToDate(i);
            String dayOfWeek = "";
            if (i == 0) dayOfWeek += "Hôm nay, ";
            if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                dayOfWeek += "Chủ Nhật";
            } else {
                dayOfWeek += "Thứ " + calendar.get(Calendar.DAY_OF_WEEK);
            }
            dayCutList.add(new DayCut(dayOfWeek + " ("
                    + calendar.get(Calendar.DATE)
                    + "/" + (calendar.get(Calendar.MONTH) + 1) + ")"
                    , "" + calendar.get(Calendar.YEAR)
                    + "-" + (calendar.get(Calendar.MONTH) + 1)
                    + "-" + calendar.get(Calendar.DATE)));
        }
        dayCutAdapter = new DayCutAdapter(this, R.layout.item_day_cut, dayCutList);

        spinnerDay.setAdapter(dayCutAdapter);
    }

    private void upToDate(int numDay) {
        if (numDay == 0) {
            return;
        }
        calendar.add(Calendar.DAY_OF_WEEK, 1);
    }

    private void AnhXa() {
        spinnerDay = (Spinner) findViewById(R.id.spinner_SelectDay);
        tabLayoutSelectSuccess = (TabLayout) findViewById(R.id.tabLayout_SelectSuccess);
        viewPager2SelectSuccess = (ViewPager2) findViewById(R.id.viewPager2_SelectSuccess);
        imageViewHome = (ImageView) findViewById(R.id.imageView_Home);
    }
}