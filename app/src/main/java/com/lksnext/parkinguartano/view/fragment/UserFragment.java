package com.lksnext.parkinguartano.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.lksnext.parkinguartano.R;
import com.lksnext.parkinguartano.view.activity.LoginActivity;
import com.lksnext.parkinguartano.viewmodel.HistoryViewModel;
import com.lksnext.parkinguartano.viewmodel.UserViewModel;

public class UserFragment extends Fragment {

    public UserFragment() {
        // Es necesario un constructor vacio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        ImageView logOutIcon = view.findViewById(R.id.LogOutIcon);
        logOutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
