package com.example.demosqlite;

public class Author {
    private int id_author;
    private String name_author, address_author, email_author;


    public Author(int id_author, String name_author, String address_author, String email_author) {
        this.id_author = id_author;
        this.name_author = name_author;
        this.address_author = address_author;
        this.email_author = email_author;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getName_author() {
        return name_author;
    }

    public void setName_author(String name_author) {
        this.name_author = name_author;
    }

    public String getAddress_author() {
        return address_author;
    }

    public void setAddress_author(String address_author) {
        this.address_author = address_author;
    }

    public String getEmail_author() {
        return email_author;
    }

    public void setEmail_author(String email_author) {
        this.email_author = email_author;
    }

    public Author() {
        this.id_author = 0;
        this.name_author = null;
        this.address_author = null;
        this.email_author = null;
    }
}
