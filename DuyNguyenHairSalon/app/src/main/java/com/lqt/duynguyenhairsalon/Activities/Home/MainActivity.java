package com.lqt.duynguyenhairsalon.Activities.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lqt.duynguyenhairsalon.CheckInternet.NetworkChangeListener;
import com.lqt.duynguyenhairsalon.Model.Adapters.ViewPagerAdapter;
import com.lqt.duynguyenhairsalon.R;

public class MainActivity extends AppCompatActivity {

    //View
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    //Param


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    //check xem đang ở fragment nào thì bottom sáng
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_tab_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_tab_favorites).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_tab_store).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_tab_account).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //bắt sự kiện Click vào bottomnav để chuyển fragment
                switch (item.getItemId()) {
                    case R.id.menu_tab_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_tab_favorites:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_tab_store:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_tab_account:
                        viewPager.setCurrentItem(3);
                        break;
                }

                return true;
            }
        });
    }

    private void initView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        viewPager = (ViewPager) findViewById(R.id.ViewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        //Số fragment load trước
        viewPager.setOffscreenPageLimit(3);
    }


}