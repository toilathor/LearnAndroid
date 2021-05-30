package com.lqt.duynguyenhairsalon.Fragments.MainApp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lqt.duynguyenhairsalon.Model.Adapters.ProductAdapter;
import com.lqt.duynguyenhairsalon.Model.ProductDuyNguyenHairSalon;
import com.lqt.duynguyenhairsalon.R;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment {

    //View 
    private View view;
    private RecyclerView recyclerViewProductNew;

    //List
    private List<ProductDuyNguyenHairSalon> productNewList;

    //Adapter
    private ProductAdapter productNewAdapter;

    //Param

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_store, container, false);

        AnhXa();

        TestNewProduct();

        return view;
    }

    private void TestNewProduct() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewProductNew.setLayoutManager(layoutManager);

        productNewList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            productNewList.add(new ProductDuyNguyenHairSalon(1
                    , "Sản phẩm này tên rất dài nhé tất cả mọi người"
                    , 199000
                    , 1000
                    , 1));

        }

        productNewAdapter = new ProductAdapter(getContext());
        productNewAdapter.setData(productNewList);

        recyclerViewProductNew.setAdapter(productNewAdapter);
    }


    private void AnhXa() {
        recyclerViewProductNew = (RecyclerView) view.findViewById(R.id.recyclerView_SanPhamMoi);
    }
}