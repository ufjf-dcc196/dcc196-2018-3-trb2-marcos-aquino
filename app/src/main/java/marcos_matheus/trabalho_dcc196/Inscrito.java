package marcos_matheus.trabalho_dcc196;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inscrito implements Serializable {

    private String nome;
    private String cpf;
    private String email;

    private List<Evento> eventos;

    public Inscrito() {
    }

    public Inscrito(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.eventos = new ArrayList<Evento>();
    }

    public Inscrito(String nome, String email, String cpf, List<Evento> eventos) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.eventos = eventos;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Evento> getEventos() {
        return eventos;
    }
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


}
