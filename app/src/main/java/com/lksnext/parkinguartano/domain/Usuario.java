package com.lksnext.parkinguartano.domain;

public class Usuario {
    private String name;
    private String email;
    private String password;

    public Usuario (String name, String email, String password){

    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
