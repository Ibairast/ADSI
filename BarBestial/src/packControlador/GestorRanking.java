package packControlador;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class GestorRanking {
    private static GestorRanking miGestorRanking;
    private Connection c;
    private Statement s;

    private GestorRanking(){}

    public static GestorRanking getMiGestorRanking() {
        if (miGestorRanking == null) {
            miGestorRanking = new GestorRanking();
        }
        return miGestorRanking;
    }


    public JSONArray obtenerMisMejoresPartidas(){
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

    public JSONArray obtenerMejorPuntuacionDia(){
        JSONArray json = new JSONArray();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT IdUsuario, Puntuacion FROM RANKING WHERE Fecha = strftime('%Y-%m-%d')  ORDER BY Puntuacion DESC LIMIT 1;");

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

    public JSONObject obtenerMejoresPartidas(){
        JSONObject json3 = new JSONObject();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT IdUsuario, Puntuacion, Fecha FROM RANKING ORDER BY Puntuacion DESC LIMIT 10;");


            while (rs.next()) {
                String usuario = rs.getString("IdUsuario");
                int puntuacion = rs.getInt("Puntuacion");
                Date fecha = rs.getDate("Fecha");
                json3.put("IdUsuario", usuario);
                json3.put("Puntuacion", puntuacion);
                json3.put("Fecha", fecha);

            }

            rs.close();
            s.close();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerMejoresPartidas terminada");
        return json3;
    }

    public JSONObject obtenerMejorMedia() {
        JSONObject json4 = new JSONObject();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            String sql = "SELECT IdUsuario, AVG(PUntuacion) AS Media FROM RANKING WHERE Gana = ? ORDER BY Media DESC LIMIT 10";

            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, "True"); //PONER EL USUARIO ACTUAL
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) { //Solo se hace una vez
                String usuario = rs.getString("IdUsuario");
                float media = rs.getFloat("Media");
                json4.put("IdUsuario", usuario);
                json4.put("Media", media);
            }

            rs.close();
            s.close();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerMejorMedia terminada");
        return json4;
    }

}
