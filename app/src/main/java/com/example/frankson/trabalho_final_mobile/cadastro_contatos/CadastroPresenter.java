package com.example.frankson.trabalho_final_mobile.cadastro_contatos;

import com.example.frankson.trabalho_final_mobile.Entities.ContatoEntity;


public class CadastroPresenter {
    private CadastroView view;

    public CadastroPresenter(CadastroView view) {
        this.view = view;
    }

    public ContatoEntity getContato(String nome, String telefone,String email,String endereco, String caminhoFoto) {
        ContatoEntity contato = null;

        if(!nome.isEmpty() && !telefone.isEmpty() && !email.isEmpty() && !endereco.isEmpty()) {
            contato = new ContatoEntity();
            contato.setNome(nome);
            contato.setTelefone(telefone);
            contato.setEmail(email);
            contato.setEndereço(endereco);
            contato.setUrlFoto(caminhoFoto);
        }

        return contato;
    }

    public void mostraContato(ContatoEntity contato) {
        if(contato != null)
            view.mostrarCadastro(contato);
    }

}
