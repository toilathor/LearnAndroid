package com.lqt.duynguyenhairsalon.Fragments.MainApp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lqt.duynguyenhairsalon.Activities.Login.CustomerDetailActivity;
import com.lqt.duynguyenhairsalon.Activities.HistoryCutActivity;
import com.lqt.duynguyenhairsalon.Activities.Other.LocationActivity;
import com.lqt.duynguyenhairsalon.Activities.MemberActivity;
import com.lqt.duynguyenhairsalon.Activities.RewardsActivity;
import com.lqt.duynguyenhairsalon.R;


public class AccountFragment extends Fragment {

    private View view;
    private ConstraintLayout constraintLayoutEditAccount;
    private CardView cardViewMember, cardViewRewards, cardViewHistory, cardViewLocation, cardViewLogin_Logout;
    private TextView textViewLogin_Logout;
    private ImageView imageViewLogin_Logout;
    private LinearLayout linearLayoutLogin_Logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);

        AnhXa();

        listFragment();
        return view;
    }

    private void listFragment() {
        constraintLayoutEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CustomerDetailActivity.class));
            }
        });

        cardViewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MemberActivity.class));
            }
        });
        cardViewRewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RewardsActivity.class));
            }
        });
        cardViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HistoryCutActivity.class));
            }
        });
        cardViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LocationActivity.class));
            }
        });
        cardViewLogin_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO đăng xuất ra
                Toast.makeText(getContext(), "Chưa hoàn thiện", Toast.LENGTH_SHORT).show();
                if (textViewLogin_Logout.getText() == "Đăng Nhập") {
                    linearLayoutLogin_Logout.setBackgroundColor(Color.rgb(225, 55, 55));

                    textViewLogin_Logout.setText("Đăng Xuất");
                    imageViewLogin_Logout.setImageResource(R.drawable.ic_logout);
                } else {
                    linearLayoutLogin_Logout.setBackgroundColor(Color.rgb(100, 225, 55));
                    textViewLogin_Logout.setText("Đăng Nhập");
                    imageViewLogin_Logout.setImageResource(R.drawable.ic_enter);
                }
            }
        });
    }

    private void AnhXa() {
        constraintLayoutEditAccount = (ConstraintLayout) view.findViewById(R.id.constraintLayout_EditAccount);
        cardViewMember = (CardView) view.findViewById(R.id.cardView_Member);
        cardViewRewards = (CardView) view.findViewById(R.id.cardView_Rewards);
        cardViewHistory = (CardView) view.findViewById(R.id.cardView_History);
        cardViewLocation = (CardView) view.findViewById(R.id.cardView_Location);

        cardViewLogin_Logout = (CardView) view.findViewById(R.id.cardView_Login_Logout);
        linearLayoutLogin_Logout = (LinearLayout) view.findViewById(R.id.linearLayout_Login_Logout);
        textViewLogin_Logout = (TextView) view.findViewById(R.id.textView_Login_Logout);
        imageViewLogin_Logout = (ImageView) view.findViewById(R.id.imageView_Login_Logout);
    }
}