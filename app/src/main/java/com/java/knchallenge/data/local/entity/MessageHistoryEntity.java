package com.java.knchallenge.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created By Himanshu on 23-02-2020
 */
@Entity(tableName = "message_history")
public class MessageHistoryEntity {

    private String name;

    private int otp;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "created_at")
    private long createdAt;

    public MessageHistoryEntity(String name, int otp, long createdAt) {
        this.name = name;
        this.otp = otp;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public int getOtp() {
        return otp;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
