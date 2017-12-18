package com.example.frankson.trabalho_final_mobile.login;

import android.text.TextUtils;

/**
 * Created by Bruno on 16/12/2017.
 */

public class LoginPresenter {
    LoginView loginView;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void login(String username, String password){
        if(TextUtils.isEmpty(username)){
            loginView.setErroUsuario();
        } else if(TextUtils.isEmpty(password)){
            loginView.setErroSenha();
        } else if(!username.equals(password)){
            loginView.showMessage("Usuário e/ou senha inválidos!");
        }
        else {
            loginView.doLogin();
        }
    }
}
