package packControlador;

import org.json.JSONArray;
import org.json.JSONObject;
import packModelo.SGBD;
import packModelo.Usuario;

import java.sql.*;

public class GestorRanking {
    private static GestorRanking miGestorRanking;

    private GestorRanking() {
    }

    public static GestorRanking getMiGestorRanking() {
        if (miGestorRanking == null) {
            miGestorRanking = new GestorRanking();
        }
        return miGestorRanking;
    }


    /**
     * Obtiene de la base de datos las mejores 10 puntuaciones ordenadas
     * en orden descendiente del usuario logueado
     * @return JSONArray -> {[{Puntuacion:int},...]}
     */

    public JSONArray obtenerMisMejoresPartidas() {
        // Array de json en el que se irán almacenando los  objetos json
        JSONArray json = new JSONArray();

        // Sentencia sql que obtiene las mejores puntuaciones
        String sql = "SELECT Puntuacion FROM RANKING WHERE IdUsuario = '" + Usuario.getUsuario().getIdUsuario() +
        "' ORDER BY Puntuacion DESC LIMIT 10;";

        // Conexión con la base de datos
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Se crean los objetos json con los datos recogidos del select
            while (rs.next()) {
                JSONObject js = new JSONObject();
                int puntuacion = rs.getInt("Puntuacion");
                js.put("Puntuacion", puntuacion);
                json.put(js);
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerMisMejoresPartidas terminada");
        return json;
    }


    /**
     * Obtiene de la base de datos la mejor puntuación del día
     * @return JSONArray -> {[{idUsuario: String, Puntuacion: int},...]}
     */
    public JSONArray obtenerMejorPuntuacionDia() {
        // Array de json en el que se irán almacenando los  objetos json
        JSONArray json = new JSONArray();

        // Sentencia sql que obtiene la mejor puntuación del día
        String sql = "SELECT IdUsuario, Puntuacion FROM RANKING WHERE Fecha = strftime('%Y-%m-%d') ORDER BY Puntuacion DESC LIMIT 1;";

        // Conexión con la base de datos
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Se crean los objetos json con los datos recogidos del select
            while (rs.next()) {
                JSONObject js = new JSONObject();
                String usuario = rs.getString("IdUsuario");
                int puntuacion = rs.getInt("Puntuacion");

                js.put("IdUsuario", usuario);
                js.put("Puntuacion", puntuacion);
                json.put(js);
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerMejorPuntuacionDia terminada");
        return json;
    }

    /**
     * Obtiene de la base de datos las 10 mejores partidas
     * ordenadas en orden descendiente
     * @return JSONArray -> {[{idUsuario: String, Puntuacion: int, Fecha: Date},...]}
     */
    public JSONArray obtenerMejoresPartidas() {
        // Array de json en el que se irán almacenando los  objetos json
        JSONArray json = new JSONArray();

        // Sentencia sql que obtiene las mejores partidas
        String sql = "SELECT IdUsuario, Puntuacion, Fecha FROM RANKING ORDER BY Puntuacion DESC LIMIT 10;";

        // Conexión con la base de datos
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Se crean los objetos json con los datos recogidos del select
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


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerMejoresPartidas terminada");
        return json;
    }


    /**
     * Obtiene de la base de datos las 10 mejores medias
     * ordenadas en orden descendiente de las partidas ganadas
     * @return JSONArray -> {[{idUsuario: String, Media: Float},...]}
     */
    public JSONArray obtenerMejorMedia() {
        // Array de json en el que se irán almacenando los  objetos json
        JSONArray json = new JSONArray();

        // Sentencia sql que obtiene las mejores medias
        String sql = "SELECT IdUsuario, AVG(Puntuacion) AS Media FROM RANKING WHERE Gana = 1 GROUP BY IdUsuario ORDER BY Media DESC LIMIT 10;";

        // Conexión con la base de datos
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Se crean los objetos json con los datos recogidos del select
            while (rs.next()) { //Solo se hace una vez
                JSONObject js = new JSONObject();
                String usuario = rs.getString("IdUsuario");
                float media = rs.getFloat("Media");
                js.put("IdUsuario", usuario);
                js.put("Media", Float.toString(media));

                json.put(js);
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Consulta obtenerMejorMedia terminada");
        return json;
    }

}
