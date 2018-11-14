package marcos_matheus.trabalho_dcc196;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CadastroParticipante extends AppCompatActivity {

    Button btCadastrarParticipante;
    Button btCancelar;

    EditText edtNomeParticipante;
    EditText edtEmailParticipante;
    EditText edtCpfParticipante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);



        btCancelar = (Button) findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cancela = new Intent(CadastroParticipante.this, MainActivity.class);
                startActivity(cancela);
            }
        });

        edtNomeParticipante = (EditText) findViewById(R.id.edtNomeParticipante);
        edtEmailParticipante = (EditText) findViewById(R.id.edtEmailParticipante);
        edtCpfParticipante = (EditText) findViewById(R.id.edtCpfParticipante);

        btCadastrarParticipante = (Button) findViewById(R.id.btCadastrarParticipante);
        btCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultadoParticipante = new Intent();

                String nome = edtNomeParticipante.getText().toString();
                String email = edtEmailParticipante.getText().toString();
                String cpf = edtCpfParticipante.getText().toString();

                if(nome.equals("") || email.equals("") || cpf.equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {

                    Toast.makeText(getApplicationContext(), "Participante cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
}
