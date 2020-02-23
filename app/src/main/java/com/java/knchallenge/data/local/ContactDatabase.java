package com.java.knchallenge.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.java.knchallenge.data.local.dao.ContactDao;
import com.java.knchallenge.data.local.entity.ContactEntity;

/**
 * Created By Himanshu on 22-02-2020
 */
@Database(entities = {ContactEntity.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {

    private static volatile ContactDatabase INSTANCE;

    public abstract ContactDao contactDao();

    public static ContactDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (ContactDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactDatabase.class, "contacts")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
