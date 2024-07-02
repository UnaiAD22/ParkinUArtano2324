package com.lksnext.parkinguartano.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.lksnext.parkinguartano.R;
import com.lksnext.parkinguartano.viewmodel.MainViewModel;

import java.util.Calendar;


public class DateFragment extends Fragment {

    private CalendarView calendarView;

    private MainViewModel dateViewModel;
    private Button nextButton;

    private DateFragment.OnFragmentDataListener mListener;

    public DateFragment() {
        // Es necesario un constructor vacio
    }

    public interface OnFragmentDataListener {
        void onFragmentFechaReceived(String fecha);
    }

    private void sendDataToActivity(String fecha) {
        if (mListener != null) {
            mListener.onFragmentFechaReceived(fecha);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentDataListener) {
            mListener = (OnFragmentDataListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentDataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);

        dateViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        calendarView = view.findViewById(R.id.calendarView);
        nextButton = view.findViewById(R.id.nextButton);

        // Obtener la fecha actual
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0); // Para comparar solo la fecha y no la hora
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                selectedDate.set(Calendar.HOUR_OF_DAY, 0); // Para comparar solo la fecha y no la hora
                selectedDate.set(Calendar.MINUTE, 0);
                selectedDate.set(Calendar.SECOND, 0);
                selectedDate.set(Calendar.MILLISECOND, 0);

                if (selectedDate.compareTo(today) >= 0) {
                    dateViewModel.setSelectedDate(dayOfMonth + "/" + (month + 1) + "/" + year);
                } else {
                    dateViewModel.setSelectedDate(null); // Fecha inválida, no habilitar el botón
                }
            }
        });

        dateViewModel.getSelectedDate().observe(getViewLifecycleOwner(), date -> {
            Log.d("SELECTEDDATE___", "Date selected: " + date);
            // Habilitar el botón si se ha seleccionado una fecha
            nextButton.setEnabled(date != null && !date.isEmpty());
            if (date != null && !date.isEmpty()) {
                sendDataToActivity(date);
            }
        });

        nextButton.setOnClickListener(v -> {

            NavController navController = Navigation.findNavController(view);
            Log.d("NEXTBUTTON___", "Next button clicked");
            navController.navigate(R.id.horaFragment);
        });

        return view;
    }
}
