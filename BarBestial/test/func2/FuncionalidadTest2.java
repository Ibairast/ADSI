package func2;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import packControlador.GestorRanking;
import packModelo.SGBD;
import packModelo.Usuario;
import java.sql.Connection;
import java.sql.Statement;
import static java.time.LocalDate.now;
import static org.junit.Assert.*;

public class FuncionalidadTest2 {


    /**
     * No hay datos en la tabla ranking de la base de datos
     */
    @Test
    public void sinDatos() {
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {

            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Ranking");

            //obtenerMisMejoresPartidas
            JSONArray json1 = GestorRanking.getMiGestorRanking().obtenerMisMejoresPartidas();
            assertTrue(json1.length() == 0);

            //obtenerMejorPuntuacionDia
            JSONArray json2 = GestorRanking.getMiGestorRanking().obtenerMejorPuntuacionDia();
            assertTrue(json2.length() == 0);

            //obtenerMejoresPartidas
            JSONArray json3 = GestorRanking.getMiGestorRanking().obtenerMejoresPartidas();
            assertTrue(json3.length() == 0);

            //obtenerMejorMedia
            JSONArray json4 = GestorRanking.getMiGestorRanking().obtenerMejorMedia();
            assertTrue(json4.length() == 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mis Mejores Partidas
     */
    @Test
    public void misMejoresPartidas() {
        cargarDatos();
        JSONArray json = GestorRanking.getMiGestorRanking().obtenerMisMejoresPartidas();

        JSONObject object = json.getJSONObject(0);
        int clave = object.getInt("Puntuacion");
        assertTrue(clave == 100);

        JSONObject object1 = json.getJSONObject(1);
        int clave1 = object1.getInt("Puntuacion");
        assertTrue(clave1 == 90);

        JSONObject object2 = json.getJSONObject(2);
        int clave2 = object2.getInt("Puntuacion");
        assertTrue(clave2 == 70);

        JSONObject object3 = json.getJSONObject(3);
        int clave3 = object3.getInt("Puntuacion");
        assertTrue(clave3 == 60);

        JSONObject object4 = json.getJSONObject(4);
        int clave4 = object4.getInt("Puntuacion");
        assertTrue(clave4 == 55);

        JSONObject object5 = json.getJSONObject(5);
        int clave5 = object5.getInt("Puntuacion");
        assertTrue(clave5 == 50);

        JSONObject object6 = json.getJSONObject(6);
        int clave6 = object6.getInt("Puntuacion");
        assertTrue(clave6 == 36);

        JSONObject object7 = json.getJSONObject(7);
        int clave7 = object7.getInt("Puntuacion");
        assertTrue(clave7 == 30);

        JSONObject object8 = json.getJSONObject(8);
        int clave8 = object8.getInt("Puntuacion");
        assertTrue(clave8 == 20);

        JSONObject object9 = json.getJSONObject(9);
        int clave9 = object9.getInt("Puntuacion");
        assertTrue(clave9 == 10);
    }

    /**
     * Mejor Puntuacion Dia
     */
    @Test
    public void mejorPuntuacionDia() {

        cargarDatos();

        JSONArray json = GestorRanking.getMiGestorRanking().obtenerMejorPuntuacionDia();

        JSONObject object = json.getJSONObject(0);
        String id = object.getString("IdUsuario");
        int punt = object.getInt("Puntuacion");
        assertTrue(id.equals("paul@gmail.com"));
        assertTrue(punt == 100);
    }

    /**
     * Mejores Partidas
     */
    @Test
    public void mejoresPartidas() {

        cargarDatos();

        JSONArray json = GestorRanking.getMiGestorRanking().obtenerMejoresPartidas();

        JSONObject object = json.getJSONObject(0);
        String id = object.getString("IdUsuario");
        int punt = object.getInt("Puntuacion");
        String fecha = object.getString("Fecha");
        assertTrue(id.equals("paul@gmail.com"));
        assertTrue(punt == 100);
        assertTrue(fecha.equals(now().toString()));

        JSONObject object1 = json.getJSONObject(9);
        String id1 = object1.getString("IdUsuario");
        int punt1 = object1.getInt("Puntuacion");
        String fecha1 = object1.getString("Fecha");
        assertTrue(id1.equals("andrea@gmail.com"));
        assertTrue(punt1 == 36);
        assertTrue(fecha1.equals("2019-01-14"));
    }

    /**
     * Mejor Media
     */
    @Test
    public void mejoresMedias() {

        cargarDatos();

        JSONArray json = GestorRanking.getMiGestorRanking().obtenerMejorMedia();

        JSONObject object = json.getJSONObject(0);
        String id = object.getString("IdUsuario");
        String media = object.getString("Media");
        assertTrue(id.equals("paul@gmail.com"));
        assertTrue(media.equals("85.0"));

        JSONObject object1 = json.getJSONObject(1);
        String id1 = object1.getString("IdUsuario");
        String media1 = object1.getString("Media");
        assertTrue(id1.equals("ibai@gmail.com"));
        assertTrue(media1.equals("80.0"));

        JSONObject object2 = json.getJSONObject(2);
        String id2 = object2.getString("IdUsuario");
        String media2 = object2.getString("Media");
        assertTrue(id2.equals("andrea@gmail.com"));
        assertTrue(media2.equals("70.833336"));
    }

    /**
     * Método para cargar los datos en la base de datos para las pruebas
     */
    private void cargarDatos(){
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {

            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            stmt.executeUpdate("DELETE FROM Ranking");

            //Cargar usuario clase
            JSONObject js = new JSONObject();
            js.put("IdUsuario", "andrea@gmail.com");
            js.put("Pass", "a");
            js.put("LogFecha", "2019-01-01");
            js.put("Ayuda", "0");
            Usuario.getUsuario().cargarUsuario(js);

            //Crear usuarios bd
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('andrea@gmail.com', 'a', 0, '2019-01-01', 0)");
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('paul@gmail.com', 'b', 0, '2019-01-01', 0)");
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('ibai@gmail.com', 'c', 0, '2019-01-01', 0)");
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('josu@gmail.com', 'd', 0, '2019-01-01', 0)");

            //Añadir info
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(1, 'andrea@gmail.com', 10, strftime('%Y-%m-%d'), 0)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(2, 'ibai@gmail.com', 80, strftime('%Y-%m-%d'), 1)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(3, 'paul@gmail.com', 100, strftime('%Y-%m-%d'), 1)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(4, 'ibai@gmail.com', 20, '2019-01-07', 0)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(5, 'andrea@gmail.com', 70, strftime('%Y-%m-%d'), 1)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(6, 'paul@gmail.com', 70, '2019-01-13', 1)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(7, 'andrea@gmail.com', 100, '2019-01-14', 1)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(8, 'andrea@gmail.com', 60, '2019-01-14', 1)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(9, 'andrea@gmail.com', 30, '2019-01-14', 0)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(10, 'andrea@gmail.com', 20, '2019-01-14', 0)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(11, 'andrea@gmail.com', 50, '2019-01-14', 1)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(12, 'andrea@gmail.com', 90, '2019-01-14', 1)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(13, 'andrea@gmail.com', 55, '2019-01-14', 1)");
            stmt.executeUpdate("INSERT INTO RANKING(IdRanking, IdUsuario, Puntuacion, Fecha, Gana)" +
                    "VALUES(14, 'andrea@gmail.com', 36, '2019-01-14', 0)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}