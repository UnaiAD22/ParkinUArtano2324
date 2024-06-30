package com.lksnext.parkinguartano.view.fragment;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.lksnext.parkinguartano.R;

public class ParkingFragment extends Fragment {

    private static final int TOTAL_SPOTS = 200;
    private static final int COLUMN_COUNT = 10;
    private GridLayout parkingGrid;

    int buttonWidthPx;
    int buttonHeightPx;

    private ParkingFragment.OnFragmentIdListener mListener;

    public interface OnFragmentIdListener {
        void onFragmentIdListener(int id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentIdListener) {
            mListener = (OnFragmentIdListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentIdListener");
        }
    }

    private void sendIdToActivity(int id) {
        if (mListener != null) {
            mListener.onFragmentIdListener(id);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_parking, container, false);

        Spinner carTypeSpinner = root.findViewById(R.id.carTypeSpinner);
        parkingGrid = root.findViewById(R.id.parkingGrid);

        carTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = parent.getItemAtPosition(position).toString();
                updateParkingSpots(selectedType, root);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Inicializar con el primer tipo de coche
        updateParkingSpots(carTypeSpinner.getSelectedItem().toString(), root);
        Toast.makeText(getContext(), "Reserva realizada con éxito", Toast.LENGTH_SHORT).show();

        return root;
    }

    @SuppressLint("ResourceAsColor")
    private void updateParkingSpots(String carType, View view) {
        parkingGrid.removeAllViews();

            buttonWidthPx = getResources().getDimensionPixelSize(R.dimen.button_default_width);
            buttonHeightPx = getResources().getDimensionPixelSize(R.dimen.button_default_height);

        int rowCount = 20; // Total de filas
        int columnCount = 10; // Total de columnas

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                int spotId = row * columnCount + col;
                Button spotButton = new Button(requireContext());
                spotButton.setId(spotId);

                boolean isEnabled = false;
                if (carType.equals("Eléctrico") && spotId < 10) {
                    isEnabled = true;
                } else if (carType.equals("Minusválido") && spotId >= 10 && spotId < 20) {
                    isEnabled = true;
                } else if (carType.equals("Normal") && spotId >= 20) {
                    isEnabled = true;
                } else {
                    spotButton.setBackgroundColor(R.color.lightGray);
                }

                // Añadir margen entre botones
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = buttonWidthPx;
                params.height = buttonHeightPx;
                params.setMargins(5, 75, 5, 75);
                spotButton.setLayoutParams(params);

                spotButton.setText("Plaza " + (spotId + 1));
                spotButton.setEnabled(isEnabled);
                spotButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int spotId = v.getId();
                        int correctSpotId = spotId + 1;
                        Log.d("ParkingFragmenttt", "Clicked spot ID: " + correctSpotId);

                        sendIdToActivity(correctSpotId);

                        NavController navController = Navigation.findNavController(view);
                        Toast.makeText(requireContext(), "Reserva realizada con éxito.", Toast.LENGTH_SHORT).show();
                        Log.d("NEXTBUTTON___", "Next button clicked");
                        navController.navigate(R.id.mainFragment);
                    }
                });

                parkingGrid.addView(spotButton);
            }
        }
    }
}