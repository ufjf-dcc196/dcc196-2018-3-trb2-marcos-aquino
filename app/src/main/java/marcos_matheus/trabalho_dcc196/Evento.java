package marcos_matheus.trabalho_dcc196;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Evento implements Serializable {

    private String titulo;
    private String facilitador;
    private String data;
    private String hora;
    private String descricao;
    private List<Inscrito> inscritos;

    public Evento() {
    }

    public Evento(String titulo, String facilitador, String data, String hora, String descricao) {
        this.titulo = titulo;
        this.facilitador = facilitador;
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.inscritos = new ArrayList<Inscrito>();
    }

    public Evento(String titulo, String data, String hora, String facilitador, String descricao, List<Inscrito> inscritos) {
        this.titulo = titulo;
        this.data = data;
        this.hora = hora;
        this.facilitador = facilitador;
        this.descricao = descricao;
        this.inscritos = inscritos;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getFacilitador() {
        return facilitador;
    }
    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public List<Inscrito> getInscritos() {
        return inscritos;
    }
    public void setInscritos(List<Inscrito> inscritos) {
        this.inscritos = inscritos;
    }
}
