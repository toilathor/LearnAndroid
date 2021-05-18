package com.lqt.duynguyenhairsalon.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lqt.duynguyenhairsalon.Model.Adapters.SelectTimeAdapter;
import com.lqt.duynguyenhairsalon.Model.BookingTime;
import com.lqt.duynguyenhairsalon.Model.DayCut;
import com.lqt.duynguyenhairsalon.Model.Adapters.DayCutAdapter;
import com.lqt.duynguyenhairsalon.Model.ServicesDuyNguyenHairSalon;
import com.lqt.duynguyenhairsalon.R;
import com.wefika.flowlayout.FlowLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerBookingActivity extends AppCompatActivity {

    //Params
    private final int REQUEST_CODE = 123;
    private boolean isEmptyService = false;
    private Calendar calendar;
    private String Url = "http://192.168.1.101/DuyNguyenHairSalonWebService/InsertTaskDuyNguyen.php";

    //Views
    private Spinner spinnerDay;
    private List<DayCut> dayCutList;
    private DayCutAdapter dayCutAdapter;
    private ImageView imageViewHome;
    private Button button_SelectService, button_Success;
    private FlowLayout flowLayoutServices;
    private RecyclerView recyclerViewSelectTime;
    private Switch switchChupAnhSauKhiCat;
    private Switch switchYeuCauTuVan;

    //Lists
    private List<ServicesDuyNguyenHairSalon> servicesList = new ArrayList<>();
    private List<BookingTime> bookingTimeList = new ArrayList<>();

    //Adapter
    private SelectTimeAdapter timeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_booking);

        AnhXa();

        ListDay();

        ListTime();

        BookingListen();
    }

    private void ListTime() {
        bookingTimeList.add(new BookingTime("08h30", false));
        bookingTimeList.add(new BookingTime("9h00", false));
        bookingTimeList.add(new BookingTime("9h30", false));
        bookingTimeList.add(new BookingTime("10h00", false));
        bookingTimeList.add(new BookingTime("10h30", true));
        bookingTimeList.add(new BookingTime("11h00", false));
        bookingTimeList.add(new BookingTime("11h30", false));
        bookingTimeList.add(new BookingTime("12h00", false));
        bookingTimeList.add(new BookingTime("12h30", false));
        bookingTimeList.add(new BookingTime("13h00", false));
        bookingTimeList.add(new BookingTime("13h30", false));
        bookingTimeList.add(new BookingTime("14h00", false));

        timeAdapter = new SelectTimeAdapter(this);
        timeAdapter.setData(bookingTimeList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        recyclerViewSelectTime.setLayoutManager(gridLayoutManager);
        recyclerViewSelectTime.setAdapter(timeAdapter);
    }


    /*
     * Do nếu chuyển sang SelectServiceActivity mà người dùng ko
     * chọn bất cứ một dịch vụ nào thì phải kiểm tra lại. (Ẩn list time đi)
     * Đây là kiến thức thuộc Life Activity
     *
     * */
    @Override
    protected void onResume() {
        super.onResume();
        if (servicesList.size() == 0 || servicesList == null) {
            recyclerViewSelectTime.setVisibility(View.GONE);
        } else {
            recyclerViewSelectTime.setVisibility(View.VISIBLE);
        }
    }

    /*
     * Hàm BookingListen sẽ cử lí các sự kiện chính trong BookinkActivity
     * */
    private void BookingListen() {
        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button_SelectService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerBookingActivity.this, SelectServiceActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        button_Success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
//                Gson gson = new Gson();
//                String dataService = gson.toJson(servicesList);
//
//                Log.d("Result Booking", dataService
//                        + "\n"
//                        + dayCutList.get(spinnerDay.getSelectedItemPosition()).getStringDayCut()
//                        + "\n"
//                        + bookingTimeList.get(timeAdapter.getPositionSelceted()).getmTime());
                if(servicesList.size() > 0 && timeAdapter.getPositionSelceted() != 0){
                    InsertData(Url);
                }
            }
        });
    }

    private void InsertData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString().equals("successful")){
                    Toast.makeText(CustomerBookingActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(CustomerBookingActivity.this, "Lỗi! Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error insert", error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<>();

                param.put("Date_Task","2021-05-18 09:30:00");

                int sum = 0;
                for (int i = 0; i<servicesList.size();i++) {
                    sum += servicesList.get(i).getPriceService();
                }
                param.put("Sum_Money_Task",""+sum);


                param.put("Is_Save_Photo",""+ (switchChupAnhSauKhiCat.isChecked()?1:0));
                param.put("Is_Consulting",""+ (switchYeuCauTuVan.isChecked()?1:0));

                return param;
            }
        };

        requestQueue.add(stringRequest);
    }

    /*
     * Xử lí spinner Day
     * */
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
            dayCutList.add(new DayCut(dayOfWeek + " (" + calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + ")"));
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
                textViewService.setBackgroundResource(R.drawable.background_border_black);
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
            Type type = new TypeToken<List<ServicesDuyNguyenHairSalon>>() {
            }.getType();
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
        recyclerViewSelectTime = (RecyclerView) findViewById(R.id.recyclerView_SelectTime);
        switchChupAnhSauKhiCat = (Switch) findViewById(R.id.swich_ChupAnhSauKhiCat);
        switchYeuCauTuVan = (Switch) findViewById(R.id.swich_YeuCauTuVan);
    }
}