package func6;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import packControlador.Controlador;
import packControlador.GestorUsuario;
import packModelo.SGBD;
import packVista.VentanaFecha;
import packVista.VentanaInicio;
import packVista.sesion.Sesion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class Funcionalidad6Test {
    @Test
    //En esta prueba lo que mira es que dada una fecha nos devuelva los usuarios que hay en la base de datos anterior
    //a esa fecha
    public void obtenerUsuariosPorFecha(){
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DELETE FROM Usuario");

            String correo = "p@gmail.com";
            String correo1 = "pg@gmail.com";
            String correo2 = "pgu@gmail.com";
            String contraBuena = "123";

            int admin = 0;
            String fecha="2019-02-02";
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo + "','" + contraBuena + "'," + admin + ",'2019-01-01', 0)");
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo1 + "','" + contraBuena + "'," + admin + ",'2019-01-01', 0)");
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo2 + "','" + contraBuena + "'," + admin + ",'2019-01-01', 0)");

            assertEquals(Controlador.getMiControlador().obtenerUsuarios(fecha).length(),3);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void eliminarUsuariosConJson(){
        //En esta prueba lo que mira es que dado un json de usuarios se eliminen
        //lo que se hace es buscar esos usuarios desde esa fecha, miramos su longitud
        //pasamos esos usuarios al metodo eliminar y despues volvemos a hacer el primer paso de mirar.
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            JSONArray json= new JSONArray();
            stmt.executeUpdate("DELETE FROM Usuario");

            String correo = "p@gmail.com";
            String correo1 = "pg@gmail.com";
            String correo2 = "pgu@gmail.com";
            String contraBuena = "123";

            String fecha="2019-02-02";


            int admin = 0;

            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo + "','" + contraBuena + "'," + admin + ",'2019-01-01', 0)");
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo1 + "','" + contraBuena + "'," + admin + ",'2019-01-01', 0)");
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo2 + "','" + contraBuena + "'," + admin + ",'2019-01-01', 0)");


            assertEquals(Controlador.getMiControlador().obtenerUsuarios(fecha).length(),0);
            Controlador.getMiControlador().eliminarUsuarios(Controlador.getMiControlador().obtenerUsuarios(fecha));
            assertEquals(Controlador.getMiControlador().obtenerUsuarios(fecha).length(),0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}