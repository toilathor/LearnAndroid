package com.lqt.duynguyenhairsalon.Fragments.AdminBoooking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.lqt.duynguyenhairsalon.Model.Adapters.TaskAdapter;
import com.lqt.duynguyenhairsalon.Model.User;
import com.lqt.duynguyenhairsalon.Model.mTask;
import com.lqt.duynguyenhairsalon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UnsuccessfulFragment extends Fragment {

    //View
    RecyclerView recyclerViewUnsuccessful;
    View view;

    //Adapter
    TaskAdapter taskAdapter;

    //List
    List<mTask> mTaskList;

    //Param
    String data = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_unsuccessful, container, false);

        AnhXa();



        /*
         * https://viblo.asia/p/giai-quyet-rac-roi-khi-su-sung-cleartext-tren-android-pie-maGK7DLeZj2
         * phải thay đổi trong manifest giá trị usesCleartextTraffic = true; để truy cập địa chỉ http//
         *
         * */
        LoadListTask("http://192.168.1.101/DuyNguyenHairSalonWebService/GetDataDuyNguyen.php");

        SetRecyclerView();

        return view;
    }

    private void LoadListTask(String url) {
        mTaskList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET
                , url
                , null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject task = response.getJSONObject(i);
                        JSONArray service = task.getJSONArray("Array_Service");

                        List<String> listService = new ArrayList<>();
                        for (int j = 0; j < service.length(); j++) {
                            listService.add(service.getJSONObject(j).getString("Name_Service"));
                        }

                        mTaskList.add(new mTask(task.getInt("ID_Task")
                                , task.getString("Date_Task")
                                , task.getInt("Sum_Money_Task")
                                , task.getInt("Is_Save_Photo")
                                , task.getInt("Is_Consulting")
                                , task.getInt("Is_Successful_Task")
                                , new User(task.getInt("ID_User")
                                , task.getString("Name_User")
                                , task.getString("Phone_Number_User"))
                                , listService));
                        taskAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void SetRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL
                , false);
        recyclerViewUnsuccessful.setLayoutManager(linearLayoutManager);
        taskAdapter = new TaskAdapter(getActivity(), false);
        taskAdapter.setData(mTaskList);
        recyclerViewUnsuccessful.setAdapter(taskAdapter);
    }

    private void AnhXa() {
        recyclerViewUnsuccessful = (RecyclerView) view.findViewById(R.id.recycleView_TaskUnsuccessful);
    }
}