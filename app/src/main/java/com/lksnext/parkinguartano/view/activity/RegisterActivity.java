package com.lksnext.parkinguartano.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.lksnext.parkinguartano.databinding.ActivityRegisterBinding;
import com.lksnext.parkinguartano.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Asignamos la vista/interfaz de registro
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Asignamos el viewModel de register
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        //registerViewModel.register(email, password);
        binding.btnRegister.setOnClickListener( v -> {
            String email = binding.emailText.getText().toString();
            String password = binding.passwordText.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                System.out.println("Email o password vacios");
            } else if (!email.contains("@gmail.com")) {
                System.out.println("No es un email valido. Tiene que contener @ y . como minimo");
            } else {
                System.out.println("Email: " + email + " Password: " + password);
                registerViewModel.register(email, password);
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}