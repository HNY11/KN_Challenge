package com.java.knchallenge.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created By Himanshu on 22-02-2020
 */
@Entity(tableName = "contacts")
public class ContactEntity {

    @SerializedName("name")
    private String name;

    @PrimaryKey
    @NonNull
    @SerializedName("mobile_number")
    private String mobile_number;

    public String getName() {
        return name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
}
