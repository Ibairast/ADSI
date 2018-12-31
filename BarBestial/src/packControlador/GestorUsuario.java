package packControlador;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class GestorUsuario {
    private static GestorUsuario miGestorUsuario;
    private Connection c;
    private Statement s;

    private GestorUsuario() {
    }

    public static GestorUsuario getMiGestorUsuario() {
        if (miGestorUsuario == null) {
            miGestorUsuario = new GestorUsuario();
        }
        return miGestorUsuario;
    }


    public JSONArray obtenerMisMejoresPartidas() {
        JSONArray json = new JSONArray();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            String sql = "SELECT IdUsuario from USUARIOS;";

            PreparedStatement pstmt = c.prepareStatement(sql);
          //  pstmt.setString(1, "Andrea");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                JSONObject js = new JSONObject();
                String usu = rs.getString("IdUsuario");
                //System.out.println(puntuacion);
                js.put("IdUsuario", usu);
                json.put(js);
            }

            rs.close();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerusuarios");
        return json;
    }
}