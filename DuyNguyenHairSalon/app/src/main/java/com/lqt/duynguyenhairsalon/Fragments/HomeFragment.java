package com.lqt.duynguyenhairsalon.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.lqt.duynguyenhairsalon.Activities.DuyNguyenTVActivity;
import com.lqt.duynguyenhairsalon.Activities.MainActivity;
import com.lqt.duynguyenhairsalon.Activities.PlayVideoYouTubeActivity;
import com.lqt.duynguyenhairsalon.Model.DuyNguyenTV;
import com.lqt.duynguyenhairsalon.Model.DuyNguyenTVAdapter;
import com.lqt.duynguyenhairsalon.Model.SystemHelper;
import com.lqt.duynguyenhairsalon.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    /*
     * Đối với fragment
     * thì mình chọn cách gán thẳng 1 tk context
     * sau đó sử dụng cho nhanh
     * */
    private View view;
    private ViewFlipper viewFlipper_Demo;
    private Context context;
    private RecyclerView recyclerViewDuyNguyenTV;
    private DuyNguyenTVAdapter duyNguyenTVAdapter;
    private SystemHelper systemHelper;
    private Button buttonCall;
    private TextView textViewXemThemTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        context = view.getContext();

        systemHelper = new SystemHelper(context);

        AnhXa();

        setSlider();

        setRecyclerview();

        setDataLookBook();

        setListenFragment();

        return view;
    }


    /*
     * Hàm này sẽ chứa sự kiện ở trong fragment này
     * */
    private void setListenFragment() {
        //listen button call
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPermisstion();
            }
        });

        textViewXemThemTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DuyNguyenTVActivity.class);
                startActivity(intent);
            }
        });
    }

    private void callPermisstion() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:0973271208"));
                startActivity(intent);
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(context, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(context)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check();
    }


    /*
     * hàm này chỉ để thử add view động
     * sau này có cơ sở dữ liệu sẽ làm chuẩn hơn.
     * */
    private void setDataLookBook() {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutDuyNguyenLookBook);


        //Vì là set code động nên ta phải tạo params

        LinearLayout.LayoutParams linearLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams cardViewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , systemHelper.dpToPx(230));

        linearLayoutParam.setMargins(systemHelper.dpToPx(10), systemHelper.dpToPx(10), systemHelper.dpToPx(10), 0);


        for (int i = 0; i < 3; i++) {
            CardView cardView = new CardView(context);
            ImageView imageView = new ImageView(context);
            //set LayoutParams
            cardView.setLayoutParams(linearLayoutParam);
            imageView.setLayoutParams(cardViewLayoutParams);

            cardView.setRadius(systemHelper.dpToPx(12));

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(R.drawable.demo2);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), PlayVideoYouTubeActivity.class);
                    intent.putExtra("ID_VIDEO", "BsXsoKnT0Ro");
                    startActivity(intent);
                }
            });

            //add lần lượt các view vào
            cardView.addView(imageView);
            linearLayout.addView(cardView);
        }

    }

    /*
     * set data cho recyclerview
     * */
    private void setRecyclerview() {
        List<DuyNguyenTV> duyNguyenTVS = new ArrayList<>();

        duyNguyenTVS.add(new DuyNguyenTV(R.drawable.demo1));
        duyNguyenTVS.add(new DuyNguyenTV(R.drawable.demo2));
        duyNguyenTVS.add(new DuyNguyenTV(R.drawable.demo3));
        LinearLayoutManager linearLayoutManagerDuyNguyenTV = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDuyNguyenTV.setLayoutManager(linearLayoutManagerDuyNguyenTV);
        duyNguyenTVAdapter = new DuyNguyenTVAdapter(getContext());
        duyNguyenTVAdapter.setData(duyNguyenTVS);
        recyclerViewDuyNguyenTV.setAdapter(duyNguyenTVAdapter);
    }

    /*
     * set data cho slider
     * */
    private void setSlider() {
        int images[] = {R.drawable.demo1, R.drawable.demo2, R.drawable.demo3};

        for (int image : images) {
            flipper(image);
        }
    }

    /*
     * Đây là slide ảnh
     * */
    public void flipper(int image) {
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(image);

        viewFlipper_Demo.addView(imageView);
        viewFlipper_Demo.setFlipInterval(4000);
        viewFlipper_Demo.setAutoStart(true);
        viewFlipper_Demo.setInAnimation(context, android.R.anim.slide_out_right);
        viewFlipper_Demo.setInAnimation(context, android.R.anim.slide_in_left);
    }


    /*
     * Ánh xạ thông thương
     * */
    private void AnhXa() {
        viewFlipper_Demo = view.findViewById(R.id.viewFlipper_Demo);
        recyclerViewDuyNguyenTV = view.findViewById(R.id.recyclerviewTV);
        buttonCall = (Button) view.findViewById(R.id.button_Call);
        textViewXemThemTV = (TextView) view.findViewById(R.id.textView_XemThemTV);
    }
}