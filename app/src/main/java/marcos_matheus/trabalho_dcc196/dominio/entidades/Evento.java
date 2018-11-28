package marcos_matheus.trabalho_dcc196.dominio.entidades;

import java.io.Serializable;

public class Evento implements Serializable {

    public int codigo;
    public String titulo;
    public String facilitador;
    public String data;
    public String hora;
    public String descricao;

    public Evento(){
        codigo = 0;
    }
}
