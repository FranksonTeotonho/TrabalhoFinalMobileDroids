package com.example.frankson.trabalho_final_mobile.listaContatos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frankson.trabalho_final_mobile.Entities.ContatoEntity;
import com.example.frankson.trabalho_final_mobile.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListaContatosAdapter extends RecyclerView.Adapter<ListaContatosAdapter.ViewHolder> {
    private List<ContatoEntity> contatoList;
    private OnRecyclerViewSelected onRecyclerViewSelected;
    private Context context;

    ListaContatosAdapter(List<ContatoEntity> contatoList, Context context) {
        this.contatoList = contatoList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contato, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContatoEntity contato = contatoList.get(position);

        if (contato.getUrlFoto() != null)
            Picasso.with(context)
                    .load("file://"+contato.getUrlFoto())
                    .centerCrop()
                    .fit()
                    .into(holder.imgFoto);

        holder.txNome.setText(contato.getNome());
        holder.txEmail.setText(contato.getEmail());
        holder.txTelefone.setText(contato.getTelefone());
        holder.txEndereco.setText(contato.getEndereço());
    }

    @Override
    public int getItemCount() {
        return contatoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contato_nome)
        TextView txNome;

        @BindView(R.id.contato_telefone)
        TextView txTelefone;

        @BindView(R.id.contato_endereco)
        TextView txEndereco;

        @BindView(R.id.contato_email)
        TextView txEmail;

        @BindView(R.id.contato_foto)
        ImageView imgFoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.container)
        void onItemClick(View view){
            if(onRecyclerViewSelected != null)
                onRecyclerViewSelected.onClick(view, getAdapterPosition());

        }

    }

    public void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }
}
