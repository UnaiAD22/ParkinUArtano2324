package com.lksnext.parkinguartano.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.lksnext.parkinguartano.R;
import com.lksnext.parkinguartano.viewmodel.HistoryViewModel;

public class HistoryFragment extends Fragment {
    private HistoryViewModel historyViewModel;

    public HistoryFragment() {
        // Es necesario un constructor vacio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        return view;
    }
}
