package packControlador;


import org.json.JSONArray;
import org.json.JSONObject;
import packModelo.EmailUtil;
import packModelo.SGBD;
import packModelo.Usuario;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.UUID;

import static java.time.LocalDate.now;

public class GestorUsuario {

    private static GestorUsuario miGestorUsuario = new GestorUsuario();


    private GestorUsuario() {
    }

    public static GestorUsuario getGestorUsuario() {

        return miGestorUsuario;
    }


    public boolean registrarUsuario(String txtCorreo, String txtPass) {
        if (buscarUsuario(txtCorreo) == null) {
            String sql = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda) VALUES(" +
                    "'" + txtCorreo + "' ," + "'" + txtPass + "' , 0, '" + now().toString() + "' ,0)";

            try (Connection conn = SGBD.getMiSGBD().conectarBD();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public JSONObject buscarUsuario(String usuario) {
        JSONObject jugador = null;
        String sql = "SELECT * FROM USUARIO WHERE IdUsuario = '" + usuario + "'";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                jugador = new JSONObject();
                jugador.put("IdUsuario", rs.getString("IdUsuario"));
                jugador.put("Pass", rs.getString("Pass"));
                jugador.put("Admin", rs.getInt("Admin"));
                jugador.put("LogFecha", rs.getString("LogFecha"));
                jugador.put("Ayuda", rs.getInt("Ayuda"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jugador;
    }

    public JSONArray obtenerUsuarios(String fecha) {
        JSONArray json = new JSONArray();
        String sql = "SELECT IdUsuario from USUARIO where LogFecha< '" + fecha + "' and Admin=0";

        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                JSONObject js = new JSONObject();
                String usu = rs.getString("IdUsuario");
                System.out.println(usu);
                js.put("IdUsuario", usu);
                json.put(js);
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerUsuarios");
        return json;
    }


    public int comprobarUsuario(String correo, String pass) {
        String sql = "SELECT * FROM USUARIO WHERE IdUsuario ='" + correo + "' AND Pass = '" + pass + "'";

        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

                String sqlUpdate = "UPDATE USUARIO SET LogFecha='" + now().toString() + "' WHERE IdUsuario = '" + correo + "'";
                PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
                pstmt.executeUpdate();

                Usuario.getUsuario().cargarUsuario(js);
                return -1;
            }


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return 0;
        }
        return 0;
    }

    public void eliminarUsuarios(JSONArray json) {
        for (int i = 0; i < json.length(); i++) {
            JSONObject objeto = json.getJSONObject(i);
            String id = objeto.getString("IdUsuario");

            String sql = "DELETE from USUARIO where IdUsuario= ?";
            try (Connection conn = SGBD.getMiSGBD().conectarBD();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, id);
                pstmt.executeUpdate();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        System.out.println("Eliminados");
    }


    public void cambiarContrasena(String usuario, String pass) {
        String sqlUpdate = "UPDATE USUARIO SET Pass= ? WHERE IdUsuario= ?";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            pstmt.setString(1, pass);
            pstmt.setString(2, usuario);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Método obtenido de la web:
     * <p>
     * https://www.journaldev.com/2532/javamail-example-send-mail-in-java-smtp
     * <p>
     * Autor: About Pankaj
     *
     * @param correo Correo introducido por el usuario en IU_RPass.
     * @return TRUE si se ha enviado la contraseña, FALSE si ha habido algún error.
     */
    public boolean recuperarContrasena(String correo) {


        JSONObject usuario = buscarUsuario(correo);

        if (usuario != null) {
            final String fromEmail = "barbesadsi2018@gmail.com"; //requires valid gmail id
            final String password = "ADSIbarbes2018"; // correct password for gmail id

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);

            return EmailUtil.sendEmail(session, correo, "BarBestial", usuario.getString("Pass"));
        }
        return false;
    }

    public boolean identificarRRSS(String correo) {
        String pass = UUID.randomUUID().toString();
        if (!registrarUsuario(correo, pass)) { //Si ya tiene cuenta
            JSONObject usuario = buscarUsuario(correo);
            comprobarUsuario(correo, usuario.getString("Pass"));
            return true;
        }else { //Si no tiene cuenta
            comprobarUsuario(correo, pass);
            return false;
        }
    }
}
