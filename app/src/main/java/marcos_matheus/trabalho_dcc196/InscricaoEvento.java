package marcos_matheus.trabalho_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InscricaoEvento extends AppCompatActivity {

    private Button btVoltar;
    private Button btConfirmaInscricao;

    private EditText edtTitulo;
    private EditText edtFacilitador;
    private EditText edtDescricao;
    private EditText edtData;
    private EditText edtHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricao_evento);

        edtTitulo = (EditText) findViewById(R.id.edtTituloEvento);
        edtFacilitador = (EditText) findViewById(R.id.edtFacilitadorEvento);
        edtDescricao = (EditText) findViewById(R.id.edtDescricaoEvento);
        edtData = (EditText) findViewById(R.id.edtDataEvento);
        edtHorario = (EditText) findViewById(R.id.edtHorarioEvento);

        Bundle bundle = getIntent().getExtras();
        final int posicao = bundle.getInt("posicao");
        final int idParticipante = bundle.getInt("idParticipante");
        final Evento e = MainActivity.eventos.get(posicao);
        final Inscrito ins = MainActivity.inscritos.get(idParticipante);
        edtTitulo.setText(e.getTitulo());
        edtFacilitador.setText(e.getFacilitador());
        edtDescricao.setText(e.getDescricao());
        edtData.setText(e.getData());
        edtHorario.setText(e.getHora());

        btVoltar = (Button) findViewById(R.id.btVoltarInscricao);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InscricaoEvento.this, ListaEventos.class);
                intent.putExtra("posicao", idParticipante);
                startActivity(intent);
            }
        });

        btConfirmaInscricao = (Button) findViewById(R.id.btConfirmaInscricao);
        btConfirmaInscricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InscricaoEvento.this, DadosParticipante.class);
                intent.putExtra("posicao", idParticipante);

                if(ins.getEventos().contains(e)) {
                    Toast.makeText(getApplicationContext(), "Participante já está inscrito neste evento", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    MainActivity.eventos.get(posicao).getInscritos().add(ins);
                    MainActivity.inscritos.get(idParticipante).getEventos().add(e);
                    Toast.makeText(getApplicationContext(), "Inscrito com sucesso!" + ins.getNome(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }
}
