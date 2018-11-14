package marcos_matheus.trabalho_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarDadosEvento extends AppCompatActivity {

    private Button btnCancela;
    private Button btnConfirma;

    private EditText edtTituloEditar;
    private EditText edtFacilitadorEditar;
    private EditText edtDescricaoEditar;
    private EditText edtDataEditar;
    private EditText edtHorarioEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dados_evento);

        edtTituloEditar = (EditText) findViewById(R.id.edtTituloEventoEditar);
        edtFacilitadorEditar = (EditText) findViewById(R.id.edtFacilitadorEditar);
        edtDescricaoEditar = (EditText) findViewById(R.id.edtDataEditar);
        edtDataEditar = (EditText) findViewById(R.id.edtDataEditar);
        edtHorarioEditar = (EditText) findViewById(R.id.edtHorarioEditar);

        Bundle bundleDetalhes = getIntent().getExtras();
        final int posicao = bundleDetalhes.getInt("posicao");
        Evento e = MainActivity.eventos.get(posicao);
        edtTituloEditar.setText(e.getTitulo());
        edtFacilitadorEditar.setText(e.getFacilitador());
        edtDescricaoEditar.setText(e.getDescricao());
        edtDataEditar.setText(e.getData());
        edtHorarioEditar.setText(e.getHora());

        btnCancela = (Button) findViewById(R.id.btCancelaEdicaoEvento);
        btnCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditarDadosEvento.this, DadosEvento.class);
                intent.putExtra("posicao", posicao);
                startActivity(intent);
            }
        });

        btnConfirma = (Button) findViewById(R.id.btConfirmaEdicaoEvento);
        btnConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultadoEventoEditar = new Intent(EditarDadosEvento.this, DadosEvento.class);
                resultadoEventoEditar.putExtra("posicao", posicao);

                String titulo = edtTituloEditar.getText().toString();
                String facilitador = edtFacilitadorEditar.getText().toString();
                String descricao = edtDescricaoEditar.getText().toString();
                String data = edtDataEditar.getText().toString();
                String hora = edtHorarioEditar.getText().toString();

                if(titulo.equals("") || descricao.equals("") || facilitador.equals("") || data.equals("") || hora.equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    startActivity(resultadoEventoEditar);
                } else {
                    MainActivity.eventos.get(posicao).setTitulo(titulo);
                    MainActivity.eventos.get(posicao).setFacilitador(facilitador);
                    MainActivity.eventos.get(posicao).setFacilitador(descricao);
                    MainActivity.eventos.get(posicao).setFacilitador(data);
                    MainActivity.eventos.get(posicao).setFacilitador(hora);

                    Toast.makeText(getApplicationContext(), "Evento atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(resultadoEventoEditar);
                }
            }
        });
    }
}

