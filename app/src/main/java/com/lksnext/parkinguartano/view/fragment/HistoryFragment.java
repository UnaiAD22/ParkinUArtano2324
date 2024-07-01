package com.lksnext.parkinguartano.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lksnext.parkinguartano.R;
import com.lksnext.parkinguartano.utils.HistoryAdapter;
import com.lksnext.parkinguartano.utils.HistoryItem;
import com.lksnext.parkinguartano.viewmodel.HistoryViewModel;

import java.util.List;

public class HistoryFragment extends Fragment {
    private HistoryViewModel historyViewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;

    public HistoryFragment() {
        // Es necesario un constructor vacio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        historyViewModel.loadHistoryItems();

        historyViewModel.getHistoryItemList().observe(getViewLifecycleOwner(), new Observer<List<HistoryItem>>() {
            @Override
            public void onChanged(List<HistoryItem> historyItems) {
                Log.d("HistoryActivity", "Data received1: " + historyItems.size() + " items");
                if (historyItems != null && !historyItems.isEmpty()) {
                    Log.d("HistoryActivity", "Data received2: " + historyItems.size() + " items");
                    historyAdapter = new HistoryAdapter(historyItems);
                    recyclerView.setAdapter(historyAdapter);
                } else {
                    Log.d("HistoryActivity", "No data received");
                }
            }
        });

        return view;
    }
}
