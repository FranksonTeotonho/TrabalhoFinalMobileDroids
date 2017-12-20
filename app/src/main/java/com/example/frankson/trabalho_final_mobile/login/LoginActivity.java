package com.example.frankson.trabalho_final_mobile.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.frankson.trabalho_final_mobile.listaContatos.ListaContatosActivity;
import com.example.frankson.trabalho_final_mobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView (R.id.text_input_layout_username) TextInputLayout inlUsername;
    @BindView (R.id.edit_text_username) TextInputEditText edtUsername;
    @BindView (R.id.text_input_layout_password) TextInputLayout inlPassword;
    @BindView (R.id.edit_text_password) TextInputEditText edtPassword;
    @BindView(R.id.button_login) Button btLogin;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(LoginActivity.this);
    }

    @OnClick(R.id.button_login)
    public void fazLogin() {
        loginPresenter.login(
                edtUsername.getText().toString(),
                edtPassword.getText().toString());
    }

    public void setErroUsuario() {
        inlUsername.setErrorEnabled(true);
        inlUsername.setError(getString(R.string.invalid_username));
    }

    public void setErroSenha() {
        inlPassword.setErrorEnabled(true);
        inlPassword.setError(getString(R.string.invalid_password));
    }

    public void doLogin(){
        Intent abrirListaContatos = new Intent(LoginActivity.this, ListaContatosActivity.class);
        startActivity(abrirListaContatos);
        this.finish();
    }

    @OnTextChanged(R.id.edit_text_username)
    public void textoLogin()
    {
            inlUsername.setErrorEnabled(false);
            inlUsername.setError("");
    }

    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnTextChanged(R.id.edit_text_password)
    public void textoPassword()
    {
        inlPassword.setErrorEnabled(false);
        inlPassword.setError("");
    }
}
