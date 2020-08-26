package com.example.demosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity {
    private EditText edt_ID, edt_Name, edt_Address, edt_Email;
    private Button btn_Exit, btn_Save, btn_Select, btn_Delete, btn_Update;
    private GridView gridView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        dbHelper = new DBHelper(this);

        AnhXa();
        Thoat();
        Them();
        Chon();
        Xoa();
        Sua();
    }

    public void AnhXa() {
        edt_ID = (EditText) findViewById(R.id.edt_ID);
        edt_Name = (EditText) findViewById(R.id.edt_Name);
        edt_Address = (EditText) findViewById(R.id.edt_Address);
        edt_Email = (EditText) findViewById(R.id.edt_Email);

        btn_Exit = (Button) findViewById(R.id.btn_Exit);
        btn_Save = (Button) findViewById(R.id.btn_Save);
        btn_Select = (Button) findViewById(R.id.btn_Select);
        btn_Delete = (Button) findViewById(R.id.btn_Delete);
        btn_Update = (Button) findViewById(R.id.bt_Update);

        gridView = (GridView) findViewById(R.id.gridView);
    }

    public void Thoat() {
        btn_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Them() {
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Author author = new Author();
                author.setId_author(Integer.parseInt(edt_ID.getText().toString()));
                author.setName_author(edt_Name.getText().toString());
                author.setAddress_author(edt_Address.getText().toString());
                author.setEmail_author(edt_Email.getText().toString());

                if (dbHelper.insertAuthors(author) > 0) {
                    Toast.makeText(AuthorActivity.this, "Luu thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AuthorActivity.this, "Luu khong thanh cong", Toast.LENGTH_SHORT).show();
                    edt_ID.setText("");
                    edt_Name.setText("");
                    edt_Address.setText("");
                    edt_Email.setText("");
                }
            }
        });
    }

    public void Chon() {
        btn_Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Author> list_Author = new ArrayList<>();
                ArrayList<String> list_String = new ArrayList<>();

                if (edt_ID.getText().toString().equals("")) {
                    list_Author = dbHelper.getAllAuthor();
                } else {
                    list_Author = dbHelper.getIdAuthor(Integer.parseInt(edt_ID.getText().toString()));
                }

                if (list_Author.size() > 0 ) {
                    for (Author author : list_Author) {
                        list_String.add(author.getId_author() + "");
                        list_String.add(author.getName_author());
                        list_String.add(author.getAddress_author());
                        list_String.add(author.getEmail_author());
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AuthorActivity.this, android.R.layout.simple_list_item_1, list_String);
                    gridView.setAdapter(arrayAdapter);
                } else {
                    Toast.makeText(AuthorActivity.this, "Co so du lieu rong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Xoa() {
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edt_ID.getText().toString());
                if (dbHelper.deleteAuthor(id)) {
                    Toast.makeText(AuthorActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AuthorActivity.this, "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Sua() {
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edt_ID.getText().toString());
                String name = edt_Name.getText().toString();
                String address = edt_Address.getText().toString();
                String email = edt_Email.getText().toString();
                if (dbHelper.updateAuthor(id, name, address, email)) {
                    Toast.makeText(AuthorActivity.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AuthorActivity.this, "Sua khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}