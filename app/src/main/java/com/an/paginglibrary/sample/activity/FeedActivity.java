package com.an.paginglibrary.sample.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.an.paginglibrary.sample.AppController;
import com.an.paginglibrary.sample.R;
import com.an.paginglibrary.sample.adapters.FeedListAdapter;
import com.an.paginglibrary.sample.databinding.FeedActivityBinding;
import com.an.paginglibrary.sample.viewmodel.FeedViewModel;

public class FeedActivity extends AppCompatActivity {

    private FeedListAdapter adapter;
    private FeedViewModel feedViewModel;
    private FeedActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        feedViewModel = new FeedViewModel(AppController.create(getApplicationContext()));

        binding.listFeed.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new FeedListAdapter(getApplicationContext());

        feedViewModel.getArticleLiveData().observe(this, pagedList -> {
            adapter.submitList(pagedList);
        });

        feedViewModel.getNetworkState().observe(this, networkState -> {
            adapter.setNetworkState(networkState);
        });

        binding.listFeed.setAdapter(adapter);
    }
}
