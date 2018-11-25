package marcos_matheus.trabalho_dcc196.dominio.repositorio;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import marcos_matheus.trabalho_dcc196.dominio.entidades.Inscrito;

public class InscritoRepositorio {

    private SQLiteDatabase conexão;

    public InscritoRepositorio(SQLiteDatabase conexão){
        this.conexão = conexão;
    }

    public void inserirInscrito(Inscrito inscrito){

        ContentValues contentValues = new ContentValues();

        contentValues.put("NOME", inscrito.nome);
        contentValues.put("EMAIL", inscrito.email);
        contentValues.put("CPF", inscrito.cpf);

        conexão.insertOrThrow("INSCRITOS", null, contentValues);

    }

    public void excluirInscrito(int codigo){

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        conexão.delete("INSCRITOS", "CODIGO = ?", paramentros);
    }

    public void alterarInscrito(Inscrito inscrito){

        ContentValues contentValues = new ContentValues();

        contentValues.put("NOME", inscrito.nome);
        contentValues.put("EMAIL", inscrito.email);
        contentValues.put("CPF", inscrito.cpf);

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(inscrito.codigo);

        conexão.update("INSCRITOS", contentValues, "CODIGO = ?", paramentros);

    }

    public Inscrito buscarInscrito(int codigo){


        return null;
    }

    public List<Inscrito> buscarInscritos(){


        return null;
    }


}
