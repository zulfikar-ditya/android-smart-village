package com.example.user.smartvillage.Modal;

/**
 * Created by user on 03/12/2017.
 */

public class User {
 private String fullname, nik;
    private int id;

    public User(int id, String fullname, String nik) {
        this.fullname = fullname;
        this.nik = nik;
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
