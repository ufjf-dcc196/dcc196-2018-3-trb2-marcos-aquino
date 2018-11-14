package marcos_matheus.trabalho_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarDadosInscrito extends AppCompatActivity {

    private Button btnCancelaEdicao;
    private Button btnConfirmaEdicao;
    private EditText edtNomeEditar;
    private EditText edtCpfEditar;
    private EditText edtEmailEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dados_inscrito);

        edtNomeEditar = (EditText) findViewById(R.id.edtNomeInscritoEditar);
        edtEmailEditar = (EditText) findViewById(R.id.edtEmailInscritoEditar);
        edtCpfEditar = (EditText) findViewById(R.id.edtCpfInscritoEditar);

        Bundle bundleDetalhes = getIntent().getExtras();
        final int posicao = bundleDetalhes.getInt("posicao");
        Inscrito atual = MainActivity.inscritos.get(posicao);
        edtNomeEditar.setText(atual.getNome());
        edtEmailEditar.setText(atual.getEmail());
        edtCpfEditar.setText(atual.getCpf());

        btnCancelaEdicao = (Button) findViewById(R.id.btCancelaEdicaoInscrito);
        btnCancelaEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditarDadosInscrito.this, DadosParticipante.class);
                intent.putExtra("posicao", posicao);
                startActivity(intent);
            }
        });

        btnConfirmaEdicao = (Button) findViewById(R.id.btConfirmaEdicaoInscrito);
        btnConfirmaEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultadoParticipante = new Intent(EditarDadosInscrito.this, DadosParticipante.class);
                resultadoParticipante.putExtra("posicao", posicao);

                String nome = edtNomeEditar.getText().toString();
                String email = edtEmailEditar.getText().toString();
                String cpf = edtCpfEditar.getText().toString();

                if(nome.equals("") || email.equals("") || cpf.equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    startActivity(resultadoParticipante);
                } else {

                    MainActivity.inscritos.get(posicao).setCpf(cpf);
                    MainActivity.inscritos.get(posicao).setEmail(email);
                    MainActivity.inscritos.get(posicao).setNome(nome);

                    Toast.makeText(getApplicationContext(), "Participante atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(resultadoParticipante);
                }
            }
        });
    }
}
