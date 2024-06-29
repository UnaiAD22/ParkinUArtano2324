package com.lksnext.parkinguartano.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.lksnext.parkinguartano.data.DataRepository;
import com.lksnext.parkinguartano.domain.Callback;
import com.lksnext.parkinguartano.view.activity.RegisterActivity;

public class RegisterViewModel extends ViewModel {
    // Aquí puedes declarar los LiveData y métodos necesarios para la vista de inicio de sesión
    MutableLiveData<Boolean> logged = new MutableLiveData<>(null);

    public LiveData<Boolean> isLogged(){
        return logged;
    }

    //Mirar si se ha registrado bien
    public MutableLiveData<String> registerMutable = new MutableLiveData<>();

    //Lo mismo pero para errores

    public void register (String email, String password) {
        //Clase para comprobar si los datos de inicio de sesión son correctos o no

        DataRepository.getInstance().register(email, password, new Callback() {
            //En caso de que el login sea correcto, que se hace
            @Override
            public void onSuccess() {
                registerMutable.setValue("Usuario registrado correctamente: " + email + "\t" + password);
                logged.setValue(Boolean.TRUE);
            }

            //En caso de que el login sea incorrecto, que se hace
            @Override
            public void onFailure() {
                registerMutable.setValue("Error al registrar usuario: " + email + "\t" + password);
                logged.setValue(Boolean.FALSE);
            }
        });
    }
}
