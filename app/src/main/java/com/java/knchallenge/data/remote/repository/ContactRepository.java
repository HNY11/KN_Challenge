package com.java.knchallenge.data.remote.repository;

import android.app.Application;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

import com.java.knchallenge.data.local.ContactDatabase;
import com.java.knchallenge.data.local.dao.ContactDao;
import com.java.knchallenge.data.local.entity.ContactEntity;

import java.util.List;

/**
 * Created on 22-02-2020
 * @author Himanshu Malik
 */
public class ContactRepository {

    private ContactDao mContactDao;
    private LiveData<List<ContactEntity>> mContactList;

    public ContactRepository(Application application) {
        ContactDatabase db = ContactDatabase.getDatabase(application);
        mContactDao = db.contactDao();
        mContactList = mContactDao.getAlphabetizedContacts();
        List<ContactEntity> contactEntities = mContactList.getValue();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    @MainThread
    public LiveData<List<ContactEntity>> getAllContacts() {
        return mContactList;
    }

    @WorkerThread
    public void insert(final List<ContactEntity> contactEntityList) {
        mContactDao.saveAllContacts(contactEntityList);
    }
}
