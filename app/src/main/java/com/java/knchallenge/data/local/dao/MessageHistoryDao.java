package com.java.knchallenge.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.java.knchallenge.data.local.entity.MessageHistoryEntity;

import java.util.List;

/**
 * Created By Himanshu on 23-02-2020
 */
@Dao
public interface MessageHistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(MessageHistoryEntity messageHistoryEntity);

    @Query("SELECT * FROM message_history")
    public LiveData<List<MessageHistoryEntity>> getMessageHistory();
}
