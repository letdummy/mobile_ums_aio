package com.sekalisubmit.laundry.data.room.user;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created By Agus Ardi on 24/05/2024.
 */
@Entity(tableName = "client")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String address;
    private String phone;

    public User(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}