package marcos_matheus.trabalho_dcc196;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroEvento extends AppCompatActivity {

    Button btCadastrarEvento;
    Button btCancelar;

    EditText edtTituloEvento;
    EditText edtDataEvento;
    EditText edtHorarioEvento;
    EditText edtFacilitadorEvento;
    EditText edtDescricaoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        btCadastrarEvento = (Button) findViewById(R.id.btCadastrarEvento);

        btCancelar = (Button) findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cancela = new Intent(CadastroEvento.this, MainActivity.class);
                startActivity(cancela);
            }
        });

        edtTituloEvento = (EditText) findViewById(R.id.edtTituloEvento);
        edtDataEvento = (EditText) findViewById(R.id.edtDataEvento);
        edtHorarioEvento = (EditText) findViewById(R.id.edtHorarioEvento);
        edtFacilitadorEvento = (EditText) findViewById(R.id.edtFacilitadorEvento);
        edtDescricaoEvento = (EditText) findViewById(R.id.edtDescricaoEvento);

        btCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultadoEvento = new Intent();

                String titulo = edtTituloEvento.getText().toString();
                String descricao = edtDescricaoEvento.getText().toString();
                String facilitador = edtFacilitadorEvento.getText().toString();
                String data = edtDataEvento.getText().toString();
                String hora = edtHorarioEvento.getText().toString();

                if(titulo.equals("") || descricao.equals("") || facilitador.equals("") || data.equals("") || hora.equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Evento cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
