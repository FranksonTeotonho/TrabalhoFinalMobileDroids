package com.example.frankson.trabalho_final_mobile.listaContatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.frankson.trabalho_final_mobile.Entities.ContatoEntity;
import com.example.frankson.trabalho_final_mobile.R;
import com.example.frankson.trabalho_final_mobile.cadastro_contatos.CadastroActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaContatosActivity extends AppCompatActivity implements ListaContatosView{

    @BindView(R.id.rv_contatos)
    RecyclerView rv_contatos;
    ListaContatosPresenter listaContatosPresenter;
    private final int CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);
        ButterKnife.bind(this);
        listaContatosPresenter = new ListaContatosPresenter(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Lista de contatos ");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contatos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_adicionar_contato:
                Intent adicionarContato = new Intent(ListaContatosActivity.this, CadastroActivity.class);
                startActivityForResult(adicionarContato, CODE);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE && resultCode == Activity.RESULT_OK){
            ContatoEntity contato = (ContatoEntity) data.getSerializableExtra("contato");
            if(contato != null)
                listaContatosPresenter.addInList(contato);
        }
    }

    @Override
    public void updateList(final List<ContatoEntity> contatoList) {

        ListaContatosAdapter contatosAdapter = new ListaContatosAdapter(contatoList, this);
        contatosAdapter.setOnRecyclerViewSelected(new com.example.frankson.trabalho_final_mobile.listaContatos.OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                Intent exibirContato = new Intent(ListaContatosActivity.this, CadastroActivity.class);
                exibirContato.putExtra("exibir_contato", contatoList.get(position));
                startActivity(exibirContato);
            }

        });

        rv_contatos.setAdapter(contatosAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_contatos.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        rv_contatos.addItemDecoration(dividerItemDecoration);

    }
}
