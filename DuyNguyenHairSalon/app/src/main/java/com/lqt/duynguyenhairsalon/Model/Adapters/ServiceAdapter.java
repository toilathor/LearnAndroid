package com.lqt.duynguyenhairsalon.Model.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.lqt.duynguyenhairsalon.Activities.SelectServiceActivity;
import com.lqt.duynguyenhairsalon.Model.ServicesDuyNguyenHairSalon;
import com.lqt.duynguyenhairsalon.Model.VideoYouTube;
import com.lqt.duynguyenhairsalon.R;

import java.util.List;
import java.util.zip.Inflater;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceHolder> {
    private List<ServicesDuyNguyenHairSalon> listService;
    private SelectServiceActivity activity;

    public ServiceAdapter(SelectServiceActivity activity) {
        this.activity = activity;
    }

    public void setData(List<ServicesDuyNguyenHairSalon> list) {
        this.listService = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_service_duynguyen_hair_salon, parent, false);
        return new ServiceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHolder holder, int position) {
        ServicesDuyNguyenHairSalon service = listService.get(position);
        if (service != null) {
            if (service.isSelected()) {
                holder.checkBoxService.setChecked(true);
            }
            holder.textViewNameService.setText("" + service.getNameService());
            holder.textViewDescriptionService.setText("" + service.getDescriptionService());
            holder.textViewPriceService.setText("" + service.getPriceService() / 1000 + "K");
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listService.get(position).isSelected()) {
                    listService.get(position).setSelected(!listService.get(position).isSelected());
                    activity.removeServicesList(listService.get(position));
                } else {
                    listService.get(position).setSelected(!listService.get(position).isSelected());
                    activity.addServicesList(listService.get(position));
                }

                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listService != null) {
            return listService.size();
        }
        return 0;
    }

    public class ServiceHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBoxService;
        private TextView textViewNameService;
        private TextView textViewDescriptionService;
        private TextView textViewPriceService;
        private LinearLayout linearLayout;
        public ServiceHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxService = (CheckBox) itemView.findViewById(R.id.checkBox_Service);
            textViewNameService = (TextView) itemView.findViewById(R.id.textView_NameService);
            textViewDescriptionService = (TextView) itemView.findViewById(R.id.textView_DescriptionService);
            textViewPriceService = (TextView) itemView.findViewById(R.id.textView_PriceService);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
