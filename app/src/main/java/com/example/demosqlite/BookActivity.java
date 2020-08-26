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

public class BookActivity extends AppCompatActivity {
    private EditText edt_ID_book, edt_Title_book, edt_Author_book;
    private Button btn_Exit_book, btn_Save_book, btn_Select_book, btn_Delete_book, btn_Update_book;
    private GridView gridView_book;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        dbHelper = new DBHelper(this);

        AnhXa();
        Thoat();
        Them();
        Chon();
    }

    public void AnhXa() {
        edt_ID_book = (EditText) findViewById(R.id.edt_ID_book);
        edt_Title_book = (EditText) findViewById(R.id.edt_Title_book);
        edt_Author_book = (EditText) findViewById(R.id.edt_Author_book);

        btn_Select_book = (Button) findViewById(R.id.btn_Select_book);
        btn_Save_book = (Button) findViewById(R.id.btn_Save_book);
        btn_Delete_book = (Button) findViewById(R.id.btn_Delete_book);
        btn_Update_book = (Button) findViewById(R.id.bt_Update_book);
        btn_Exit_book = (Button) findViewById(R.id.btn_Exit_book);

        gridView_book = (GridView) findViewById(R.id.gridView_book);
    }

    public void Thoat() {
        btn_Exit_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Them() {
        btn_Save_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setId_book(Integer.parseInt(edt_ID_book.getText().toString()));
                book.setTitle(edt_Title_book.getText().toString());
                book.setId_author(Integer.parseInt(edt_Author_book.getText().toString()));

                if (dbHelper.insertBooks(book) > 0) {
                    Toast.makeText(BookActivity.this, "Luu thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BookActivity.this, "Luu khong thanh cong", Toast.LENGTH_SHORT).show();
                }
                edt_ID_book.setText("");
                edt_Title_book.setText("");
                edt_Author_book.setText("");
            }
        });
    }

    public void Chon() {
        btn_Select_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Book> list_Book = new ArrayList<>();
                ArrayList<String> list_String = new ArrayList<>();

                if (edt_ID_book.getText().toString().equals("")) {
                    list_Book = dbHelper.getAllBook();
                } else {
                    list_Book = dbHelper.getIdBook(Integer.parseInt(edt_ID_book.getText().toString()));
                }

                if (list_Book.size() > 0 ) {
                    for (Book book : list_Book) {
                        list_String.add(book.getId_book() + "");
                        list_String.add(book.getTitle());
                        list_String.add(book.getId_author() + "");
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BookActivity.this, android.R.layout.simple_list_item_1, list_String);
                    gridView_book.setAdapter(arrayAdapter);
                } else {
                    Toast.makeText(BookActivity.this, "Co so du lieu rong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}