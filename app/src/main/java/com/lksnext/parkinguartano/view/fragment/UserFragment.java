package com.lksnext.parkinguartano.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lksnext.parkinguartano.R;
import com.lksnext.parkinguartano.viewmodel.HistoryViewModel;
import com.lksnext.parkinguartano.viewmodel.UserViewModel;

public class UserFragment extends Fragment {
    private UserViewModel userViewModel;

    public UserFragment() {
        // Es necesario un constructor vacio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        return view;
    }
}
