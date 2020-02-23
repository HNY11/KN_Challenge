package com.java.knchallenge.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.java.knchallenge.data.local.dao.MessageHistoryDao;
import com.java.knchallenge.data.local.entity.MessageHistoryEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created By Himanshu on 23-02-2020
 */
@Database(entities = {MessageHistoryEntity.class}, version = 1, exportSchema = false)
public abstract class MessageHistoryDatabase extends RoomDatabase {

    public abstract MessageHistoryDao messageHistoryDao();

    private static volatile MessageHistoryDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS=4;
    public static final ExecutorService databaseWriterExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MessageHistoryDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (MessageHistoryDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MessageHistoryDatabase.class,"message_history")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
