package marcos_matheus.trabalho_dcc196;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import marcos_matheus.trabalho_dcc196.database.DadosOpenHelper;
import marcos_matheus.trabalho_dcc196.dominio.entidades.Evento;
import marcos_matheus.trabalho_dcc196.dominio.entidades.Inscrito;
import marcos_matheus.trabalho_dcc196.dominio.repositorio.EventoRepositorio;
import marcos_matheus.trabalho_dcc196.dominio.repositorio.InscritoRepositorio;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_PARTICIPANTE = 1;
    public static final int REQUEST_EVENTO = 2;

    private Button btNovoEvento;
    private Button btNovoInscrito;

    private RecyclerView lstEventos;
    private RecyclerView lstInscritos;

    private SQLiteDatabase conexao;   // cria conexão com o banco

    private DadosOpenHelper dadosOpenHelper;

    private ConstraintLayout activityMain;

    private EventoRepositorio eventoRepositorio;
    private InscritoRepositorio inscritoRepositorio;

    private EventoAdapter eventoAdapter;
    private InscritoAdapter inscritoAdapter;

//    public static List<Inscrito> inscritos = new ArrayList<Inscrito>(){{
//
//        Inscrito in1 = new Inscrito("Fulano da Silva", "fulano@exemplo.com", "12312312311");
//        Inscrito in2 = new Inscrito("Leon S Kenedy", "revil@umbrella.com", "66622244488");
//        Inscrito in3 = new Inscrito("Tony Stark", "iron@man.com", "00000000000");
//        Inscrito in4 = new Inscrito("Fábio Assunção", "sexta@feira.com", "158473961pt");
//
//        add(in1);
//        add(in2);
//        add(in3);
//        add(in4);
//    }};
//
//    public static List<Evento> eventos = new ArrayList<Evento>(){{
//        Evento ev1 = new Evento("Curso X", "Charles Xavier", "12/12/2030" , "00:00" , "Exemplo de descrição.");
//        Evento ev2 = new Evento("Curso X", "Professor Girafales", "12/12/2030" , "00:00" , "Exemplo de descrição.");
//        Evento ev3 = new Evento("Curso X", "Professor Raimundo", "12/12/2030" , "00:00" , "Exemplo de descrição.");
//        Evento ev4 = new Evento("Curso X", "Ronaldinho", "12/12/2030" , "00:00" , "Exemplo de descrição.");
//
//        add(ev1);
//        add(ev2);
//        add(ev3);
//        add(ev4);
//
//        ev1.getInscritos().add(inscritos.get(1));
//        ev2.getInscritos().add(inscritos.get(1));
//        ev3.getInscritos().add(inscritos.get(1));
//        ev4.getInscritos().add(inscritos.get(1));
//
//        inscritos.get(1).getEventos().add(ev1);
//        inscritos.get(1).getEventos().add(ev1);
//        inscritos.get(1).getEventos().add(ev1);
//        inscritos.get(1).getEventos().add(ev1);
//    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMain = (ConstraintLayout) findViewById(R.id.activityMain);

        criarConexao();

        btNovoEvento = (Button) findViewById(R.id.btNovoEvento);
        btNovoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MainActivity.this, CadastroEvento.class);
                startActivity(it);

            }
        });

        btNovoInscrito = (Button) findViewById(R.id.btNovoInscrito);
        btNovoInscrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MainActivity.this, CadastroParticipante.class);
                startActivity(it);

            }
        });

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        lstEventos = (RecyclerView) findViewById(R.id.lstEventos);
        lstEventos.setLayoutManager(new LinearLayoutManager(this));

        eventoRepositorio = new EventoRepositorio(conexao);
        List<Evento> dadosEvento = eventoRepositorio.buscarEventos();
        eventoAdapter = new EventoAdapter(dadosEvento);
        lstEventos.setAdapter(eventoAdapter);

        lstInscritos = (RecyclerView) findViewById(R.id.lstInscritos);
        lstInscritos.setLayoutManager(new LinearLayoutManager(this));

        inscritoRepositorio = new InscritoRepositorio(conexao);
        List<Inscrito> dadosInscrito = inscritoRepositorio.buscarInscritos();
        inscritoAdapter = new InscritoAdapter(dadosInscrito);
        lstInscritos.setAdapter(inscritoAdapter);






//        lstInscritos = (RecyclerView) findViewById(R.id.lstInscritos);
//        lstInscritos.setLayoutManager(new LinearLayoutManager(this));
//
//        final ControleInscrito controle = new ControleInscrito(inscritos);
//        lstInscritos.setAdapter(controle);
//        controle.setOnParticipanteClickListener(new ControleInscrito.OnInscritoClickListener() {
//            @Override
//            public void onInscritoClick(View view, int position) {
//                Intent intentInscritoDados = new Intent(MainActivity.this, DadosParticipante.class);
//                intentInscritoDados.putExtra("posicao", position);
//                startActivity(intentInscritoDados);
//            }
//        });
//        controle.setOnParticipanteLongClickListener(new ControleInscrito.OnInscritoLongClickListener() {
//            @Override
//            public void onInscritoLongClickListener(View view, int position) {
//                inscritos.remove(position);
//                controle.notifyItemRemoved(position);
//            }
//        });
//
//        lstEventos = (RecyclerView) findViewById(R.id.lstEventos);
//
//        lstEventos.setLayoutManager(new LinearLayoutManager(this));
//
//        final ControleEvento eventoControle = new ControleEvento(eventos);
//        lstEventos.setAdapter(eventoControle);
//        eventoControle.setOnEventoClickListener(new ControleEvento.OnEventoClickListener() {
//            @Override
//            public void onEventoClick(View view, int position) {
//                Intent intentEventoDetalhe = new Intent(MainActivity.this, DadosEvento.class);
//                intentEventoDetalhe.putExtra("posicao", position);
//                startActivity(intentEventoDetalhe);
//            }
//        });
//        eventoControle.setOnEventoLongClickListener(new ControleEvento.OnEventoLongClickListener() {
//            @Override
//            public void onEventoLongClickListener(View view, int position) {
//                eventos.remove(position);
//                eventoControle.notifyItemRemoved(position);
//            }
//        });
    }


    private void criarConexao(){

        try {

            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(activityMain, R.string.message_conexao_criada, Snackbar.LENGTH_SHORT)
                    .setAction(getString(R.string.action_ok), null).show();

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(getString(R.string.title_erro));
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(getString(R.string.action_ok), null);
            dlg.show();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MainActivity.REQUEST_PARTICIPANTE && resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundleResultadoParticipante = data.getExtras();

            Inscrito in = (Inscrito) bundleResultadoParticipante.getSerializable("participante");

        } else if(requestCode == MainActivity.REQUEST_EVENTO && resultCode == Activity.RESULT_OK && data != null) {
            Bundle bundleResultadoEvento = data.getExtras();

            Evento ev = (Evento) bundleResultadoEvento.getSerializable("evento");

        }
    }
}
