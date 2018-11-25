package marcos_matheus.trabalho_dcc196.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import marcos_matheus.trabalho_dcc196.dominio.entidades.Evento;

public class EventoRepositorio {

    private SQLiteDatabase conexão;

    public EventoRepositorio(SQLiteDatabase conexão){
        this.conexão = conexão;
    }

    public void inserirEvento(Evento evento){

        ContentValues contentValues = new ContentValues();

        contentValues.put("TITULO", evento.titulo);
        contentValues.put("DATA", evento.data);
        contentValues.put("HORA", evento.hora);
        contentValues.put("FACILITADOR", evento.facilitador);
        contentValues.put("DESCRICAO", evento.descricao);

        conexão.insertOrThrow("EVENTOS", null, contentValues);
    }

    public void excluirEvento(int codigo){

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        conexão.delete("EVENTOS","CODIGO = ?", paramentros);

    }

    public void alterarEvento(Evento evento){

        ContentValues contentValues = new ContentValues();

        contentValues.put("TITULO", evento.titulo);
        contentValues.put("DATA", evento.data);
        contentValues.put("HORA", evento.hora);
        contentValues.put("FACILITADOR", evento.facilitador);
        contentValues.put("DESCRICAO", evento.descricao);

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(evento.codigo);

        conexão.update("EVENTOS", contentValues, "CODIGO = ?", paramentros);

    }

    public Evento buscarEvento(int codigo){

        Evento evento = new Evento();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT TITULO, DATA, HORA, FACILITADOR, DESCRICAO ");
        sql.append(" FROM EVENTOS ");
        sql.append(" WHERE CODIGO = ? ");

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        Cursor resultado = conexão.rawQuery(sql.toString(), paramentros);

        if(resultado.getCount() > 0) {

            resultado.moveToFirst();

            evento.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
            evento.titulo = resultado.getString(resultado.getColumnIndexOrThrow("TITULO"));
            evento.data = resultado.getString(resultado.getColumnIndexOrThrow("DATA"));
            evento.hora = resultado.getString(resultado.getColumnIndexOrThrow("HORA"));
            evento.facilitador = resultado.getString(resultado.getColumnIndexOrThrow("FACILITADOR"));
            evento.descricao = resultado.getString(resultado.getColumnIndexOrThrow("DESCRICAO"));

            return evento;
        }

        return null;
    }

    public List<Evento> buscarEventos(){

        List<Evento> eventos = new ArrayList<Evento>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT TITULO, DATA, HORA, FACILITADOR, DESCRICAO ");
        sql.append(" FROM EVENTOS ");


        Cursor resultado = conexão.rawQuery(sql.toString(), null);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();
        }

        do{

            Evento ev = new Evento();

            ev.codigo = resultado.getInt( resultado.getColumnIndexOrThrow("CODIGO") );
            ev.titulo = resultado.getString( resultado.getColumnIndexOrThrow("TITULO") );
            ev.data = resultado.getString( resultado.getColumnIndexOrThrow("DATA") );
            ev.hora = resultado.getString( resultado.getColumnIndexOrThrow("HORA") );
            ev.facilitador = resultado.getString( resultado.getColumnIndexOrThrow("FACILITADOR") );
            ev.descricao = resultado.getString( resultado.getColumnIndexOrThrow("DESCRICAO") );

            eventos.add(ev);

        }while (resultado.moveToNext());

        return eventos;
    }
}
