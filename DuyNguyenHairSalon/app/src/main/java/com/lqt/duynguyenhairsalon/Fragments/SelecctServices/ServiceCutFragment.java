package com.lqt.duynguyenhairsalon.Fragments.SelecctServices;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.lqt.duynguyenhairsalon.Model.Adapters.SelectServiceAdapter;
import com.lqt.duynguyenhairsalon.Model.ServicesDuyNguyenHairSalon;
import com.lqt.duynguyenhairsalon.R;

import java.util.ArrayList;
import java.util.List;

public class ServiceCutFragment extends Fragment {

    private ListView listViewServiceCut;
    private SelectServiceAdapter adapter;
    private List<ServicesDuyNguyenHairSalon> servicesList;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service_cut, container, false);

        AnhXa();

        AddData();

        return view;
    }

    private void AddData() {
        servicesList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            servicesList.add(new ServicesDuyNguyenHairSalon("Dịch vụ này"
                    , "Cắt tóc thôi chắc là không có ngoáy mũi đâu bạn."
                    , false
                    , 199000));
        }
        adapter = new SelectServiceAdapter(getContext()
                , R.layout.item_service_duynguyen_hair_salon
                , servicesList);
        listViewServiceCut.setAdapter(adapter);
    }

    private void AnhXa() {
        listViewServiceCut = (ListView) view.findViewById(R.id.listView_Service);
    }
}