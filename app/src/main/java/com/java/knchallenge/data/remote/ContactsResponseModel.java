package com.java.knchallenge.data.remote;

import com.google.gson.annotations.SerializedName;
import com.java.knchallenge.data.local.entity.ContactEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 21-02-2020
 * @author Himanshu Malik
 */
public class ContactsResponseModel {

    @SerializedName("contacts")
    private List<ContactEntity> contacts = null;

    public List<ContactEntity> getContacts() {
        return contacts;
    }

}