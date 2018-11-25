package marcos_matheus.trabalho_dcc196.database;

public class ScriptDados {

    public static String getCreateTableEventos(){

        StringBuilder sql = new StringBuilder();


        sql.append("CREATE TABLE EVENTOS( ");

        sql.append("CODIGO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("TITULO VARCHAR(250), ");
        sql.append("DATA VARCHAR(50), ");
        sql.append("HORA VARCHAR(50), ");
        sql.append("FACILITADOR VARCHAR(250), ");
        sql.append("DESCRICAO VARCHAR(250) ) ");

        return sql.toString();

    }

    public static String getCreateTableInscritos(){

        StringBuilder sql = new StringBuilder();


        sql.append("CREATE TABLE INSCRITOS( ");

        sql.append("CODIGO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("NOME VARCHAR(250), ");
        sql.append("EMAIL VARCHAR(250), ");
        sql.append("CPF VARCHAR(11) ) ");

        return sql.toString();

    }

}
