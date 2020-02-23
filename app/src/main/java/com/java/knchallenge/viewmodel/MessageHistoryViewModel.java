package com.java.knchallenge.viewmodel;

import android.app.Application;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.java.knchallenge.data.local.entity.MessageHistoryEntity;
import com.java.knchallenge.data.local.repository.MessageHistoryRepository;

import java.util.List;

/**
 * Created By Himanshu on 23-02-2020
 */
public class MessageHistoryViewModel extends AndroidViewModel {

    private MessageHistoryRepository mRepository;

    private MutableLiveData<List<MessageHistoryEntity>> messageHistory =
            new MutableLiveData<>();

    MessageHistoryViewModel(Application application) {
        super(application);
        mRepository = new MessageHistoryRepository(application);
    }

    @MainThread
    public LiveData<List<MessageHistoryEntity>> getMessageHistory(){
        return mRepository.getMessageHistory();
    }

    public void insert(MessageHistoryEntity messageHistoryEntity){
        mRepository.insert(messageHistoryEntity);
    }
}
