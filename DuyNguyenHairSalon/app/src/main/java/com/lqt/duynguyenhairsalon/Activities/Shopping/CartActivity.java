package com.lqt.duynguyenhairsalon.Activities.Shopping;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lqt.duynguyenhairsalon.Model.Adapters.ProductInCartAdapter;
import com.lqt.duynguyenhairsalon.Model.Config;
import com.lqt.duynguyenhairsalon.Model.ProductDuyNguyenHairSalon;
import com.lqt.duynguyenhairsalon.R;
import com.lqt.duynguyenhairsalon.SharedPreferences.DataLocalManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {


    //View
    private RecyclerView recyclerViewProduct;
    private TextView textViewPriceDelivery;
    private TextView textViewSum;
    private TextView textViewSumProduct;
    private CheckBox checkBoxFastShip;
    private Button buttonOrder;
    private ImageView imageViewRowBack;
    private Dialog dialogRequest;
    private Dialog dialogAddress;
    private Dialog dialogChooseADW;
    private TextView textViewDeliveryAddress;

    //List
    private List<ProductDuyNguyenHairSalon> productList;

    //Adapter
    private ProductInCartAdapter productInCartAdapter;

    //Param
    private static final String PROVINCE = "province";
    private static final String DISTRICT = "district";
    private static final String WARD = "ward";
    private static final String TAG = "error";
    private String url = Config.LOCALHOST + "GetCart.php?User_Name=";
    private String UserName;
    private String urlChangeProductToCart = Config.LOCALHOST + "ChangeAmountInCart.php";
    private String urlADW = "https://online-gateway.ghn.vn/shiip/public-api/master-data/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initView();

        SetProductList();

        ListenActivity();
    }

    private void ListenActivity() {
        imageViewRowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textViewDeliveryAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogChooseAddress();
            }
        });
    }

    private void ShowDialogChooseAddress() {
        dialogAddress = new Dialog(this);

        //no titel
        dialogAddress.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set layout
        dialogAddress.setContentView(R.layout.dialog_select_delivery_address);

        //get window
        Window window = dialogAddress.getWindow();

        if (window == null) {
            return;
        }

        //set layout manager
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //set vị trí xuất hiện
        window.setGravity(Gravity.CENTER);

        //chạm ra ngoài không bị close
        dialogAddress.setCanceledOnTouchOutside(false);

        TextView textViewName = (TextView) dialogAddress.findViewById(R.id.textView_Name);
        TextView textViewPhoneNumber = (TextView) dialogAddress.findViewById(R.id.textView_PhoneNumber);
        TextView textViewProvince = (TextView) dialogAddress.findViewById(R.id.textView_Province);
        TextView textViewDistrict = (TextView) dialogAddress.findViewById(R.id.textView_District);
        TextView textViewWard = (TextView) dialogAddress.findViewById(R.id.textView_Ward);
        Button buttonConfirm = (Button) dialogAddress.findViewById(R.id.button_Confirm);
        ImageView imageViewClose = (ImageView) dialogAddress.findViewById(R.id.imageView_Close);

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddress.dismiss();
            }
        });

        textViewProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogChooseADW(PROVINCE);
            }
        });

        dialogAddress.show();
    }

    private void ShowDialogChooseADW(String leverAddress) {
        dialogChooseADW = new Dialog(this);

        //set no title
        dialogChooseADW.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set layout
        dialogChooseADW.setContentView(R.layout.dialog_choose_adw);

        //get window
        Window window = dialogChooseADW.getWindow();
        if (window == null) {
            return;
        }

        //set layout manager
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //set Vị trí hiển thị
        window.setGravity(Gravity.CENTER);

        //Chạm ra ngoài
        dialogChooseADW.setCanceledOnTouchOutside(false);

        ListView listViewADW = (ListView) dialogChooseADW.findViewById(R.id.listView_ADW);

        List<String> listADW = new ArrayList<>();
        SetListADW(listADW, leverAddress);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listADW);
        listViewADW.setAdapter(arrayAdapter);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Collections.sort(listADW, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });

                dialogChooseADW.show();
            }
        }, 500);
    }

    private void RequestDelete(ProductDuyNguyenHairSalon product) {
        dialogRequest = new Dialog(this);

        //không có title
        dialogRequest.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //SetLayout
        dialogRequest.setContentView(R.layout.diaglog_request_successful);

        Window window = dialogRequest.getWindow();
        if (window == null) {
            return;
        }

        //Xét LayoutParams
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Vị trí hiển thị
        window.setGravity(Gravity.CENTER);

        //Bắt sự kiện nhấn ra ngoài vùng
        dialogRequest.setCanceledOnTouchOutside(false);

        //Ánh xạ các thứ ở dưới này
        Button buttonCancel = (Button) dialogRequest.findViewById(R.id.buttonCancel);
        Button buttonConfirm = (Button) dialogRequest.findViewById(R.id.button_Confirm);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogRequest.dismiss();
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductChange(product, ProductInCartAdapter.IClickChangeProductListener.DELETE);
                dialogRequest.dismiss();
            }
        });

        //show
        dialogRequest.show();
    }

    private void SetListADW(List<String> listADW, String leverAddress) {
        if (listADW == null) {
            return;
        }

        RequestQueue requestQueue = Volley.newRequestQueue(CartActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlADW + leverAddress, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject data = array.getJSONObject(i);
                        listADW.add(data.getString("ProvinceName"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("token", getString(R.string.token_ghn));
                return param;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }

    private void SetProductList() {
        getDataProduct();

        productInCartAdapter = new ProductInCartAdapter(this);

        productInCartAdapter.setData(productList, new ProductInCartAdapter.IClickChangeProductListener() {
            @Override
            public void onClickUpProduct(ProductDuyNguyenHairSalon product) {
                ProductChange(product, UP);
            }

            @Override
            public void onClickDownProduct(ProductDuyNguyenHairSalon product) {
                if (product.getAmount_Product() == 1) {
                    RequestDelete(product);
                } else {
                    ProductChange(product, DOWN);
                }
            }

            @Override
            public void onClickDeleteProduct(ProductDuyNguyenHairSalon product) {
                RequestDelete(product);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewProduct.setLayoutManager(layoutManager);

        recyclerViewProduct.setAdapter(productInCartAdapter);
    }

    private void ProductChange(ProductDuyNguyenHairSalon product, String change) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlChangeProductToCart
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("successful")) {
                    if (change.equals(ProductInCartAdapter.IClickChangeProductListener.UP)) {
                        product.setAmount_Product(product.getAmount_Product() + 1);
                        productInCartAdapter.notifyDataSetChanged();
                    } else if (change.equals(ProductInCartAdapter.IClickChangeProductListener.DOWN)) {
                        product.setAmount_Product(product.getAmount_Product() - 1);
                        productInCartAdapter.notifyDataSetChanged();
                    } else {
                        productList.remove(product);
                        productInCartAdapter.notifyDataSetChanged();
                        Toast.makeText(CartActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                    }

                    LoadAllPrice();
                } else {
                    Log.e(TAG, response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();

                param.put("Change", change);
                param.put("User_Name", DataLocalManager.getPrefUserName());

                param.put("ID_Product", "" + product.getID_Product());

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getDataProduct() {
        productList = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url + UserName
                , null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject product = response.getJSONObject(i);
                        productList.add(new ProductDuyNguyenHairSalon(product.getInt("ID_Product")
                                , product.getString("Name_Product")
                                , product.getInt("Price_Product")
                                , product.getInt("Amount_Product")
                                , ""
                                , ""
                                , ""
                                , product.getString("Image_Product")
                                , 1
                                , ""));
                        productInCartAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                LoadAllPrice();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });
        requestQueue.add(arrayRequest);
    }

    private void LoadAllPrice() {
        int sumProduct = 0;
        for (int i = 0; i < productList.size(); i++) {
            sumProduct += (productList.get(i).getAmount_Product() * productList.get(i).getPrice_Product());
        }

        if (sumProduct >= 500000) {
            textViewPriceDelivery.setText(R.string.free_ship);
            textViewSum.setText(sumProduct / 1000 + ".000đ");
        } else {
            textViewPriceDelivery.setText(R.string.charge_ship);
            textViewSum.setText((sumProduct + 30000) / 1000 + ".000đ");
        }

        textViewSumProduct.setText(sumProduct / 1000 + ".000đ");

    }

    private void initView() {
        recyclerViewProduct = (RecyclerView) findViewById(R.id.recyclerView_ProductInCart);
        textViewPriceDelivery = (TextView) findViewById(R.id.textView_PriceDelivery);
        textViewSumProduct = (TextView) findViewById(R.id.textView_SumProduct);
        textViewSum = (TextView) findViewById(R.id.textView_Sum);
        checkBoxFastShip = (CheckBox) findViewById(R.id.checkBox_FastShip);
        buttonOrder = (Button) findViewById(R.id.button_Order);
        imageViewRowBack = (ImageView) findViewById(R.id.imageView_RowBack);
        textViewDeliveryAddress = (TextView) findViewById(R.id.textView_DeliveryAddress);

        char[] chars = new char[15];
        DataLocalManager.getPrefUserName().getChars(1, 12, chars, 0);
        UserName = String.valueOf(chars);
    }
}