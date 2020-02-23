package com.java.knchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.java.knchallenge.fragments.AllContactsFragment;
import com.java.knchallenge.fragments.SendOtpFragment;
import com.java.knchallenge.utils.FragmentUtils;

public class ContactDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txtUserName, txtUserMobileNumber;
    private String userName, userMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        initView();
        setupToolbar();
        getData();
        setData();
    }

    private void initView() {
        toolbar=findViewById(R.id.toolbar_contactDetail);
        txtUserName=findViewById(R.id.txt_userName);
        txtUserMobileNumber=findViewById(R.id.txt_user_mobile);
    }

    private void setupToolbar() {
        toolbar.findViewById(R.id.toolbar_img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getData() {
        Intent contactIntent = getIntent();
        if (contactIntent != null){
            userName=contactIntent.getStringExtra(AllContactsFragment.USER_NAME);
            userMobile=contactIntent.getStringExtra(AllContactsFragment.USER_MOBILE);
        }
    }

    private void setData() {
        txtUserName.setText(userName);
        txtUserMobileNumber.setText(userMobile);
    }

    public void OpenOtpSendFragment(View view) {
        FragmentUtils.replaceFragment(this, SendOtpFragment.getInstance(userName,userMobile),R.id.relative_contactDetail_container,true,FragmentUtils.TRANSITION_SLIDE_LEFT_RIGHT);
    }
}
