package packControlador;


import org.json.JSONObject;

import java.sql.*;

public class GestorUsuario {
    private static GestorUsuario miGestorUsuario = new GestorUsuario();

    private static final String SQL_INSERT = "INSERT INTO USUARIO (IdUsuario, Pass, Admin, LogFecha, Ayuda) VALUES (?, ?, ?, ? ,?)";
    private static final String SQL_FIND = "SELECT * FROM USUARIO WHERE IdUsuario = ?";


    private Connection connection;

    public static GestorUsuario getGestorUsuario() {
        return miGestorUsuario;
    }

    private GestorUsuario() {
    }


    public boolean registrarUsuario(String txtCorreo, String txtPass) {
        if (buscarUsuario(txtCorreo)==null) {
            PreparedStatement ps = null;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:barbes.db");
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_INSERT);
                ps.setString(1,txtCorreo);
                ps.setString(2,txtPass);
                ps.setInt(3,0);
                ps.setString(4, "strftime('%Y-%m-%d')");
                ps.setInt(5,0);
                if (ps.executeUpdate()>0){
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    public JSONObject buscarUsuario(String j) {
        JSONObject jugador = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(SQL_FIND);
            ps.setString(1, j);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                jugador = new JSONObject();
                jugador.put("IdUsuario",rs.getString("IdUsuario"));
                jugador.put("Pass", rs.getString("Pass"));
                jugador.put("Admin", rs.getInt("Admin"));
                jugador.put("LogFecha", rs.getString("LogFecha"));
                jugador.put("Ayuda", rs.getInt("Ayuda"));
            }
            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jugador;
    }
}
