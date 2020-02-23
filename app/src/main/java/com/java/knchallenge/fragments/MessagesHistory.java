package com.java.knchallenge.fragments;


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

import com.java.knchallenge.R;
import com.java.knchallenge.adapters.MessageHistoryRecyclerAdapter;
import com.java.knchallenge.data.local.entity.MessageHistoryEntity;
import com.java.knchallenge.viewmodel.MessageHistoryViewModel;
import com.java.knchallenge.viewmodel.ViewModelFactory;

import java.util.List;

/**
 * List of messages sent
 */
public class MessagesHistory extends Fragment {

    private MessageHistoryViewModel messageHistoryViewModel;

    public MessagesHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        messageHistoryViewModel = new ViewModelProvider(requireActivity(), new ViewModelFactory(getActivity().getApplication()))
                .get(MessageHistoryViewModel.class);

        RecyclerView recyclerViewMessages = view.findViewById(R.id.recycler_messages_sent);
        final MessageHistoryRecyclerAdapter adapter = new MessageHistoryRecyclerAdapter(getContext());
        recyclerViewMessages.setAdapter(adapter);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(getContext()));

        messageHistoryViewModel.getMessageHistory().observe(this, new Observer<List<MessageHistoryEntity>>() {
            @Override
            public void onChanged(List<MessageHistoryEntity> messageHistoryEntities) {
                adapter.setData(messageHistoryEntities);
            }
        });
    }
}
