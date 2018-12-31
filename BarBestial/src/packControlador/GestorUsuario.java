package packControlador;


import org.json.JSONObject;

import java.sql.*;

public class GestorUsuario {
    private static GestorUsuario miGestorUsuario = new GestorUsuario();

    private static final String SQL_INSERT = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda) VALUES(?, ?, ?, strftime('%Y-%m-%d') ,?)";
    private static final String SQL_FIND = "SELECT * FROM USUARIO WHERE IdUsuario = ?";


    private Connection connection;


    private Connection c;
    private Statement s;
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
                c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
                c.setAutoCommit(false);

                String sql = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda) VALUES("+
                        "'" + txtCorreo + "' ," + "'" + txtPass  + "' , 0, strftime('%Y-%m-%d') ,0)";

                s.executeUpdate(sql);

                s.close();
                c.commit();
                c.close();
                return true;
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
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement(SQL_FIND);
            ps.setString(1, j);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                jugador = new JSONObject();
                jugador.put("IdUsuario",rs.getString("IdUsuario"));
                jugador.put("Pass", rs.getString("Pass"));
                jugador.put("Admin", rs.getInt("Admin"));
                jugador.put("LogFecha", rs.getString("LogFecha"));
                jugador.put("Ayuda", rs.getInt("Ayuda"));

                System.out.println(rs.getString("IdUsuario"));
            }
            rs.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jugador;
    }
}
