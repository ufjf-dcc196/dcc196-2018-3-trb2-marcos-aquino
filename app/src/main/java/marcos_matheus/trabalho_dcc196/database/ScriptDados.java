package marcos_matheus.trabalho_dcc196.database;

public class ScriptDados {

    public static String getCreateTableEventos(){

        StringBuilder sql = new StringBuilder();


        sql.append(" CREATE TABLE IF NOT EXISTS EVENTOS( ");

        sql.append("CODIGO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("NOME VARCHAR (250) NOT NULL DEFAULT, ");
        sql.append("DATA DATE NOT NULL DEFAULT, ");
        sql.append("HORARIO TIME NOT NULL DEFAULT, ");
        sql.append("FACILITADOR VARCHAR (250) NOT NULL DEFAULT, ");
        sql.append("DESCRICAO VARCHAR (250) NOT NULL DEFAULT, ) ");

        return sql.toString();

    }

}
