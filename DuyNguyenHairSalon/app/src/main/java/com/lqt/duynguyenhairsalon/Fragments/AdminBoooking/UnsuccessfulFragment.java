package com.lqt.duynguyenhairsalon.Fragments.AdminBoooking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lqt.duynguyenhairsalon.Model.Adapters.TaskAdapter;
import com.lqt.duynguyenhairsalon.Model.mTask;
import com.lqt.duynguyenhairsalon.R;

import java.util.ArrayList;
import java.util.List;

public class UnsuccessfulFragment extends Fragment {

    //View
    RecyclerView recyclerViewUnsuccessful;
    View view;

    //Adapter
    TaskAdapter taskAdapter;

    //List
    List<mTask> mTaskList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_unsuccessful, container, false);

        AnhXa();

        SetRecyclerView();

        return view;
    }

    private void SetRecyclerView() {
        mTaskList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mTaskList.add(new mTask("Lê Quang Thọ", "0973***208"));
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL
                , false);
        recyclerViewUnsuccessful.setLayoutManager(linearLayoutManager);
        taskAdapter = new TaskAdapter(getActivity());
        taskAdapter.setData(mTaskList);
        recyclerViewUnsuccessful.setAdapter(taskAdapter);
    }

    private void AnhXa() {
        recyclerViewUnsuccessful = (RecyclerView) view.findViewById(R.id.recycleView_TaskUnsuccessful);
    }
}