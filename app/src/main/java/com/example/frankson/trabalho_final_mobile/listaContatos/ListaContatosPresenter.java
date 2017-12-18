package com.example.frankson.trabalho_final_mobile.listaContatos;

import com.example.frankson.trabalho_final_mobile.Entities.ContatoEntity;

import java.util.ArrayList;
import java.util.List;

public class ListaContatosPresenter {
    private ListaContatosView view;
    private List<ContatoEntity> contatoList = new ArrayList<>();

    public ListaContatosPresenter(ListaContatosView view) {
        this.view = view;
    }

    public void addInList(ContatoEntity contato) {
        contatoList.add(contato);
        view.updateList(contatoList);
    }
}
