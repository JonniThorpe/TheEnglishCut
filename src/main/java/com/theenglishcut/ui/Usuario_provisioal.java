package com.theenglishcut.ui;

public class Usuario_provisioal {
    String user;

    String password;
    String tipo;
    public Usuario_provisioal(String user, String password, String tipo) {
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
