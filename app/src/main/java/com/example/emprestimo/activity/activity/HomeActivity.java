package com.example.emprestimo.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.emprestimo.R;

public class HomeActivity extends AppCompatActivity {
Button enviar,enviarLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        enviar = findViewById(R.id.buttonSolicitarCredito);
        enviarLista = findViewById(R.id.buttonMinhasPropostas);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroCreditoActivity.class);
                startActivity(intent);
            }
        });


        enviarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MeusAnunciosActivity.class);
                startActivity(intent);
            }
        });
    }
}
