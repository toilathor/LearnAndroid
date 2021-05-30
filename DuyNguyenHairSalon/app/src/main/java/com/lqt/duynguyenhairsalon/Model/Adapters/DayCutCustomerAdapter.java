package com.lqt.duynguyenhairsalon.Model.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lqt.duynguyenhairsalon.Activities.Booking.AdminBookingActivity;
import com.lqt.duynguyenhairsalon.Activities.Booking.CustomerBookingActivity;
import com.lqt.duynguyenhairsalon.Model.DayCut;
import com.lqt.duynguyenhairsalon.R;

import java.util.List;

public class DayCutCustomerAdapter extends ArrayAdapter {

    private String dateSelected = "";
    private int mPosition = 0;
    private Activity activity;

    public DayCutCustomerAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_day, parent, false);
        TextView textViewSelected = (TextView) convertView.findViewById(R.id.textView_SelectedDay);

        DayCut dayCut = (DayCut) this.getItem(position);
        if (dayCut != null){
            textViewSelected.setText("" + dayCut.getStringDayCut());
            dateSelected = dayCut.getDateCut();
            mPosition = position;

            if (activity.getClass() == CustomerBookingActivity.class){
                CustomerBookingActivity customerBookingActivity = (CustomerBookingActivity) activity;
                customerBookingActivity.ListTime();
            }else {
                //TODO
                AdminBookingActivity adminBookingActivity = (AdminBookingActivity) activity;
                adminBookingActivity.SetTabLayout();
            }
        }

        return convertView;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_cut, parent, false);
        TextView textViewDay = (TextView) convertView.findViewById(R.id.textView_Day);

        DayCut dayCut = (DayCut) this.getItem(position);
        if (dayCut != null){
            textViewDay.setText("" + dayCut.getStringDayCut());
        }
        return convertView;
    }

    public void setDateSelected(String dateSelected) {
        this.dateSelected = dateSelected;
    }

    public String getDateSelected() {
        return dateSelected;
    }

    public int getmPosition() {
        return mPosition;
    }
}
