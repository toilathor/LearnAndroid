package com.lqt.kiemtrade01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button buttonAddContact, buttonDeleteContact;
    private RecyclerView recyclerViewContact;
    private EditText editTextFilter;
    private DataBase dataBase;
    private List<Contact> contactList;
    private ContactAdapter contactAdapter;
    private final static String NAME_DATABASE = "contact.sqlite";
    private final static String NAME_TABLE = "Contact";
    private final static int REQUEST_CODE = 1412;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        setDatabase();

        setRecyclerView();

        setListenerActivity();
    }

    private void setListenerActivity() {
        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddContactActivity.class), REQUEST_CODE);
            }
        });
        
        buttonDeleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setRecyclerView() {
        contactAdapter = new ContactAdapter(this, new ContactAdapter.IClickContactRecyclerView() {
            @Override
            public void OnClickItemClick(int position) {
                contactList.get(position).setStatus(!contactList.get(position).isStatus());
                contactAdapter.notifyDataSetChanged();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewContact.setLayoutManager(layoutManager);

        LoadListContact();
        contactList.add(new Contact(1, "Lê Quang Thọ", "0973271208", false));

        contactAdapter.setData(contactList);
        recyclerViewContact.setAdapter(contactAdapter);
    }

    private void setDatabase() {
        dataBase = new DataBase(this, NAME_DATABASE, null, 1);

        dataBase.Query("Create Table IF NOT EXISTS " + NAME_TABLE +
                "(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Name NVARCHAR(30), " +
                " PhoneNumber NVARCHAR(12)," +
                " Status INTEGER DEFAULT 0)");
    }

    private void LoadListContact() {
        Cursor cursor = dataBase.GetData("Select * from " + NAME_TABLE);
        if (contactList == null) {
            contactList = new ArrayList<>();
        }
        contactList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            boolean status = String.valueOf(cursor.getInt(3)).equals("1");

            contactList.add(new Contact(id, name, phone, status));

            contactAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        buttonAddContact = (Button) findViewById(R.id.button_AddContact);
        buttonDeleteContact = (Button) findViewById(R.id.button_DeleteContact);
        ;
        recyclerViewContact = (RecyclerView) findViewById(R.id.recyclerViewContact);
        editTextFilter = (EditText) findViewById(R.id.editText_Filter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("Name");
            String phone = data.getStringExtra("Phone");
            Log.e("error", name+phone);
            insertContact(name, phone);
        }
    }

    private void insertContact(String name, String phone) {
        String sql = "Insert into " + NAME_TABLE + " values (null, '" +
                name + "', '" +
                phone + "', null)";
        dataBase.Query(sql);
        LoadListContact();
    }
}