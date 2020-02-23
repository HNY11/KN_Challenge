package com.java.knchallenge.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.java.knchallenge.R;
import com.java.knchallenge.data.local.entity.ContactEntity;

import java.util.List;

/**
 * Created on 21-02-2020
 * @author Himanshu Malik
 */
public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ContactsViewHolder> {

    private List<ContactEntity> contactList;
    private ContactListCallBack contactListCallBack;

    public ContactsRecyclerAdapter(ContactListCallBack contactListCallBack) {
        this.contactListCallBack = contactListCallBack;
    }

    public void setData(List<ContactEntity> contactList) {
        this.contactList = contactList;
    }

    public void clearList() {
        if (contactList != null) contactList.clear();
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        if (contactList != null) {
            ContactEntity contact = contactList.get(position);
            holder.txtContact.setText(contact.getName());
            holder.txtLetter.setText(String.valueOf(contact.getName().charAt(0)));
        }
    }

    @Override
    public int getItemCount() {
        if(contactList != null) return contactList.size();
        else return 0;
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtContact, txtLetter;

        ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtContact = itemView.findViewById(R.id.text_contact);
            txtLetter = itemView.findViewById(R.id.text_letter);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (contactListCallBack != null) {
                contactListCallBack.onContactClicked(contactList.get(getAdapterPosition()));
            }
        }
    }

    public interface ContactListCallBack {
        void onContactClicked(ContactEntity contactEntity);
    }

}
