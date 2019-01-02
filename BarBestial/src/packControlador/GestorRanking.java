package packControlador;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class GestorRanking {
    private static GestorRanking miGestorRanking;
    private Connection c;
    private Statement s;

    private GestorRanking() {
    }

    public static GestorRanking getMiGestorRanking() {
        if (miGestorRanking == null) {
            miGestorRanking = new GestorRanking();
        }
        return miGestorRanking;
    }

    public JSONArray obtenerMisMejoresPartidas() {
        JSONArray json = new JSONArray();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            String sql = "SELECT Puntuacion FROM RANKING WHERE IdUsuario = ? ORDER BY Puntuacion DESC LIMIT 10;";

            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, "Andrea"); //PONER EL USUARIO ACTUAL
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                JSONObject js = new JSONObject();
                int puntuacion = rs.getInt("Puntuacion");
                //System.out.println(puntuacion);
                js.put("Puntuacion", puntuacion);
                json.put(js);
            }

            rs.close();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerMisMejoresPartidas terminada");
        return json;
    }

    public JSONArray obtenerMejorPuntuacionDia() {
        JSONArray json = new JSONArray();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT IdUsuario, Puntuacion FROM RANKING WHERE Fecha = strftime('%Y-%m-%d') ORDER BY Puntuacion DESC LIMIT 1;");

            while (rs.next()) { //Solo se hace una vez
                JSONObject js = new JSONObject();
                String usuario = rs.getString("IdUsuario");
                int puntuacion = rs.getInt("Puntuacion");
                //System.out.println(usuario);
                //System.out.println(puntuacion);

                js.put("IdUsuario", usuario);
                js.put("Puntuacion", puntuacion);
                json.put(js);
            }

            rs.close();
            s.close();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerMejorPuntuacionDia terminada");
        return json;
    }

    public JSONArray obtenerMejoresPartidas() {
        JSONArray json = new JSONArray();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT IdUsuario, Puntuacion, Fecha FROM RANKING ORDER BY Puntuacion DESC LIMIT 10;");


            while (rs.next()) {
                JSONObject js = new JSONObject();
                String usuario = rs.getString("IdUsuario");
                int puntuacion = rs.getInt("Puntuacion");
                String fecha = rs.getString("Fecha");
                js.put("IdUsuario", usuario);
                js.put("Puntuacion", puntuacion);
                js.put("Fecha", fecha);

                json.put(js);

            }

            rs.close();
            s.close();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerMejoresPartidas terminada");
        return json;
    }

    public JSONArray obtenerMejorMedia() {
        JSONArray json = new JSONArray();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT IdUsuario, AVG(Puntuacion) AS Media FROM RANKING WHERE Gana = 1 GROUP BY IdUsuario ORDER BY Media DESC LIMIT 10;");

            while (rs.next()) { //Solo se hace una vez
                JSONObject js = new JSONObject();
                String usuario = rs.getString("IdUsuario");
                float media = rs.getFloat("Media");
                js.put("IdUsuario", usuario);
                js.put("Media", Float.toString(media));

                json.put(js);
            }

            rs.close();
            s.close();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerMejorMedia terminada");
        return json;
    }

}
