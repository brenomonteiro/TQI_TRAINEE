package com.example.emprestimo.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emprestimo.R;
import com.example.emprestimo.activity.model.Proposta;

public class CadastroCreditoActivity extends AppCompatActivity {
private EditText valor;
private Proposta proposta;
private Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_credito);
        valor = findViewById(R.id.editTextValor);
        enviar = findViewById(R.id.buttonEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarAnuncio();
            }
        });
    }
    public void validarAnuncio(){
        String valorProposta = valor.getText().toString();

        if(!valorProposta.isEmpty()){
            salvarAnuncio();
        }else{
            Toast.makeText(CadastroCreditoActivity.this,"digite o valor da sua proposta",Toast.LENGTH_SHORT);
        }
    }

    public void salvarAnuncio(){
        String valorProposta = valor.getText().toString();
        Log.i("valorProposta",valorProposta);
         proposta =  new Proposta();
         proposta.setValor(valorProposta);
         proposta.setStatusProposta("Em andamento");
         Log.i("obj proposta",proposta.getId());
        Log.i("obj proposta2",proposta.getStatusProposta());

         proposta.salvar();
        Toast.makeText(CadastroCreditoActivity.this,"proposta cadastrada com sucesso",Toast.LENGTH_SHORT);

    }
}
