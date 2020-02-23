package com.java.knchallenge.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.gson.stream.MalformedJsonException;
import com.java.knchallenge.ContactDetailActivity;
import com.java.knchallenge.R;
import com.java.knchallenge.adapters.ContactsRecyclerAdapter;
import com.java.knchallenge.api.ApiClient;
import com.java.knchallenge.api.ApiInterface;
import com.java.knchallenge.data.remote.ContactsResponseModel;
import com.java.knchallenge.data.local.entity.ContactEntity;
import com.java.knchallenge.viewmodel.ContactListViewModel;
import com.java.knchallenge.viewmodel.ViewModelFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * List of All Contacts
 * @author Himanshu Malik
 */
public class AllContactsFragment extends Fragment implements ContactsRecyclerAdapter.ContactListCallBack {

    public static final String USER_NAME="userName";
    public static final String USER_MOBILE="userMobile";

    private Context mContext;
    private ProgressBar progressBar;
    private RecyclerView recyclerViewContacts;
    private ContactsRecyclerAdapter contactsAdapter;
    private List<ContactEntity> contactEntities;
    private ContactListViewModel contactListViewModel;

    public AllContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contactListViewModel = new ViewModelProvider(requireActivity(), new ViewModelFactory(getActivity().getApplication()))
                .get(ContactListViewModel.class);

        initView(view);
        setupContactsRecycler();
        getContactsApi();
    }

    private void initView(View view) {
        progressBar = view.findViewById(R.id.progress_circular);
        recyclerViewContacts = view.findViewById(R.id.recycler_contacts);
    }

    private void setupContactsRecycler() {
        recyclerViewContacts.setHasFixedSize(true);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(mContext));
        contactsAdapter = new ContactsRecyclerAdapter(this);
        recyclerViewContacts.setAdapter(contactsAdapter);
    }

    private void getContactsApi() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.provideRetrofit().create(ApiInterface.class);
        final Call<ContactsResponseModel> contactCall = apiInterface.getContacts();

        contactCall.enqueue(new Callback<ContactsResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ContactsResponseModel> call,
                                   @NonNull Response<ContactsResponseModel> response) {
                if (response.body() != null) {
                    contactEntities = response.body().getContacts();
                    // add contactEntities in db
                    contactListViewModel.saveAllContacts(contactEntities);
                    contactsAdapter.setData(contactEntities);
                    contactsAdapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<ContactsResponseModel> call,
                                  @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(mContext, handleError(t), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onContactClicked(ContactEntity contactEntity) {
        startActivity(new Intent(mContext, ContactDetailActivity.class)
                .putExtra(USER_NAME,contactEntity.getName())
                .putExtra(USER_MOBILE,contactEntity.getMobile_number()));
    }

    private String handleError(Throwable error) {
        if (error instanceof SocketTimeoutException) {
            return mContext.getString(R.string.requestTimeOutError);
        } else if (error instanceof MalformedJsonException) {
            return mContext.getString(R.string.responseMalformedJson);
        } else if (error instanceof IOException) {
            getContactsFromDb();
            return mContext.getString(R.string.networkError);
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return mContext.getString(R.string.unknownError);
        }
    }

    private void getContactsFromDb(){
        contactListViewModel.getAllContacts().observe(this, new Observer<List<ContactEntity>>() {
            @Override
            public void onChanged(List<ContactEntity> contactEntityList) {
                if (contactEntityList != null) {
                    contactsAdapter.clearList();
                    contactsAdapter.setData(contactEntityList);
                    contactsAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
