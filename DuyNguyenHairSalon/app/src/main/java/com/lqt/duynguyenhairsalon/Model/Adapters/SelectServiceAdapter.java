package com.lqt.duynguyenhairsalon.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.lqt.duynguyenhairsalon.Model.ServicesDuyNguyenHairSalon;
import com.lqt.duynguyenhairsalon.R;

import java.util.List;
import java.util.zip.Inflater;

public class SelectServiceAdapter extends ArrayAdapter {
    public SelectServiceAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_service_duynguyen_hair_salon, parent, false);

        CheckBox checkBoxService = (CheckBox) convertView.findViewById(R.id.checkBox_Service);
        TextView textViewNameService = (TextView) convertView.findViewById(R.id.textView_NameService);
        TextView textViewDescriptionService = (TextView) convertView.findViewById(R.id.textView_DescriptionService);
        TextView textViewPriceService = (TextView) convertView.findViewById(R.id.textView_PriceService);
        ServicesDuyNguyenHairSalon service = (ServicesDuyNguyenHairSalon) this.getItem(position);
        if (service != null) {
            if (service.isSelected()) {
                checkBoxService.setChecked(true);
            }
            textViewNameService.setText("" + service.getNameService());
            textViewDescriptionService.setText("" + service.getDescriptionService());
            textViewPriceService.setText("" + service.getPriceService() / 1000 + "K");
        }

        return convertView;
    }


}
