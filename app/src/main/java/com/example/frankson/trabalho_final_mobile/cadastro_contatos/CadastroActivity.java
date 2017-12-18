package com.example.frankson.trabalho_final_mobile.cadastro_contatos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.frankson.trabalho_final_mobile.Entities.ContatoEntity;
import com.example.frankson.trabalho_final_mobile.R;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroActivity extends AppCompatActivity implements CadastroView {
    @BindView(R.id.cadastro_nome)
    public EditText campoNome;
    @BindView(R.id.cadastro_telefone)
    public EditText campoTelefone;
    @BindView(R.id.cadastro_email)
    public  EditText campoEmail;
    @BindView(R.id.cadastro_endereco)
    public  EditText campoEndereco;
    @BindView(R.id.cadastro_foto)
    public ImageView campoFoto;

    CadastroPresenter cadastroPresenter;
    private static final int CODIGO_CAMERA = 123;
    public String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar__contato);

        ButterKnife.bind(this);
        cadastroPresenter = new CadastroPresenter(this);
        cadastroPresenter.mostraContato((ContatoEntity) getIntent().getSerializableExtra("exibir_contato"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_contato, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_salvar:
                if(campoNome.getText().toString().isEmpty() ||
                        campoEndereco.getText().toString().isEmpty() ||
                        campoEmail.getText().toString().isEmpty() ||
                        campoTelefone.getText().toString().isEmpty()){
                    Toast.makeText(this, "Necessário preencher todos os campos para salvar!", Toast.LENGTH_SHORT).show();
                    return super.onOptionsItemSelected(item);
                }
                Intent resultado = new Intent().putExtra("contato",
                        cadastroPresenter.getContato(campoNome.getText().toString(),
                                campoTelefone.getText().toString(),
                                campoEmail.getText().toString(),
                                campoEndereco.getText().toString(),
                                caminhoFoto));

                setResult(Activity.RESULT_OK, resultado);
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void mostrarCadastro(ContatoEntity contato) {
        campoNome.setText(contato.getNome());
        campoTelefone.setText(contato.getTelefone());
        campoEndereco.setText(contato.getEndereço());
        campoEmail.setText(contato.getEmail());
        caminhoFoto = contato.getUrlFoto();
        exibeFoto();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODIGO_CAMERA && resultCode == Activity.RESULT_OK) {
            exibeFoto();
        }
    }

    @OnClick(R.id.botao_foto)
    public void tiraFoto(){
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
        File arquivoFoto = new File(caminhoFoto);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
        startActivityForResult(intentCamera, CODIGO_CAMERA);
    }

    @OnClick(R.id.localmapa)
    public void abreMapa(){
        Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ campoEndereco.getText().toString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    private void exibeFoto(){
        Picasso.with(this)
                .load("file://"+caminhoFoto)
                .fit()
                .centerCrop()
                .into(campoFoto);
    }

}
