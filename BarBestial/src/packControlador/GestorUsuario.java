package packControlador;

import org.json.JSONArray;
import org.json.JSONObject;
import packModelo.EmailUtil;
import packModelo.SGBD;
import packModelo.Usuario;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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


    /* ********************************************* FUNCIONALIDAD 1 **************************************************/


    /**
     * @param txtCorreo Correo utilizado para registrarse.
     * @param txtPass   Contraseña introducida, en el primer campo de la interfaz "IU_SignUP"
     * @param txtPass2  Contraseña utilizada, en el segundo campo, por el usuario en su registro.
     * @precondicion El correo sigue un formato válido.
     * @postcondicion TRUE o FALSE, dependiendo si el registro se ha realizado correctamente.
     * <p>Funcionamiento</p>
     * Utiliza los métodos "validarFormatoCorreo" y "contrasenaValida" para comprobar la correcta introducción de datos.
     * Utiliza el método "buscarCorreo" para comprobar si ya alguien registrado con ese correo.
     * Si no hay nadie aún se conecta a "barbes.bd"(base de datos), ejecuta la sentencia SQL(INSERT...) y devuelve TRUE.
     * Devuelve FALSE en caso que haya un problema con la base de datos o con la sentencia SQL o el correo esté ya en
     * nuestra base de datos o los datos introducidos sean inválidos.
     */
    public boolean registrarUsuario(String txtCorreo, String txtPass, String txtPass2) {

        if (validarFormatoCorreo(txtCorreo) && contrasenaValida(txtPass, txtPass2)) {
            if (buscarCorreo(txtCorreo) == null) {
                String sql = "INSERT INTO USUARIO(IdUsuario, Pass, Admin, LogFecha, Ayuda, IDMazo) VALUES(" +
                        "'" + txtCorreo + "' ," + "'" + txtPass + "' , 0, '" + now().toString() + "' ,0,'defecto')";

                try (Connection conn = SGBD.getMiSGBD().conectarBD();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.executeUpdate();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * @param txtPass  Contraseña uno a validar.
     * @param txtPass2 Contraseña dos a validar.
     * @precondicion Ninguna.
     * @postcondicion TRUE o FALSE, dependiendo si la contraseña es válida o no.
     * <p>Funcionamiento</p>
     * Comprueba que ambas contraseñas sean iguales y que no estén vacias.
     */
    public boolean contrasenaValida(String txtPass, String txtPass2) {
        return (txtPass.equals(txtPass2) && !txtPass.equals(""));
    }

    /**
     * @param correo Correo a validar.
     * @precondicion Ninguna.
     * @postcondicion TRUE o FALSE, dependiendo si el correo es válido o no.
     * <p>Funcionamiento</p>
     * Comprueba si el correo es vacío y utilizando el método "validate" de la API "javax.mail" se comprueba si el
     * correo analizado es válido.
     */
    public boolean validarFormatoCorreo(String correo) {
        if (!correo.equals("")) {
            try {
                InternetAddress emailAddr = new InternetAddress(correo);
                emailAddr.validate();
                return true;
            } catch (AddressException ex) {
                return false;
            }
        }
        return false;

    }

    /**
     * @param correo Correo a buscar.
     * @precondicion Ninguna.
     * @postcondicion JSONObject cargado con los datos obtenidos con la ejecución del método.
     * <p>Funcionamiento</p>
     * Se conecta a "barbes.bd"(base de datos), ejecuta la sentencia SQL (SELECT...) y se carga todos los datos obtenidos
     * de la sentencia SQL en el JSONObject "jugador".
     * Finalmente, se devuelve el JSONObject "jugador".
     */
    private JSONObject buscarCorreo(String correo) {
        JSONObject jugador = null;
        String sql = "SELECT * FROM USUARIO WHERE IdUsuario = '" + correo + "'";
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
                jugador.put("IDMazo", rs.getString("IDMazo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jugador;
    }

    /**
     * @param correo Correo introducido en la interfaz "IU_Login"
     * @param pass   Contraseña introducida en la interfaz "IU_Login"
     * @precondicion Ninguna.
     * @postcondicion :
     * 1 si el correo identificado pertenece a un administrador.
     * -1 si el correo identificado pertenece a un usuario.
     * 0 en caso que se produzca algún error con la conexión a "barbes.bd"(base de datos) o con la sentencia SQL(SELECT...)
     * o el si el formato del correo es inválido.
     * <p>Funcionamiento</p>
     * Comprueba si el correo y la contraseña son válidos con los métodos "validarFormatoCorreo" y "contrasenaValida"
     * correspondientemente si son válidos se conecta a "barbes.bd"(base de datos)
     * y se ejecuta la sentencia SQL (SELECT...). Comprueba si el usuario obtenido es administrador, sino lo es, carga
     * todos los datos obtenidos en la anterior sentencia SQL en el JSONObject "usuario", ejecuta una nueva sentencia
     * SQL(UPDATE...) y finalmente carga ejecuta el método "cargarUsuario" del Singleton "Usuario" pasando como parámetro
     * el JSONObject "usuario".
     * Dependiendo las condiciones por donde se haya ejecutado o los errores que haya tenido, devolverá 0,1 ó -1.
     */
    public int identificarUsuario(String correo, String pass) {
        String sql = "SELECT * FROM USUARIO WHERE IdUsuario ='" + correo + "' AND Pass = '" + pass + "'";
        if (validarFormatoCorreo(correo) && contrasenaValida(pass, pass)) {
            try (Connection conn = SGBD.getMiSGBD().conectarBD();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    JSONObject usuario = new JSONObject();
                    int admin = rs.getInt("Admin");
                    if (admin == 1) {
                        return 1;
                    }
                    usuario.put("IdUsuario", correo);
                    usuario.put("Pass", pass);
                    usuario.put("LogFecha", now().toString());
                    int ayuda = rs.getInt("Ayuda");
                    usuario.put("Ayuda", ayuda);
                    String idmazo = rs.getString("IDMazo");
                    usuario.put("IDMazo",idmazo);
                    
                    String sqlUpdate = "UPDATE USUARIO SET LogFecha='" + now().toString() + "' WHERE IdUsuario = '" + correo + "'";
                    PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
                    pstmt.executeUpdate();

                    Usuario.getUsuario().cargarUsuario(usuario);
                    return -1;
                }
            } catch (Exception e) {
                return 0;
            }
            return 0;
        }
        return 0;
    }

    /**
     * @param correo Correo usuario.
     * @param pass   Nueva contraseña.
     * @precondicion El correo tiene que estar en "barbes.bd"(base de datos).
     * @postcondicion Cambia la contraseña actual, del correo introducido, por la nueva contraseña introducida.
     * <p>Funcionamiento</p>
     * Se conecta a "barbes.bd"(base de datos) y se ejecuta la sentencia SQL(UPDATE...).
     */
    public void cambiarContrasena(String correo, String pass) {
        String sqlUpdate = "UPDATE USUARIO SET Pass= ? WHERE IdUsuario= ?";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            pstmt.setString(1, pass);
            pstmt.setString(2, correo);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param correo Correo utilizado en la identificación con RRSS.
     * @precondicion Tener un correo registrado con Google.
     * @postcondicion Se identifica en el Bar Bestial.
     * <p>Funcionamiento</p>
     * Utiliza el método "buscarCorreo" para comprobar si ya existe una cuenta en "barbes.bd"(base de datos) con
     * el correo recibido.
     * Si se obtiene null en el JSONObject "usuario" se registra el correo y se identifica.
     * En caso contrario, identificamos el correo obteniendo la contraseña del JSONObject "usuario".
     */
    public void identificarRRSS(String correo) {
        String contrasena = UUID.randomUUID().toString();
        JSONObject usuario = buscarCorreo(correo);
        if (usuario == null) {
            registrarUsuario(correo, contrasena, contrasena);
            identificarUsuario(correo, contrasena);
        } else {
            identificarUsuario(correo, usuario.getString("Pass"));
        }
    }

    /**
     * Método obtenido de la web:
     * <p>Funcionamiento</p>
     * https://www.journaldev.com/2532/javamail-example-send-mail-in-java-smtp
     * <p>Funcionamiento</p>
     * Autor: About Pankaj
     *
     * @param correo Correo introducido en la interfaz "IU_RPass".
     * @return TRUE o FALSE, dependiendo si se envía la contraseña o se genera un error en el proceso de envío.
     */
    public boolean recuperarContrasena(String correo) {
        if (validarFormatoCorreo(correo)) {
            JSONObject usuario = buscarCorreo(correo);
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
        return false;
    }

    /* *********************************************** FUNCIONALIDAD 6 ************************************************/


    public void eliminarUsuarios(JSONArray json) {
        //Recorremos el json
        for (int i = 0; i < json.length(); i++) {
            JSONObject objeto = json.getJSONObject(i);
            String id = objeto.getString("IdUsuario");
            //Por cada elemento del json lo vamos eliminado de la base de datos
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
    }


    public JSONArray obtenerUsuarios(String fecha) {
        //Creamos un json
        JSONArray json = new JSONArray();
        //Hacemos la select donde sacaremos los usuarios que no se han logeado desde la fecha pasada como
        //parametro y que no sean administradores
        String sql = "SELECT IdUsuario from USUARIO where LogFecha< '" + fecha + "' and Admin=0";
        //Establecemos la conexión con la base de datos
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            //Por cada elemento que hemos sacado de la base de datos, cogemos su IdUsuario
            //y lo guardamos en nuestro json.Que sera lo que devolvemos.
            while (rs.next()) {
                JSONObject js = new JSONObject();
                String usu = rs.getString("IdUsuario");
                js.put("IdUsuario", usu);
                json.put(js);
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return json;
    }

}
