package com.example.emprestimo.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emprestimo.R;
import com.example.emprestimo.activity.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.emprestimo.activity.model.Usuario;

public class CadastroActivity extends AppCompatActivity {
    private DatabaseReference referenciaBD = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth autenticacao;

    private EditText nome,cpf,rg,email,senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = findViewById(R.id.editTextNome);
        cpf = findViewById(R.id.editTextCPF);
        rg = findViewById(R.id.editTextRG);
        email = findViewById(R.id.editTextEmail);
        senha = findViewById(R.id.editTextSenha);
    }

    public void validarCadastroUsuario(View view){
        String textoNome = nome.getText().toString();
        String textoCpf = cpf.getText().toString();
        String textoRg = rg.getText().toString();
        String textoEmail = email.getText().toString();
        String textoSenha = senha.getText().toString();

        if(!textoNome.isEmpty()){
            if(!textoCpf.isEmpty()){
                if(!textoRg.isEmpty()){
                    if(!textoEmail.isEmpty()){
                        if(!textoSenha.isEmpty()){
                            Usuario usuario = new Usuario();
                            usuario.setNome(textoNome);
                            usuario.setCpf(textoCpf);
                            usuario.setRg(textoRg);
                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);

                            cadastrarUsuario(usuario);
                        }else{
                            Toast.makeText(CadastroActivity.this, "preencha a senha", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CadastroActivity.this, "preencha o e-mail", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CadastroActivity.this, "preencha o rg", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(CadastroActivity.this, "preencha o cpf", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(CadastroActivity.this, "preencha o nome", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarUsuario(final Usuario usuario){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String idUsuario = task.getResult().getUser().getUid();
                            usuario.setId(idUsuario);
                            usuario.salvar();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(CadastroActivity.this, "cadastro realizado", Toast.LENGTH_SHORT).show();
                        }else{

                            Toast.makeText(CadastroActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();}

                    }


                    }
                );
    }
}
