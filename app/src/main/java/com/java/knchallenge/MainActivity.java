package com.java.knchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.java.knchallenge.adapters.ContactMessagesTabAdapter;
import com.java.knchallenge.fragments.AllContactsFragment;
import com.java.knchallenge.fragments.MessagesHistory;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setTabAdapter();
        setUpToolbar();
    }

    private void initView() {
        viewPager =  findViewById(R.id.viewpager);
        tabLayout =  findViewById(R.id.tabMode);
    }

    private void setTabAdapter() {
        ContactMessagesTabAdapter adapter = new ContactMessagesTabAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllContactsFragment(),"ALL");
        adapter.addFragment(new MessagesHistory(),"HISTORY");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpToolbar() {
        getSupportActionBar().setTitle("Contacts");
    }
}
