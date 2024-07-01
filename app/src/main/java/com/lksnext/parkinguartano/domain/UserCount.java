package com.lksnext.parkinguartano.domain;

public class UserCount {
    private static Usuario usuario;

    public static void setUsuario(Usuario user) {
        usuario = user;
    }

    public static Usuario getUsuario() {
        return usuario;
    }
}
