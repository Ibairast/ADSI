package packModelo;

import java.io.File;
import java.sql.*;

public class SGBD {

    private static SGBD miSGBD;
    private Connection c;
    private Statement s;

    private SGBD(){
        File f = new File("barbes.db");
        if (!f.exists()) {
            this.crearBD();
            this.crearTablas();
            this.pruebasRanking();
            this.pruebasUsuarios();
        }
    }

    public static SGBD getMiSGBD() {
        if (miSGBD == null) {
            miSGBD = new SGBD();
        }
        return miSGBD;
    }

    private void crearBD() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Base de datos creada");
    }

    private void crearTablas() { // AÑADIR TABLAS QUE FALTAN //
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");

            s = c.createStatement();
            String usuario = "CREATE TABLE USUARIO" +
                    "(IdUsuario VARCHAR(100) NOT NULL, " +
                    "Pass VARCHAR(100) NOT NULL, " +
                    "Admin BOOLEAN NOT NULL, " +
                    "LogFecha DATE NOT NULL, " +
                    "Ayuda INT(11) NOT NULL, " +

                    "PRIMARY KEY(IdUsuario))";

            String ranking = "CREATE TABLE RANKING" +
                    "(IdRanking INT(11) NOT NULL, " +
                    "IdUsuario VARCHAR(100) NOT NULL, " +
                    "Puntuacion INT(11) NOT NULL, " +
                    "Fecha DATE NOT NULL, " +
                    "Gana BOOLEAN NOT NULL, " +

                    "PRIMARY KEY(IdRanking), " +
                    "FOREIGN KEY (IdUsuario) REFERENCES USUARIO(IdUsuario))";

            s.executeUpdate(usuario);
            s.executeUpdate(ranking);
            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Tablas creadas");
    }

    private void pruebasRanking() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            s = c.createStatement();
            String sql1 = "INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(1, 'Andrea', 10, strftime('%Y-%m-%d'), 'true')";

            String sql2 = "INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(2, 'Andrea', 20, strftime('%Y-%m-%d'), 'true')";

            String sql3 = "INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(3, 'David', 5, strftime('%Y-%m-%d'), 'true')";

            s.executeUpdate(sql1);
            s.executeUpdate(sql2);
            s.executeUpdate(sql3);

            s.close();
            c.commit();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Insertados datos ranking");

    }
    private void pruebasUsuarios() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            s = c.createStatement();
            String sql1 = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    "VALUES('Josu', 'Josu','true','1995-10-10',1)";

            String sql2 = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    "VALUES('Usoj', 'Usoj','false','1995-10-10',1)";

            String sql3 = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    "VALUES('Pedro', 'Pedro','false','1995-10-10',1)";

            s.executeUpdate(sql1);
            s.executeUpdate(sql2);
            s.executeUpdate(sql3);

            s.close();
            c.commit();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Insertados datos en usuario");

    }
}

