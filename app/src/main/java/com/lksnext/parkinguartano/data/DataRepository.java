package com.lksnext.parkinguartano.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lksnext.parkinguartano.databinding.ActivityRegisterBinding;
import com.lksnext.parkinguartano.domain.Callback;

public class DataRepository {

    private static DataRepository instance;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

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
            System.out.println("Error: " + e.getMessage());
            callback.onFailure(); //String error, String message
            System.out.println("MAL");
        }
    }

    public void register(String email, String password, Callback callback){
        try {
            //Realizar petición
            mAuth.createUserWithEmailAndPassword(email, password);
            callback.onSuccess();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            callback.onFailure(); //String error, String message
            System.out.println("MAL");
        }
    }

}
