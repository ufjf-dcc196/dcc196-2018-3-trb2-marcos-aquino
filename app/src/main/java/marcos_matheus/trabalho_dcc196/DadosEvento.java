package marcos_matheus.trabalho_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DadosEvento extends AppCompatActivity {

    private Button btVoltar;
    private Button btEditarEvento;

    private EditText edtTituloEvento;
    private EditText edtDataEvento;
    private EditText edtHorarioEvento;
    private EditText edtFacilitadorEvento;
    private EditText edtDescricaoEvento;

    private RecyclerView lstParticipantesInscritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_evento);

        edtTituloEvento = (EditText) findViewById(R.id.edtTituloEvento);
        edtFacilitadorEvento = (EditText) findViewById(R.id.edtFacilitadorEvento);
        edtDescricaoEvento = (EditText) findViewById(R.id.edtDescricaoEvento);
        edtDataEvento = (EditText) findViewById(R.id.edtDataEvento);
        edtHorarioEvento = (EditText) findViewById(R.id.edtHorarioEvento);

        btVoltar = (Button) findViewById(R.id.btVoltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DadosEvento.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundleDetalhes = getIntent().getExtras();
        final int posicao = bundleDetalhes.getInt("posicao");
        Evento e = MainActivity.eventos.get(posicao);
        edtTituloEvento.setText(e.getTitulo());
        edtFacilitadorEvento.setText(e.getFacilitador());
        edtDescricaoEvento.setText(e.getDescricao());
        edtDataEvento.setText(e.getData());
        edtHorarioEvento.setText(e.getHora());

        lstParticipantesInscritos = (RecyclerView) findViewById(R.id.lstInscritos);
        lstParticipantesInscritos.setLayoutManager(new LinearLayoutManager(this));


        final ControleInscrito controleInscrito = new ControleInscrito(e.getInscritos());
        lstParticipantesInscritos.setAdapter(controleInscrito);

        btEditarEvento = (Button) findViewById(R.id.btEditarEvento);
        btEditarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEditar = new Intent(DadosEvento.this, EditarDadosEvento.class);
                intentEditar.putExtra("posicao", posicao);
                startActivity(intentEditar);
            }
        });
    }
}
