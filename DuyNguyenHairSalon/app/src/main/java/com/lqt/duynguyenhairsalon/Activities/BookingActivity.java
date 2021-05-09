package com.lqt.duynguyenhairsalon.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lqt.duynguyenhairsalon.Model.DayCut;
import com.lqt.duynguyenhairsalon.Model.Adapters.DayCutAdapter;
import com.lqt.duynguyenhairsalon.Model.ServicesDuyNguyenHairSalon;
import com.lqt.duynguyenhairsalon.R;
import com.wefika.flowlayout.FlowLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 123;
    private Spinner spinnerDay;
    private List<DayCut> dayCutList;
    private DayCutAdapter dayCutAdapter;
    private ImageView imageViewHome;
    private Button button_SelectService, button_Success;

    private List<ServicesDuyNguyenHairSalon> servicesList = new ArrayList<>();
    private FlowLayout flowLayoutServices;

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
                Intent intent = new Intent(BookingActivity.this, SelectServiceActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
//                startActivity();
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

    private void setFlowLayoutService() {
        if (flowLayoutServices == null) {
            return;
        }

        flowLayoutServices.removeAllViews();

        if (servicesList != null || servicesList.size() > 0) {
            for (ServicesDuyNguyenHairSalon service : servicesList) {
                TextView textViewService = new TextView(this);
                FlowLayout.LayoutParams param = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT);
                param.setMargins(0, 20, 20, 0);
                textViewService.setLayoutParams(param);
                textViewService.setText("" + service.getNameService());
                textViewService.setPadding(30, 10, 30, 10);
                textViewService.setBackgroundResource(R.drawable.background_textview_to_edittext);
                textViewService.setTextColor(getResources().getColor(R.color.black));

                flowLayoutServices.addView(textViewService);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String dataStringExtra = data.getStringExtra("data");
            Gson gson = new Gson();
            /*
            * phải tạo mộ kiểu danh sách như thế này để truyền vào
            * thì Gson mới đọc được list
            *
            *https://www.youtube.com/watch?v=xbo1G02c2VM&list=RDCMUC_Fh8kvtkVPkeihBs42jGcA&start_radio=1&t=443
            *
            * */
            Type type = new TypeToken<List<ServicesDuyNguyenHairSalon>>(){}.getType();
            servicesList = gson.fromJson(dataStringExtra, type);
            setFlowLayoutService();
            Log.d("check_send_data", "" + servicesList.size());

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
     * ánh xạ các view
     * */
    private void AnhXa() {
        spinnerDay = (Spinner) findViewById(R.id.spinner_SelectDay);
        imageViewHome = (ImageView) findViewById(R.id.imageView_Home);
        button_SelectService = (Button) findViewById(R.id.button_SelectService);
        button_Success = (Button) findViewById(R.id.button_SuccessBook);
        flowLayoutServices = (FlowLayout) findViewById(R.id.flowLayout_SelectedService);
    }
}