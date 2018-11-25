package marcos_matheus.trabalho_dcc196.database;

public class ScriptDados {

    public static String getCreateTableEventos(){

        StringBuilder sql = new StringBuilder();


        sql.append("CREATE TABLE Eventos( ");

        sql.append("CODIGO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("NOME VARCHAR(250), ");
        sql.append("DATA DATE, ");
        sql.append("HORARIO TIME, ");
        sql.append("FACILITADOR VARCHAR(250), ");
        sql.append("DESCRICAO VARCHAR(250) ) ");

        return sql.toString();

    }

}
