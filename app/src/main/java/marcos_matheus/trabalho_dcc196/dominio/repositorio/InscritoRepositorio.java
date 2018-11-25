package marcos_matheus.trabalho_dcc196.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import marcos_matheus.trabalho_dcc196.dominio.entidades.Evento;
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

        Inscrito inscrito = new Inscrito();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT NOME, EMAIL, CPF ");
        sql.append(" FROM INSCRITOS ");
        sql.append(" WHERE CODIGO = ? ");

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        Cursor resultado = conexão.rawQuery(sql.toString(), paramentros);

        if(resultado.getCount() > 0) {

            resultado.moveToFirst();

            inscrito.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
            inscrito.nome = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
            inscrito.email = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
            inscrito.cpf = resultado.getString(resultado.getColumnIndexOrThrow("CPF"));

            return inscrito;
        }

        return null;
    }

    public List<Inscrito> buscarInscritos(){

        List<Inscrito> inscritos = new ArrayList<Inscrito>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT NOME, EMAIL, CPF ");
        sql.append(" FROM INSCRITOS ");


        Cursor resultado = conexão.rawQuery(sql.toString(), null);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();
        }

        do{

            Inscrito in = new Inscrito();

            in.codigo = resultado.getInt( resultado.getColumnIndexOrThrow("CODIGO") );
            in.nome = resultado.getString( resultado.getColumnIndexOrThrow("NOME") );
            in.email = resultado.getString( resultado.getColumnIndexOrThrow("EMAIL") );
            in.cpf = resultado.getString( resultado.getColumnIndexOrThrow("CPF") );

            inscritos.add(in);

        }while (resultado.moveToNext());

        return inscritos;
    }
}
