//package marcos_matheus.trabalho_dcc196;
//
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.support.constraint.ConstraintLayout;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import marcos_matheus.trabalho_dcc196.database.DadosOpenHelper;
//import marcos_matheus.trabalho_dcc196.dominio.entidades.Evento;
//import marcos_matheus.trabalho_dcc196.dominio.repositorio.EventoRepositorio;
//
//public class DadosEvento extends AppCompatActivity {
//
//    private Button btVoltar;
//    private Button btEditarEvento;
//
//    private TextView txtTituloAtual;
//    private TextView txtFacilitadorAtual;
//    private TextView txtDataAtual;
//    private TextView txtHorarioAtual;
//    private TextView txtDescricaoAtual;
//
//    private RecyclerView lstInscritosAtual;
//
//    EventoRepositorio eventoRepositorio;
//    private SQLiteDatabase conexao;   // cria conexão com o banco
//    private DadosOpenHelper dadosOpenHelper;
//    private ConstraintLayout actDadosEvento;
//    private Evento evento;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dados_evento);
//
//        txtTituloAtual = (TextView) findViewById(R.id.txtTituloAtual);
//        txtFacilitadorAtual = (TextView) findViewById(R.id.txtFacilitadorAtual);
//        txtDataAtual = (TextView) findViewById(R.id.txtDataAtual);
//        txtHorarioAtual = (TextView) findViewById(R.id.txtHorarioAtual);
//        txtDescricaoAtual = (TextView) findViewById(R.id.txtDescricaoAtual);
//
//        btVoltar = (Button) findViewById(R.id.btVoltar);
//        btVoltar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        btEditarEvento = (Button) findViewById(R.id.btEditarEvento);
//        btEditarEvento.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent it = new Intent(DadosEvento.this, EditarDadosEvento.class);
//                it.putExtra("EVENTO", evento);
//                startActivity(it);
//                //((AppCompatActivity)context).startActivityForResult(it, 0);
//
//
//
//
////                Intent intentEditar = new Intent(DadosEvento.this, EditarDadosEvento.class);
////                intentEditar.putExtra("posicao", posicao);
////                startActivity(intentEditar);
//            }
//        });
////
//
//
//        actDadosEvento = (ConstraintLayout)findViewById(R.id.actDadosEvento);
//
//        criarConexao();
//        verificaParametro();
//    }
//
//    private void verificaParametro(){
//
//        Bundle bundleEvento = getIntent().getExtras();
//
//        if((bundleEvento != null) && (bundleEvento.containsKey("EVENTO"))){
//
//            Evento evento = (Evento)bundleEvento.getSerializable("EVENTO");
//            txtTituloAtual.setText(evento.titulo);
//            txtFacilitadorAtual.setText(evento.facilitador);
//            txtDataAtual.setText(evento.data);
//            txtHorarioAtual.setText(evento.hora);
//            txtDescricaoAtual.setText(evento.descricao);
//
//        }
//
////        final int posicao = bundleDetalhes.getInt("posicao");
////        Evento e = MainActivity.eventos.get(posicao);
////        edtTituloEvento.setText(e.getTitulo());
////        edtFacilitadorEvento.setText(e.getFacilitador());
////        edtDescricaoEvento.setText(e.getDescricao());
////        edtDataEvento.setText(e.getData());
////        edtHorarioEvento.setText(e.getHora());
////
////        lstParticipantesInscritos = (RecyclerView) findViewById(R.id.lstInscritos);
////        lstParticipantesInscritos.setLayoutManager(new LinearLayoutManager(this));
////
////
////        final ControleInscrito controleInscrito = new ControleInscrito(e.getInscritos());
////        lstParticipantesInscritos.setAdapter(controleInscrito);
//
//
//
//    }
//
//    private void criarConexao(){
//
//        try {
//
//            dadosOpenHelper = new DadosOpenHelper(this);
//
//            conexao = dadosOpenHelper.getWritableDatabase();
//
//            Snackbar.make(actDadosEvento, R.string.message_conexao_criada, Snackbar.LENGTH_SHORT)
//                    .setAction(getString(R.string.action_ok), null).show();
//
//            eventoRepositorio = new EventoRepositorio(conexao);
//
//        }catch (SQLException ex){
//
//            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
//            dlg.setTitle(getString(R.string.title_erro));
//            dlg.setMessage(ex.getMessage());
//            dlg.setNeutralButton(getString(R.string.action_ok), null);
//            dlg.show();
//
//        }
//    }
//
//}
