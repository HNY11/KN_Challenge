package com.java.knchallenge.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/* ViewModel factory class which keeps all the viewmodel instances
* Created on 22-02-2020
* @author Himanshu Malik
* */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public ViewModelFactory(Application application){

        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(ContactListViewModel.class)) {

            return (T) new ContactListViewModel(application);

        }else if (modelClass.isAssignableFrom(MessageHistoryViewModel.class)){
            return (T) new MessageHistoryViewModel(application);
        } else {

          return null;
        }
    }
}
