package com.lksnext.parkinguartano.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.lksnext.parkinguartano.R;

public class ParkingChooseFragment extends Fragment {

    private static final String TAG = "ParkingFragment";

    private ParkingChooseFragment.OnFragmentTypeListener mListener;

    public interface OnFragmentTypeListener {
        void onFragmentTipoReceived(String hora);
    }

    private void sendTypeToActivity(String fecha) {
        if (mListener != null) {
            mListener.onFragmentTipoReceived(fecha);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentTypeListener) {
            mListener = (OnFragmentTypeListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentTypeListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parking_choose, container, false);

        ImageButton btnNormal = view.findViewById(R.id.btnNormal);
        ImageButton btnElectric = view.findViewById(R.id.btnElectric);
        ImageButton btnHandicapped = view.findViewById(R.id.btnHandicapped);

        Bundle bundle = new Bundle();
        ParkingFragment parkingFragment = new ParkingFragment();
        parkingFragment.setArguments(bundle);

        btnNormal.setOnClickListener(v -> {
            String type = "Normal";
            Log.d(TAG, type);

            bundle.putString("TipoCar", type);

            sendTypeToActivity(type);
            NavController navController = Navigation.findNavController(view);
            Log.d("NEXTBUTTON___", "Next button clicked");
            navController.navigate(R.id.parkingFragment, bundle);
        });

        btnElectric.setOnClickListener(v -> {
            String type = "Eléctrico";
            Log.d(TAG, type);

            bundle.putString("TipoCar", type);

            sendTypeToActivity(type);
            NavController navController = Navigation.findNavController(view);
            Log.d("NEXTBUTTON___", "Next button clicked");
            navController.navigate(R.id.parkingFragment, bundle);
        });

        btnHandicapped.setOnClickListener(v -> {
            String type = "Minusválido";
            Log.d(TAG, type);

            bundle.putString("TipoCar", type);

            sendTypeToActivity(type);
            NavController navController = Navigation.findNavController(view);
            Log.d("NEXTBUTTON___", "Next button clicked");
            navController.navigate(R.id.parkingFragment, bundle);
        });

        return view;
    }
}
