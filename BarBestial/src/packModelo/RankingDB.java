package packModelo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class RankingDB {
    private static RankingDB miRankingDB;
    private Connection c;
    private Statement s;

    private RankingDB() {
        File f = new File("ranking.db");
        if (!f.exists()) {
            this.crearRanking();
            this.crearTablaPuntuaciones();
        }
    }

    public static RankingDB getRankingDB() {
        if (miRankingDB == null) {
            miRankingDB = new RankingDB();
        }
        return miRankingDB;
    }

    private void crearRanking() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ranking.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Base de datos creada");
    }

    private void crearTablaPuntuaciones() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ranking.db");

            s = c.createStatement();
            String instruccion = "CREATE TABLE PUNTUACIONES " +
                    "(NOMBRE		VARCHAR(20) NOT NULL, " +
                    " FECHA	    DATETIME    NOT NULL, " +
                    " NCARTAS	INT, " +
                    " FUERZA		INT, " +

                    " PRIMARY KEY(NOMBRE, FECHA))";
            s.executeUpdate(instruccion);
            s.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Tabla creada");
    }
    
    public void insertarPuntuacion(String pNombre, int pNCartas, int pFuerza) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ranking.db");
            c.setAutoCommit(false);

            s = c.createStatement();
            String instruccion = "INSERT INTO PUNTUACIONES (NOMBRE, FECHA, NCARTAS, FUERZA) " +
                    "VALUES(" + "'" + pNombre + "'" + "," + "datetime('now')" + "," + pNCartas + "," + pFuerza + ")";

            s.executeUpdate(instruccion);

            s.close();
            c.commit();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Puntuacion insertada");
    }

    public Vector<Vector<String>> obtenerMejoresPuntuaciones() {
        Vector<String> puntuacion;
        Vector<Vector<String>> puntuaciones = new Vector<>();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:ranking.db");
            c.setAutoCommit(false);

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM PUNTUACIONES ORDER BY fuerza ASC;");


            while (rs.next()) {
                puntuacion = new Vector<>();

                String nombre = rs.getString("nombre");
                String fecha = rs.getString("fecha");
                String nCartas = rs.getString("ncartas");
                String fuerza = rs.getString("fuerza");

                puntuacion.add(nombre);
                puntuacion.add(nCartas);
                puntuacion.add(fuerza);
                puntuacion.add(fecha);

                puntuaciones.add(puntuacion);
            }

            rs.close();
            s.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta terminada");
        return puntuaciones;
    }
}
   