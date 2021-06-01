package com.lqt.duynguyenhairsalon.Fragments.MainApp;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.lqt.duynguyenhairsalon.Model.Adapters.ProductAdapter;
import com.lqt.duynguyenhairsalon.Model.ProductDuyNguyenHairSalon;
import com.lqt.duynguyenhairsalon.R;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment {

    //View 
    private View view;
    private RecyclerView recyclerViewHotProduct;
    private RecyclerView recyclerViewNewProduct;
    private RecyclerView recyclerViewTopProduct;
    private RecyclerView recyclerViewSpeciesProduct;
    private TextView textViewOtherHotProduct;
    private TextView textViewOtherNewProduct;
    private TextView textViewOtherTopProduct;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewFlipper viewFlipper;


    //List
    private List<ProductDuyNguyenHairSalon> hotProductList;
    private List<ProductDuyNguyenHairSalon> newProductList;
    private List<ProductDuyNguyenHairSalon> topProductList;

    //Adapter
    private ProductAdapter hotProductAdapter;
    private ProductAdapter newProductAdapter;
    private ProductAdapter topProductAdapter;

    //Param

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_store, container, false);

        AnhXa();

        Slider();

        HotProduct();

        NewProduct();

        TopProduct();

        return view;
    }

    private void TopProduct() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewTopProduct.setLayoutManager(layoutManager);

        topProductList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            topProductList.add(new ProductDuyNguyenHairSalon(1
                    , "Sản phẩm này tên rất dài nhé tất cả mọi người"
                    , 199000
                    , 1000
                    , 1));

        }

        topProductAdapter = new ProductAdapter(getContext());
        topProductAdapter.setData(topProductList);
        recyclerViewTopProduct.setAdapter(topProductAdapter);
    }

    private void HotProduct() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewHotProduct.setLayoutManager(layoutManager);

        hotProductList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            hotProductList.add(new ProductDuyNguyenHairSalon(1
                    , "Sản phẩm này tên rất dài nhé tất cả mọi người"
                    , 199000
                    , 1000
                    , 1));

        }

        hotProductAdapter = new ProductAdapter(getContext());
        hotProductAdapter.setData(hotProductList);

        recyclerViewHotProduct.setAdapter(hotProductAdapter);
    }

    private void NewProduct() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewNewProduct.setLayoutManager(layoutManager);

        newProductList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newProductList.add(new ProductDuyNguyenHairSalon(1
                    , "Sản phẩm này tên rất dài nhé tất cả mọi người"
                    , 199000
                    , 1000
                    , 1));

        }

        newProductAdapter = new ProductAdapter(getContext());
        newProductAdapter.setData(newProductList);

        recyclerViewNewProduct.setAdapter(newProductAdapter);
    }

    private void Slider() {
        int images[] = {R.drawable.banner_shop_1, R.drawable.banner_shop_2};
        for (int image : images) {
            flipper(image);
        }
    }

    /*
     * Đây là slide ảnh
     * */
    public void flipper(int image) {
        ImageView imageView = new ImageView(view.getContext());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(view.getContext(), android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(view.getContext(), android.R.anim.slide_in_left);
    }


    private void AnhXa() {
        recyclerViewHotProduct = (RecyclerView) view.findViewById(R.id.recyclerView_HotProduct);
        recyclerViewNewProduct = (RecyclerView) view.findViewById(R.id.recyclerView_NewProduct);
        recyclerViewTopProduct = (RecyclerView) view.findViewById(R.id.recyclerView_TopProduct);
        recyclerViewSpeciesProduct = (RecyclerView) view.findViewById(R.id.recyclerView_SpeciesProduct);
        textViewOtherHotProduct = (TextView) view.findViewById(R.id.textView_OtherHotProduct);
        textViewOtherNewProduct = (TextView) view.findViewById(R.id.textView_OtherNewProduct);
        textViewOtherTopProduct = (TextView) view.findViewById(R.id.textView_OtherTopProduct);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
    }
}