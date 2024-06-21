package com.lksnext.parkinguartano.data;

import com.google.firebase.auth.FirebaseAuth;
import com.lksnext.parkinguartano.databinding.ActivityRegisterBinding;
import com.lksnext.parkinguartano.domain.Callback;

public class DataRepository {

    private static DataRepository instance;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private DataRepository(){

    }

    //Creación de la instancia en caso de que no exista.
    public static synchronized DataRepository getInstance(){
        if (instance==null){
            instance = new DataRepository();
        }
        return instance;
    }

    //Petición del login.
    public void login(String email, String pass, Callback callback){
        try {
            //Realizar petición
            mAuth.signInWithEmailAndPassword(email, pass);
            callback.onSuccess();
        } catch (Exception e){
            callback.onFailure(); //String error, String message
        }
    }

    public void register(String email, String password, Callback callback){
        mAuth.createUserWithEmailAndPassword(email, password);
    }

}
