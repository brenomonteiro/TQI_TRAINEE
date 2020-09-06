package com.example.emprestimo.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emprestimo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    //começa a partir do raiz
    private DatabaseReference referenciaBD = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();

    private TextView linkCadastro;
    private Button botaoLogar;
    private EditText editEmail;
    private EditText editSenha;
    private String emailValue,senhaValue;

    //deslogar usuario = usuario.signOut();
    //verifica se usuario está logado = usuario.getCurrentUser


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botaoLogar = findViewById(R.id.buttonLogin);
        linkCadastro = findViewById(R.id.textViewCadastro);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 editEmail = findViewById(R.id.editTextEmail);
                 editSenha = findViewById(R.id.editTextSenha);

                 emailValue = editEmail.getText().toString();
                 senhaValue = editSenha.getText().toString();
                if(!emailValue.isEmpty() && !senhaValue.isEmpty()){
                    DatabaseReference usuarios = referenciaBD.child("usuarios");
                    usuario.signInWithEmailAndPassword(emailValue,senhaValue).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"Senha ou Login inválidos",Toast.LENGTH_SHORT).show();                            }
                        }
                    });
                }else {
                    Toast.makeText(MainActivity.this,"Senha ou Login inválidos",Toast.LENGTH_SHORT).show();

                }

            }
        });

        linkCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
}
