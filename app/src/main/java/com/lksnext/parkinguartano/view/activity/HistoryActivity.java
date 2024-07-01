package com.lksnext.parkinguartano.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lksnext.parkinguartano.R;
import com.lksnext.parkinguartano.databinding.ActivityMainBinding;
import com.lksnext.parkinguartano.utils.HistoryAdapter;
import com.lksnext.parkinguartano.utils.HistoryItem;
import com.lksnext.parkinguartano.viewmodel.HistoryViewModel;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ActivityMainBinding binding;
    NavController navController;

    private HistoryViewModel historyViewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;

    @SuppressLint("WrongViewCast")
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

        recyclerView = findViewById(R.id.recycler_view);// Asegúrate que este es el ID correcto de RecyclerView en activity_main.xml
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        historyViewModel.loadHistoryItems();

        historyViewModel.getHistoryItemList().observe(this, new Observer<List<HistoryItem>>() {
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
    }
}
