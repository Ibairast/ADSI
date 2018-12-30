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
                    "VALUES(1, 'Andrea', 10, datetime('now'), 'true')";


            s.executeUpdate(sql1);

            s.close();
            c.commit();
            c.close();

/*            PreparedStatement pstmt1 = c.prepareStatement(sql1);
            pstmt1.setInt(1, 1);
            pstmt1.setString(2, "Andrea");
            pstmt1.setInt(3, 10);
            pstmt1.setString(4, "2018/12/28");
            //pstmt1.setDate(4, Date.valueOf("2018/12/28"));
            pstmt1.setBoolean(5, true);

            pstmt1.executeUpdate();

            String sql2 = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmt2 = c.prepareStatement(sql2);
            pstmt2.setString(1, "Andrea");
            pstmt2.setString(2, "0710");
            pstmt2.setBoolean(3, false);
            pstmt2.setString(4, "2018/12/28");
            //pstmt2.setDate(4, Date.valueOf("2018/12/28"));
            pstmt2.setInt(5, 2);

            pstmt2.executeUpdate();*/


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Insertados datos ranking");

    }
}

