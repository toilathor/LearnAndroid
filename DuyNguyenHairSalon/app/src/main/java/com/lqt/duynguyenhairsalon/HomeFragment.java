package com.lqt.duynguyenhairsalon;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    View view;
    ViewFlipper viewFlipper_Demo;
    Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();

        Toast.makeText(getActivity(), "Load Loại 1", Toast.LENGTH_SHORT).show();

        AnhXa();

        int images[] = {R.drawable.demo1,R.drawable.demo2,R.drawable.demo3};

        for (int image: images) {
            flipper(image);
        }
        return view;
    }

    private void AnhXa() {
        viewFlipper_Demo = view.findViewById(R.id.viewFlipper_Demo);
    }

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