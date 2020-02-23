package com.java.knchallenge.data.local.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.java.knchallenge.data.local.MessageHistoryDatabase;
import com.java.knchallenge.data.local.dao.MessageHistoryDao;
import com.java.knchallenge.data.local.entity.MessageHistoryEntity;

import java.util.List;

/**
 * Created By Himanshu on 23-02-2020
 */
public class MessageHistoryRepository {

    MessageHistoryDao messageHistoryDao;
    LiveData<List<MessageHistoryEntity>> mMessageHistory;

    public MessageHistoryRepository(Application application){
        MessageHistoryDatabase database = MessageHistoryDatabase.getDatabase(application);
        messageHistoryDao=database.messageHistoryDao();
        mMessageHistory = messageHistoryDao.getMessageHistory();
    }

    public LiveData<List<MessageHistoryEntity>> getMessageHistory(){
        return mMessageHistory;
    }

    public void insert(MessageHistoryEntity messageHistoryEntity){
        MessageHistoryDatabase.databaseWriterExecutor.execute(()->{
            messageHistoryDao.insert(messageHistoryEntity);
        });
    }
}
