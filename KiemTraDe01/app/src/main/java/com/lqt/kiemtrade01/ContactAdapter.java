package com.lqt.kiemtrade01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private Context context;
    private List<Contact> contactList;
    private View view;
    private IClickContactRecyclerView iClickContactRecyclerView;

    public interface IClickContactRecyclerView {
        void OnClickItemClick(int position);
    }

    public ContactAdapter(Context context, IClickContactRecyclerView iClickContactRecyclerView) {
        this.context = context;
        this.iClickContactRecyclerView = iClickContactRecyclerView;
    }

    public void setData(List<Contact> contacts) {
        this.contactList = contacts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        if (contact == null) {
            return;
        }

        holder.checkBoxStatus.setChecked(contact.isStatus());
        holder.textViewName.setText("" + contact.getName());
        holder.textViewPhone.setText("" + contact.getPhoneNumber());
        holder.constraintLayoutItemContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickContactRecyclerView.OnClickItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (contactList != null) {
            return contactList.size();
        }
        return 0;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewPhone;
        CheckBox checkBoxStatus;
        ConstraintLayout constraintLayoutItemContact;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textView_Name);
            textViewPhone = (TextView) itemView.findViewById(R.id.textView_Phone);
            checkBoxStatus = (CheckBox) itemView.findViewById(R.id.checkbox_Status);
            constraintLayoutItemContact = (ConstraintLayout) itemView.findViewById(R.id.constraintLayout_ItemContact);

        }
    }
}
