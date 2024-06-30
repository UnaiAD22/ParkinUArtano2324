package com.lksnext.parkinguartano.view.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lksnext.parkinguartano.R;
import com.lksnext.parkinguartano.databinding.ActivityMainBinding;
import com.lksnext.parkinguartano.domain.Hora;
import com.lksnext.parkinguartano.domain.Plaza;
import com.lksnext.parkinguartano.domain.Reserva;
import com.lksnext.parkinguartano.domain.UserCount;
import com.lksnext.parkinguartano.domain.Usuario;
import com.lksnext.parkinguartano.view.fragment.HoraFragment;
import com.lksnext.parkinguartano.view.fragment.MainFragment;
import com.lksnext.parkinguartano.view.fragment.ParkingChooseFragment;
import com.lksnext.parkinguartano.view.fragment.ParkingFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements
        MainFragment.OnFragmentDataListener,
        HoraFragment.OnFragmentHoraListener,
        ParkingChooseFragment.OnFragmentTypeListener,
        ParkingFragment.OnFragmentIdListener
{

    private String fechaSeleccionada;
    private String horaSeleccionada;
    private String tipoVehiculoSeleccionado;
    private int idPlazaSeleccionada;

    BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;
    NavController navController;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Asignamos la vista/interfaz main (layout)
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Con el NavigationHost podremos movernos por distintas pestañas dentro de la misma pantalla
        NavHostFragment navHostFragment =
            (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.flFragment);
        navController = navHostFragment.getNavController();

        //Asignamos los botones de navegacion que se encuentran en la vista (layout)
        bottomNavigationView = binding.bottomNavigationView;
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        //Dependendiendo que boton clique el usuario de la navegacion se hacen distintas cosas
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.newres) {
                navController.navigate(R.id.mainFragment);
                return true;
            } else if (itemId == R.id.reservations) {
                navController.navigate(R.id.historyFragment);
                return true;
            } else if (itemId == R.id.person) {
                navController.navigate(R.id.userFragment);
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    public void onFragmentFechaReceived(String fecha) {
        fechaSeleccionada = fecha;
        createObjectIfReady();
    }

    public void onFragmentHoraReceived(String hora) {
        horaSeleccionada = hora;
        createObjectIfReady();
    }

    public void onFragmentTipoReceived(String hora) {
        tipoVehiculoSeleccionado = hora;
        createObjectIfReady();
    }

    public void onFragmentIdListener(int id) {
        idPlazaSeleccionada = id;
        createObjectIfReady();
    }

    private void createObjectIfReady() {
        if (fechaSeleccionada != null && horaSeleccionada != null && tipoVehiculoSeleccionado != null && idPlazaSeleccionada != 0) {
            // Todos los datos están disponibles, crea el objeto
            Usuario user = UserCount.getUsuario();

            String[] dates = horaSeleccionada.split(" - ");
            String inicioHora = dates[0];
            String finHora = dates[1];

            Hora hora = null;

            long longValue = Long.valueOf(idPlazaSeleccionada);
            System.out.println("El valor long es: " + longValue);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            //Convertir las horas a milisegundos
            try {
                Date timeDateI = timeFormat.parse(inicioHora);
                Date timeDateF = timeFormat.parse(finHora);

                long timeIInLong = timeDateI.getTime() - timeFormat.parse("00:00").getTime();
                long timeFInLong = timeDateF.getTime() - timeFormat.parse("00:00").getTime();

                System.out.println("Hora (long): " + timeIInLong);
                System.out.println("Hora (long): " + timeFInLong);

                hora = new Hora(timeIInLong, timeFInLong);

            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Error al analizar la hora.");
            }

            Plaza plaza = new Plaza(longValue, tipoVehiculoSeleccionado);
            Log.d("PLAZACREADAAA", plaza.toString());

            Log.d("IDPLAZA", hora.toString());

            String idInString = Long.toString(longValue);
            Reserva reserva = new Reserva(fechaSeleccionada, user.getName(), idInString, plaza, hora);
            Log.d("RESERVACREADAAA", reserva.toString());
        }
    }
}