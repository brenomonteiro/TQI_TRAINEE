package com.example.emprestimo.activity.model;

import android.util.Log;

import com.example.emprestimo.activity.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Proposta {
    private String id;
    private String valor;
    private String statusProposta;

    public Proposta() {
        DatabaseReference propostaRef = ConfiguracaoFirebase.getFirebaseDatabase().child("minhas_propostas");
        setId(propostaRef.push().getKey());
    }

    public void salvar(){
        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        Log.i("id usuario",idUsuario);
        DatabaseReference propostaRef = ConfiguracaoFirebase.getFirebaseDatabase().child("minhas_propostas");

        propostaRef.child(idUsuario).child(getId()).setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getStatusProposta() {
        return statusProposta;
    }

    public void setStatusProposta(String statusProposta) {
        this.statusProposta = statusProposta;
    }
}
