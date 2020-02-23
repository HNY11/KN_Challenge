package com.java.knchallenge.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.java.knchallenge.data.local.entity.ContactEntity;
import com.java.knchallenge.data.remote.repository.ContactRepository;

import java.util.List;

/**
 * Created on 22-02-2020
 * @author Himanshu Malik
 */
public class ContactListViewModel extends AndroidViewModel {

    private ContactRepository mRepository;

    private MutableLiveData<List<ContactEntity>> mAllContacts =
            new MutableLiveData<>();

    ContactListViewModel(Application application) {
        super(application);
        mRepository = new ContactRepository(application);
    }

    @MainThread
    public LiveData<List<ContactEntity>> getAllContacts() {
       return mRepository.getAllContacts();
    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    public void saveAllContacts(final List<ContactEntity> contactEntityList) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                mRepository.insert(contactEntityList);
                return null;
            }
        }.execute();
    }
}
