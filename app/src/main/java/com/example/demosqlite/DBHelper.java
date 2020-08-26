package com.example.demosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    //Tao bang
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Authors(id_author integer primary key, name_author text, address_author text, email_author text)");
        db.execSQL("create table Books(id_book integer primary key, title text, id_author integer constraint id_author references Authors(id_author) on delete cascade on update cascade)");
    }

    //Kiem tra trung bang
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Authors");
        db.execSQL("drop table if exists Books");
        onCreate(db);
    }

    //Contructer
    public DBHelper(@Nullable Context context) {
        super(context, "dbdemo.sqlite", null, 1);
    }

    //Them du lieu bang Authors
    public int insertAuthors(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id_author", author.getId_author());
        contentValues.put("name_author", author.getName_author());
        contentValues.put("address_author", author.getAddress_author());
        contentValues.put("email_author", author.getEmail_author());

        int result = (int) db.insert("Authors", null, contentValues);
        db.close();

        return result;
    }

    //Them du lieu bang Books
    public int insertBooks(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id_book", book.getId_book());
        contentValues.put("title", book.getTitle());
        contentValues.put("id_author", book.getId_author());

        int result = (int) db.insert("Books", null, contentValues);
        db.close();

        return result;
    }

    //Lay tat ca du lieu bang Authors
    public ArrayList<Author> getAllAuthor() {
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false) {
            list.add(new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();

        db.close();
        return list;
    }

    //Lay tat ca du lieu bang Books
    public ArrayList<Book> getAllBook() {
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Books", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false) {
            list.add(new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();

        db.close();
        return list;
    }

    //Tim kiem Author theo id
    public ArrayList<Author> getIdAuthor(int id) {
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors where id_author =" + id, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false) {
            list.add(new Author(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();

        db.close();
        return list;
    }

    //Tim kiem Book theo id
    public ArrayList<Book> getIdBook(int id) {
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Books where id_book =" + id, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false) {
            list.add(new Book(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            cursor.moveToNext();
        }
        cursor.close();

        db.close();
        return list;
    }

    //Xoa Author theo id
    public boolean deleteAuthor(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db.delete("Authors", "id_author" + "=?", new String[]{String.valueOf(id)}) > 0) {
            db.close();
            return true;
        }
        return false;
    }

    //Xoa Book theo id
    public boolean deleteBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db.delete("Books", "id_book" + "=?", new String[]{String.valueOf(id)}) > 0) {
            db.close();
            return true;
        }
        return false;
    }

    //Chinh sua Author theo id
    public boolean updateAuthor(int id, String name, String address, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_author", id);
        contentValues.put("name_author", name);
        contentValues.put("address_author", address);
        contentValues.put("email_author", email);

        db.update("Authors", contentValues, "id_author =" + id, null);
        return true;
    }

    //Chinh sua Book theo id
    public boolean updateBook(int id_book, String title, int id_author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_book", id_book);
        contentValues.put("title", title);
        contentValues.put("id_author", id_author);

        db.update("Books", contentValues, "id_book =" + id_book, null);
        return true;
    }
}
