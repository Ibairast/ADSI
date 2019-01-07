package packModelo;

import java.io.File;
import java.sql.*;

public class SGBD {

    private static SGBD miSGBD;

    private SGBD() {
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
        try (Connection con =
                     DriverManager.getConnection("jdbc:sqlite:barbes.db")) {
            if (con != null) {
                System.out.println("La bd ha sido creada.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection conectarBD() {
        String url = "jdbc:sqlite:barbes.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void crearTablas() { // AÑADIR TABLAS QUE FALTAN //
        String usuario = "CREATE TABLE Usuario" +
                "(IdUsuario VARCHAR(100) NOT NULL, " +
                "Pass VARCHAR(100) NOT NULL, " +
                "Admin INT(1) NOT NULL, " +
                "LogFecha DATE NOT NULL, " +
                "Ayuda INT(11) NOT NULL, " +

                "PRIMARY KEY(IdUsuario))";

        String ranking = "CREATE TABLE Ranking" +
                "(IdRanking INT(11) NOT NULL, " +
                "IdUsuario VARCHAR(100) NOT NULL, " +
                "Puntuacion INT(11) NOT NULL, " +
                "Fecha DATE NOT NULL, " +
                "Gana INT(1) NOT NULL, " +

                "PRIMARY KEY(IdRanking), " +
                "FOREIGN KEY (IdUsuario) REFERENCES USUARIO(IdUsuario))";

        String partida ="CREATE TABLE `Partida` ( `IdPartida` TEXT NOT NULL, `IdUsuario` TEXT NOT NULL, `IdMazoP` TEXT, `NAyudas` INTEGER, PRIMARY KEY(`IdPartida`,`IdUsuario`), FOREIGN KEY(`IdUsuario`) REFERENCES `USUARIO`(`IdUsuario`) )";
        String cartas = "CREATE TABLE `Cartas` ( `IdCartas` TEXT NOT NULL, `IdUsuario` TEXT NOT NULL, `Grupo` TEXT, `Color` TEXT, `Animal` TEXT, FOREIGN KEY(`IdCartas`) REFERENCES `Partida`(`IdPartida`), PRIMARY KEY(`IdCartas`,`IdUsuario`), FOREIGN KEY(`IdUsuario`) REFERENCES `USUARIO`(`IdUsuario`) )";

        try (Connection con = this.conectarBD();
             Statement stmt = con.createStatement()) {
            stmt.execute(usuario);
            stmt.execute(ranking);
            stmt.execute(partida);
            stmt.execute(cartas);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Tablas creadas");
    }

    private void pruebasRanking() {
        String sql1 = "INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                "VALUES(1, 'Andrea', 10, strftime('%Y-%m-%d'), 1)";

        String sql2 = "INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                "VALUES(2, 'Andrea', 20, strftime('%Y-%m-%d'), 1)";

        String sql3 = "INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                "VALUES(3, 'David', 5, strftime('%Y-%m-%d'), 1)";


        try (Connection con = this.conectarBD();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Insertados datos ranking");

    }

    private void pruebasUsuarios() {

        String sql1 = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                "VALUES('josu@gmail.com','josu',1,'1995-10-10',1)";

        String sql2 = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                "VALUES('Usoj', 'Usoj',0,'1995-10-10',1)";

        String sql3 = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                "VALUES('Pedro', 'Pedro',0,'1995-10-10',1)";

        try (Connection con = this.conectarBD();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Insertados datos en usuario");

    }


}

