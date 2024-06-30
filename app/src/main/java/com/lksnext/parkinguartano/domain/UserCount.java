package com.lksnext.parkinguartano.domain;

public class UserCount {
    private static Usuario usuario;

    // Método para establecer el usuario después del login
    public static void setUsuario(Usuario user) {
        usuario = user;
    }

    // Método para obtener el usuario
    public static Usuario getUsuario() {
        return usuario;
    }
}
