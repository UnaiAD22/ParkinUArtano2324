package com.lksnext.parkinguartano.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lksnext.parkinguartano.data.DataRepository;
import com.lksnext.parkinguartano.domain.Callback;

public class LoginViewModel extends ViewModel {

    // Aquí puedes declarar los LiveData y métodos necesarios para la vista de inicio de sesión
    MutableLiveData<Boolean> logged = new MutableLiveData<>(null);

    public LiveData<Boolean> isLogged(){
        return logged;
    }

    public MutableLiveData<String> loginMutable = new MutableLiveData<>();

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    public void loginUser(String email, String password) {
        //Clase para comprobar si los datos de inicio de sesión son correctos o no
        DataRepository.getInstance().login(email, password, new Callback() {
            //En caso de que el login sea correcto, que se hace

            String name;
            @Override
            public void onSuccess() {

                if (firebaseUser != null) {
                    name = firebaseUser.getDisplayName();
                }

                loginMutable.setValue("Usuario logeado correctamente: " + name + "\t" + email + "\t" + password);
                logged.setValue(Boolean.TRUE);
            }

            //En caso de que el login sea incorrecto
            @Override
            public void onFailure() {
                loginMutable.setValue("Error al logear usuario: " + email + "\t" + password);
                logged.setValue(Boolean.FALSE);
            }
        });
    }
}

