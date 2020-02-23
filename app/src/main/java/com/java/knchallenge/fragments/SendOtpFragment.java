package com.java.knchallenge.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.java.knchallenge.R;
import com.java.knchallenge.data.local.entity.MessageHistoryEntity;
import com.java.knchallenge.viewmodel.MessageHistoryViewModel;
import com.java.knchallenge.viewmodel.ViewModelFactory;

import java.util.Random;

/**
 * Send Otp message
 */
public class SendOtpFragment extends Fragment implements View.OnClickListener {

    private ImageView imgClose;
    private Button btnSend;
    private EditText edtTxtMessage;
    private static SendOtpFragment sendOtpFragment = null;

    private int otp;
    private static String MOBILE_NO, NAME;

    private MessageHistoryViewModel messageHistoryViewModel;

    public SendOtpFragment() {
        // Required empty public constructor
    }

    public static SendOtpFragment getInstance(String name, String mobileNumber){
        NAME=name;
        MOBILE_NO=mobileNumber;
        if (sendOtpFragment == null)
        {
            sendOtpFragment = new SendOtpFragment();
        }
        return sendOtpFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_otp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        setListeners();
    }

    private void initView(View view) {
        imgClose=view.findViewById(R.id.img_close);
        edtTxtMessage=view.findViewById(R.id.edit_txt_message);
        btnSend=view.findViewById(R.id.btn_send);

        // initialize viewModel
        messageHistoryViewModel = new ViewModelProvider(requireActivity(), new ViewModelFactory(getActivity().getApplication()))
                .get(MessageHistoryViewModel.class);
        // set message to editText
        otp = generateRandomDigits(6);
        edtTxtMessage.setText(getContext().getResources().getString(R.string.otp_message,otp));
    }

    private void setListeners() {
        btnSend.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_send){
            String message = edtTxtMessage.getText().toString();

            if (message.isEmpty()){
                Toast.makeText(getContext(), "Please enter the message.", Toast.LENGTH_SHORT).show();
            }else {
                sendOtpMessage(message);
            }
        } else if (view.getId() == R.id.img_close){
            getFragmentManager().popBackStack();
        }
    }

    private void sendOtpMessage(String message) {
        //TODO: ISSUE: couldn't find any sdk which doesn't depend on backend server for sending custom otp

        // after sending otp save contact in history
        MessageHistoryEntity messageHistoryEntity = new MessageHistoryEntity(NAME,otp,System.currentTimeMillis());
        messageHistoryViewModel.insert(messageHistoryEntity);
        getActivity().finish();
    }

    private static int generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }
}
