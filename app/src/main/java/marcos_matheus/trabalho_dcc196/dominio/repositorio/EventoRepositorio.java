package marcos_matheus.trabalho_dcc196.dominio.repositorio;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

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

        return null;
    }

    public List<Evento> buscarEventos(){


        return null;
    }


}
