package com.example.task.models;

import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "user")
public class UserDetails {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDetails(){

    }
    public UserDetails(String first_Name, String last_Name, String mobile_No, String email, String password){
        this.First_Name=first_Name;
        this.Last_Name=last_Name;
        this.Mobile_No=mobile_No;
        this.Email=email;
        this.Password=password;
    }

    @ColumnInfo(name = "First_Name")
    String First_Name;
    @ColumnInfo(name = "Last_Name")
    String Last_Name;
    @ColumnInfo(name = "Mobile_No")
    String Mobile_No;
    @ColumnInfo(name = "Email")
    String Email;
    @ColumnInfo(name = "Password")
    String Password;

   /* public UserDetails(String first_Name, String last_Name, String mobile_No, String email, String password) {
        First_Name = first_Name;
        Last_Name = last_Name;
        Mobile_No = mobile_No;
        Email = email;
        Password = password;
    }*/

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getMobile_No() {
        return Mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        Mobile_No = mobile_No;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
