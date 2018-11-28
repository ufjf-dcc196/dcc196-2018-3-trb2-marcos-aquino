package marcos_matheus.trabalho_dcc196;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import marcos_matheus.trabalho_dcc196.database.DadosOpenHelper;
import marcos_matheus.trabalho_dcc196.dominio.entidades.Evento;
import marcos_matheus.trabalho_dcc196.dominio.repositorio.EventoRepositorio;

public class CadastroEvento extends AppCompatActivity {

    Button btCadastrarEvento;
    Button btExcluir;

    EditText edtTituloEvento;
    EditText edtDataEvento;
    EditText edtHorarioEvento;
    EditText edtFacilitadorEvento;
    EditText edtDescricaoEvento;

    EventoRepositorio eventoRepositorio;

    private SQLiteDatabase conexao;   // cria conexão com o banco

    private DadosOpenHelper dadosOpenHelper;

    private ConstraintLayout actCadEvento;

    private Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        edtTituloEvento = (EditText) findViewById(R.id.edtTituloCadEvento);
        edtDataEvento = (EditText) findViewById(R.id.edtDataCadEvento);
        edtHorarioEvento = (EditText) findViewById(R.id.edtHorarioCadEvento);
        edtFacilitadorEvento = (EditText) findViewById(R.id.edtFacilitadorCadEvento);
        edtDescricaoEvento = (EditText) findViewById(R.id.edtDescricaoCadEvento);



        btExcluir = (Button) findViewById(R.id.btExcluir);
        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eventoRepositorio.excluirEvento(evento.codigo);
                Toast.makeText(getApplicationContext(), "Evento excluído", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        btCadastrarEvento = (Button) findViewById(R.id.btCadastrarEvento);
        btCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent resultadoEvento = new Intent();

//                String titulo = edtTituloEvento.getText().toString();
//                String descricao = edtDescricaoEvento.getText().toString();
//                String facilitador = edtFacilitadorEvento.getText().toString();
//                String data = edtDataEvento.getText().toString();
//                String hora = edtHorarioEvento.getText().toString();

                //if(validaCampos()) {
                  //  Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    //finish();
                //}
                //else {
                    confirmarCadastroEvento();

                //}
            }
        });

        actCadEvento = (ConstraintLayout)findViewById(R.id.actCadEvento);

        criarConexao();
        verificaParametro();
    }

    private void verificaParametro() {    // verifica parametros recebidos

        Bundle bundleEvento = getIntent().getExtras();

        if ((bundleEvento != null) && (bundleEvento.containsKey("EVENTO"))) {

            evento = (Evento) bundleEvento.getSerializable("EVENTO");

            edtTituloEvento.setText(evento.titulo);
            edtDataEvento.setText(evento.data);
            edtHorarioEvento.setText(evento.hora);
            edtFacilitadorEvento.setText(evento.facilitador);
            edtDescricaoEvento.setText(evento.descricao);

//            txtTituloAtual.setText(evento.titulo);
//            txtFacilitadorAtual.setText(evento.facilitador);
//            txtDataAtual.setText(evento.data);
//            txtHorarioAtual.setText(evento.hora);
//            txtDescricaoAtual.setText(evento.descricao);

        }
    }

    private void criarConexao(){

        try {

            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(actCadEvento, R.string.message_conexao_criada, Snackbar.LENGTH_SHORT)
                    .setAction(getString(R.string.action_ok), null).show();

            eventoRepositorio = new EventoRepositorio(conexao);

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(getString(R.string.title_erro));
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(getString(R.string.action_ok), null);
            dlg.show();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void confirmarCadastroEvento(){

        //evento = new Evento();

        if(!validaCampos()){

            try {

                if(evento.codigo == 0){

                    eventoRepositorio.inserirEvento(evento);
                    Toast.makeText(getApplicationContext(), "Evento cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                    //finish();
                }else {
                    eventoRepositorio.alterarEvento(evento);
                    Toast.makeText(getApplicationContext(), "Evento editado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                }

            }catch (SQLException ex){

                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(getString(R.string.title_erro));
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton(getString(R.string.action_ok), null);
                dlg.show();

            }
        }

    }


    // valida todos os campos do cadastro
    private boolean validaCampos(){

        boolean res = false;

        String titulo = edtTituloEvento.getText().toString();
        String data = edtDataEvento.getText().toString();
        String horario = edtHorarioEvento.getText().toString();
        String facilitador = edtFacilitadorEvento.getText().toString();
        String descricao = edtFacilitadorEvento.getText().toString();

        evento.titulo = titulo;
        evento.data = data;
        evento.hora = horario;
        evento.facilitador = facilitador;
        evento.descricao = descricao;

        if(res = isCampoVazio(titulo)){
            edtTituloEvento.requestFocus();
        }
        else
        if(res = isCampoVazio(data)){
            edtDataEvento.requestFocus();
        }
        else
        if(res = isCampoVazio(horario)){
            edtHorarioEvento.requestFocus();
        }
        else
        if(res = isCampoVazio(facilitador)){
            edtFacilitadorEvento.requestFocus();
        }
        else
        if(res = isCampoVazio(descricao)){
            edtDescricaoEvento.requestFocus();
        }

        if(res){

            android.support.v7.app.AlertDialog.Builder dlg = new android.support.v7.app.AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_atencao);
            dlg.setMessage(R.string.message_campos_nao_preenchidos);
            dlg.setNeutralButton(R.string.lbl_ok, null);
            dlg.show();
        }

        return res;
    }

    // verifica se campo está preenchido ou vazio
    private boolean isCampoVazio(String valor){

        boolean resultado = ( TextUtils.isEmpty(valor) || valor.trim().isEmpty() );
        return resultado;
    }
}
