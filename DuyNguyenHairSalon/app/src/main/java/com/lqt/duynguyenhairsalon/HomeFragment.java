package com.lqt.duynguyenhairsalon;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.lqt.duynguyenhairsalon.Model.DuyNguyenTV;
import com.lqt.duynguyenhairsalon.Model.DuyNguyenTVAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View view;
    private ViewFlipper viewFlipper_Demo;
    private Context context;
    private RecyclerView recyclerViewDuyNguyenTV;
    private DuyNguyenTVAdapter duyNguyenTVAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();

        Toast.makeText(getActivity(), "Load Loại 1", Toast.LENGTH_SHORT).show();

        AnhXa();

        setSlider();

        setRecyclerview();

        return view;
    }

    private void setRecyclerview() {
        List<DuyNguyenTV> duyNguyenTVS = new ArrayList<>();

        duyNguyenTVS.add(new DuyNguyenTV(R.drawable.demo1));
        duyNguyenTVS.add(new DuyNguyenTV(R.drawable.demo2));
        duyNguyenTVS.add(new DuyNguyenTV(R.drawable.demo3));
        LinearLayoutManager linearLayoutManagerDuyNguyenTV = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDuyNguyenTV.setLayoutManager(linearLayoutManagerDuyNguyenTV);
        duyNguyenTVAdapter = new DuyNguyenTVAdapter(getContext());
        duyNguyenTVAdapter.setData(duyNguyenTVS);
        recyclerViewDuyNguyenTV.setAdapter(duyNguyenTVAdapter);
    }

    private void setSlider() {
        int images[] = {R.drawable.demo1,R.drawable.demo2,R.drawable.demo3};

        for (int image: images) {
            flipper(image);
        }
    }

    private void AnhXa() {
        viewFlipper_Demo = view.findViewById(R.id.viewFlipper_Demo);
        recyclerViewDuyNguyenTV = view.findViewById(R.id.recyclerviewTV);
    }

    /*
    * Đây là slide ảnh
    * */
    public void flipper(int image){
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(image);

        viewFlipper_Demo.addView(imageView);
        viewFlipper_Demo.setFlipInterval(4000);
        viewFlipper_Demo.setAutoStart(true);
        viewFlipper_Demo.setInAnimation(context ,android.R.anim.slide_out_right);
        viewFlipper_Demo.setInAnimation(context, android.R.anim.slide_in_left);
    }
}