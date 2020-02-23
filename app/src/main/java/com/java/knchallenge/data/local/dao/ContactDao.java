package com.java.knchallenge.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.java.knchallenge.data.local.entity.ContactEntity;
import java.util.List;

/**
 * Created on 22-02-2020
 * @author Himanshu Malik
 */
@Dao
public interface ContactDao {
    @Query("SELECT * from contacts ORDER BY name ASC")
    LiveData<List<ContactEntity>> getAlphabetizedContacts();

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveAllContacts(List<ContactEntity> contactEntities);
}
