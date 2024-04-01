package com.gt.theenglishcut.model;

public class Usuario {
    String user;

    String password;
    String tipo;
    public Usuario(String user, String password, String tipo) {
        this.user = user;
        this.password = password;
        this.tipo = tipo;
    }

    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }

    public String getTipo() {
        return tipo;
    }

}
