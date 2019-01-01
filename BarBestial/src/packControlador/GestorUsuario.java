package packControlador;


import org.json.JSONArray;
import org.json.JSONObject;
import packModelo.Usuario;

import java.sql.*;

import static java.time.LocalDate.now;

public class GestorUsuario {
    private static final String SQL_FIND = "SELECT * FROM USUARIO WHERE IdUsuario = ?";
    private static GestorUsuario miGestorUsuario = new GestorUsuario();
    private Connection c;
    private Statement s;

    private GestorUsuario() {
    }

    public static GestorUsuario getGestorUsuario() {

        return miGestorUsuario;
    }

    public boolean registrarUsuario(String txtCorreo, String txtPass) {

        if (buscarUsuario(txtCorreo) == null) {
            try {

                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
                c.setAutoCommit(false);
                s = c.createStatement();
                String sql = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda) VALUES(" +
                        "'" + txtCorreo + "' ," + "'" + txtPass + "' , 0, '" + now().toString() + "' ,0)";
                s.executeUpdate(sql);
                s.close();
                c.commit();
                c.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
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
                jugador.put("IdUsuario", rs.getString("IdUsuario"));
                jugador.put("Pass", rs.getString("Pass"));
                jugador.put("Admin", rs.getInt("Admin"));
                jugador.put("LogFecha", rs.getString("LogFecha"));
                jugador.put("Ayuda", rs.getInt("Ayuda"));
            }
            rs.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jugador;
    }

    public JSONArray obtenerUsuarios(String fecha) {
        JSONArray json = new JSONArray();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            String sql = "SELECT IdUsuario from USUARIO where LogFecha<?;";

            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, fecha);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                JSONObject js = new JSONObject();
                String usu = rs.getString("IdUsuario");
                System.out.println(usu);
                js.put("IdUsuario", usu);
                json.put(js);
            }

            rs.close();
            c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerUsuarios");
        return json;
    }


    public int comprobarUsuario(String correo, String pass) {
        //FALTA UPDATE LOGFECHA

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

            String sql = "SELECT * FROM USUARIO WHERE IdUsuario = ? AND Pass = ?;";

            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, correo);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                JSONObject js = new JSONObject();
                int admin = rs.getInt("Admin");
                if (admin == 1) {
                    return 1;
                }

                js.put("IdUsuario", correo);
                js.put("Pass", pass);
                js.put("LogFecha", now().toString());
                int ayuda = rs.getInt("Ayuda");
                js.put("Ayuda", ayuda);

                s = c.createStatement();
                String sqlUpdate = "UPDATE USUARIO SET LogFecha='" + now().toString() + "' WHERE IdUsuario = '" + correo + "';";

                s.executeUpdate(sqlUpdate);
                s.close();
                c.commit();
                Usuario.getUsuario().cargarUsuario(js);
                return -1;
            }
            rs.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return 0;
        }
        return 0;
    }
    public void eliminarUsuarios(String id){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);

                String sql = "DELETE from USUARIO where IdUsuario=?;";

                PreparedStatement pstmt = c.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.executeUpdate();
                c.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Eliminados");

    }
}
