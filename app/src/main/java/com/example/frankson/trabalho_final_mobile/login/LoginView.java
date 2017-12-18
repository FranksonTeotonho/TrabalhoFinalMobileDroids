package com.example.frankson.trabalho_final_mobile.login;

/**
 * Created by Bruno on 16/12/2017.
 */

public interface LoginView {
    void setErroUsuario();
    void setErroSenha();
    void doLogin();
    void showMessage(String msg);
}
