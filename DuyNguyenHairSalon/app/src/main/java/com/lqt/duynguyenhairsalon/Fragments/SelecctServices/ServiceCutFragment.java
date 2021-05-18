package com.lqt.duynguyenhairsalon.Fragments.SelecctServices;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lqt.duynguyenhairsalon.Activities.SelectServiceActivity;
import com.lqt.duynguyenhairsalon.Model.Adapters.SelectServiceAdapter;
import com.lqt.duynguyenhairsalon.Model.ServicesDuyNguyenHairSalon;
import com.lqt.duynguyenhairsalon.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceCutFragment extends Fragment {

    /*
     * android:descendantFocusability="blocksDescendants"
     * phần này phải dùng để nó bắt đk sự kiện nhé.
     * chưa hiểu lắm nhưng thôi kệ đi
     *
     * đặt thêm đoạn này vào checkbox để tránh xung đột
     * android:clickable="false"
     *android:focusable="false"
     *android:focusableInTouchMode="false"
     *https://stackoverflow.com/questions/5551042/onitemclicklistener-not-working-in-listview
     *
     * */
    //View
    private ListView listViewServiceCut;
    private View view;

    //Adapter
    private SelectServiceAdapter adapter;

    //List
    private List<ServicesDuyNguyenHairSalon> servicesList;

    //param
    private SelectServiceActivity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service_cut, container, false);

        AnhXa();

        AddData();

        ServiceCutLister();

        return view;
    }

    private void ServiceCutLister() {

    }

    private void AddData() {
        servicesList = new ArrayList<>();
        servicesList.add(new ServicesDuyNguyenHairSalon("Combo cắt gội 10 bước"
                , "Combo Cắt - Gội - Thư giãn 10 bước cơ bản"
                , false
                , 80000));
        servicesList.add(new ServicesDuyNguyenHairSalon("Cắt xả"
                , "Cắt xả nhanh không gội, massage. Tiết kiệm thời gian"
                , false
                , 70000));
        servicesList.add(new ServicesDuyNguyenHairSalon("Vip combo cắt gội"
                , "Combo 10 bước kèm các dịch vụ chăm sóc grooming cao cấp"
                , false
                , 199000));
        servicesList.add(new ServicesDuyNguyenHairSalon("Kid combo"
                , "Combo cắt gội riêng cho bé (mỹ phẩm riêng cho trẻ em)"
                , false
                , 70000));
        servicesList.add(new ServicesDuyNguyenHairSalon("Gội massage dưỡng sinh vuốt tạo kiểu"
                , "Áp dụng Y học cổ truyền, bấm huyệt chữa mỏi vai gáy"
                , false
                , 40000));
        adapter = new SelectServiceAdapter(getContext()
                , R.layout.item_service_duynguyen_hair_salon
                , servicesList);
        listViewServiceCut.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (servicesList.get(position).isSelected()) {
                    servicesList.get(position).setSelected(!servicesList.get(position).isSelected());
                    activity.removeServicesList(servicesList.get(position));
                } else {
                    servicesList.get(position).setSelected(!servicesList.get(position).isSelected());
                    activity.addServicesList(servicesList.get(position));
                }

                adapter.notifyDataSetChanged();
            }
        });
        listViewServiceCut.setAdapter(adapter);
    }

    private void AnhXa() {
        activity = (SelectServiceActivity) getActivity();

        listViewServiceCut = (ListView) view.findViewById(R.id.listView_Service);
    }
}