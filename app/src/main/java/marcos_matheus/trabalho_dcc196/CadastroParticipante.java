package marcos_matheus.trabalho_dcc196;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import marcos_matheus.trabalho_dcc196.database.DadosOpenHelper;
import marcos_matheus.trabalho_dcc196.dominio.entidades.Inscrito;
import marcos_matheus.trabalho_dcc196.dominio.repositorio.InscritoRepositorio;


public class CadastroParticipante extends AppCompatActivity {

    Button btCadastrarParticipante;
    Button btCancelar;

    EditText edtNomeParticipante;
    EditText edtEmailParticipante;
    EditText edtCpfParticipante;

    InscritoRepositorio inscritoRepositorio;

    private SQLiteDatabase conexao;   // cria conexão com o banco

    private DadosOpenHelper dadosOpenHelper;

    private ConstraintLayout actCadParticipante;

    private Inscrito inscrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);

        edtNomeParticipante = (EditText)findViewById(R.id.edtNomeParticipante);
        edtEmailParticipante = (EditText)findViewById(R.id.edtEmailParticipante);
        edtCpfParticipante = (EditText)findViewById(R.id.edtCpfParticipante);

        btCancelar = (Button) findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        btCadastrarParticipante = (Button)findViewById(R.id.btCadastrarParticipante);
        btCadastrarParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultadoParticipante = new Intent();

//                String nome = edtNomeParticipante.getText().toString();
//                String email = edtEmailParticipante.getText().toString();
//                String cpf = edtCpfParticipante.getText().toString();

                //if(validaCampos()) {
                  //  Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    //finish();
                //} else {
                confirmarCadastroParticipante();
//                Toast.makeText(getApplicationContext(), "Inscrito com sucesso!", Toast.LENGTH_SHORT).show();
//                finish();
                //}
            }
        });

        actCadParticipante = (ConstraintLayout)findViewById(R.id.actCadParticipante);

        criarConexao();
    }

    private void criarConexao(){

        try {

            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(actCadParticipante, R.string.message_conexao_criada, Snackbar.LENGTH_SHORT)
                    .setAction(getString(R.string.action_ok), null).show();

            inscritoRepositorio = new InscritoRepositorio(conexao);

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(getString(R.string.title_erro));
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(getString(R.string.action_ok), null);
            dlg.show();

        }

    }

    private void confirmarCadastroParticipante(){

        inscrito = new Inscrito();

        if(!validaCampos()){

            try {

                inscritoRepositorio.inserirInscrito(inscrito);
                Toast.makeText(getApplicationContext(), "Inscrito com sucesso!", Toast.LENGTH_LONG).show();
                finish();

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

        String nome = edtNomeParticipante.getText().toString();
        String email = edtEmailParticipante.getText().toString();
        String cpf = edtCpfParticipante.getText().toString();

        inscrito.nome = nome;
        inscrito.email = email;
        inscrito.cpf = cpf;

        if(res = isCampoVazio(nome)){
            edtNomeParticipante.requestFocus();
        }
        else
        if(res = !isEmailValido(email)){
            edtEmailParticipante.requestFocus();
        }
        else
        if(res = isCampoVazio(cpf)){
            edtCpfParticipante.requestFocus();
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

    // verifica se o email é válido
    private boolean isEmailValido(String email){

        boolean resultado = ( !isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() );
        return resultado;
    }
}
