package com.lksnext.parkinguartano.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.lksnext.parkinguartano.R;
import com.lksnext.parkinguartano.databinding.ActivityLoginBinding;
import com.lksnext.parkinguartano.domain.Usuario;
import com.lksnext.parkinguartano.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    private LinearLayout cardContainer;

    private int cardCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Asignamos la vista/interfaz login (layout)
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Asignamos el viewModel de login
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        //Acciones a realizar cuando el usuario clica el boton de login
        binding.loginButton.setOnClickListener(v -> {
            String email = binding.emailText.getText().toString();
            String password = binding.passwordText.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Log.d("LLLLL", "Email o password vacios");
            } else if (!email.contains("@gmail.com")) {
                Log.d("LLLLL", "No es un email valido. Tiene que contener @ y . como minimo");
            } else {
                Log.d("LLLLL", "Login correcto");
                loginViewModel.loginUser(email, password);
            }
        });

        //Acciones a realizar cuando el usuario clica el boton de crear cuenta (se cambia de pantalla)
        binding.createAccount.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        //Observamos la variable logged, la cual nos informara cuando el usuario intente hacer login y se
        //cambia de pantalla en caso de login correcto
        loginViewModel.isLogged().observe(this, logged -> {
            if (logged != null) {
                if (logged) {
                    //Login Correcto
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    //Login incorrecto
                    Log.d("LLLLL", "Login incorrecto");
                }
            }
        });

        String email = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();
        loginViewModel.loginUser(email, password);

        cardContainer = findViewById(R.id.recycler_view);
    }

    public void agregarTarjeta(View view) {
        int numeroTarjetas = 2;

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("NUMERO_TARJETAS", numeroTarjetas);
        startActivity(intent);
    }
}