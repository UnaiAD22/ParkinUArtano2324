package com.lksnext.parkinguartano.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.lksnext.parkinguartano.R;

import javax.annotation.Nullable;


public class HoraFragment extends Fragment {

    private TimePicker timePickerI;
    private TimePicker timePickerF;
    private Button nextButton;

    private HoraFragment.OnFragmentHoraListener mListener;
    public HoraFragment() {
        // Es necesario un constructor vacio
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentHoraListener) {
            mListener = (OnFragmentHoraListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentHoraListener");
        }
    }

    public interface OnFragmentHoraListener {
        void onFragmentHoraReceived(String hora);
    }

    private void sendHoraToActivity(String fecha) {
        if (mListener != null) {
            mListener.onFragmentHoraReceived(fecha);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timePickerI = view.findViewById(R.id.timePickerI);
        timePickerF = view.findViewById(R.id.timePickerF);
        nextButton = view.findViewById(R.id.nextButton);

        timePickerI.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Habilitar el botón cuando se seleccione una hora
            }
        });

        timePickerF.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Habilitar el botón cuando se seleccione una hora
                nextButton.setEnabled(true);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hourI = timePickerI.getCurrentHour();
                int minuteI = timePickerI.getCurrentMinute();
                // Aquí puedes manejar la hora seleccionada
                //Toast.makeText(getContext(), "Hora seleccionada: " + hourI + ":" + minuteI, Toast.LENGTH_SHORT).show();

                String formattedTimeI = String.format("%d:%02d", hourI, minuteI);

                int hourF = timePickerF.getCurrentHour();
                int minuteF = timePickerF.getCurrentMinute();

                String formattedTimeF = String.format("%d:%02d", hourF, minuteF);

                System.out.println("Hora seleccionadaaa: " + formattedTimeI + " - " + formattedTimeF);

                sendHoraToActivity(formattedTimeI + " - " + formattedTimeF);

                NavController navController = Navigation.findNavController(view);
                Log.d("NEXTBUTTON___", "Next button clicked");
                navController.navigate(R.id.parkingChooseFragment);
            }
        });

        //return view;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hora, container, false);
    }
}
