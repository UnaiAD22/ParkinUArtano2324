package com.lksnext.parkinguartano.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lksnext.parkinguartano.databinding.ActivityRegisterBinding;
import com.lksnext.parkinguartano.domain.Callback;
import com.lksnext.parkinguartano.domain.UserCount;
import com.lksnext.parkinguartano.domain.Usuario;

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
    public Usuario login(String email, String pass, Callback callback){
        try {
            //Realizar petición
            //Usuario user = new Usuario(email, email, pass);

            FirebaseUser firebaseUser = mAuth.getCurrentUser();

            Usuario user = new Usuario(firebaseUser.getDisplayName(), firebaseUser.getEmail(), pass);

            UserCount.setUsuario(user);
            mAuth.signInWithEmailAndPassword(email, pass);
            callback.onSuccess();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            callback.onFailure(); //String error, String message
            System.out.println("MAL");
        }

        return  UserCount.getUsuario();
    }

    public Usuario register(String email, String password, Callback callback){
        try {
            String username = email.substring(0, email.indexOf('@'));
            //Realizar petición


            mAuth.createUserWithEmailAndPassword(email, password);

            FirebaseUser firebaseUser = mAuth.getCurrentUser();

            Usuario user = new Usuario(firebaseUser.getDisplayName(), firebaseUser.getEmail(), password);

            UserCount.setUsuario(user);

            callback.onSuccess();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            callback.onFailure(); //String error, String message
            System.out.println("MAL");
        }

        return UserCount.getUsuario();
    }

}
