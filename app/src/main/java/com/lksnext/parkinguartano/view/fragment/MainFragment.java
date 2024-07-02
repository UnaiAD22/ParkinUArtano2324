package com.lksnext.parkinguartano.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.lksnext.parkinguartano.R;
import com.lksnext.parkinguartano.view.activity.LoginActivity;
import com.lksnext.parkinguartano.view.activity.MainActivity;
import com.lksnext.parkinguartano.viewmodel.MainViewModel;

import java.util.Calendar;


public class MainFragment extends Fragment {
    private MainViewModel mainViewModel;
    private Button nextButton;


    public MainFragment() {
        // Es necesario un constructor vacio
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        nextButton = view.findViewById(R.id.nextButton);

        nextButton.setEnabled(true);

        nextButton.setOnClickListener(v -> {

            NavController navController = Navigation.findNavController(view);
            Log.d("NEXTBUTTON___", "Next button clicked");
            navController.navigate(R.id.dateFragment);
        });

        return view;
    }
}
