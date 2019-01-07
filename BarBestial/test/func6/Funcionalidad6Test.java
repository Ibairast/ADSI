package func6;

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
    public void prueba1(){
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

            assertEquals(Controlador.getMiControlador().obtenerUsuarios(fecha).length(),0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void prueba2(){
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DELETE FROM Usuario");

            String fecha="2019-02-02";
            assertEquals(Controlador.getMiControlador().obtenerUsuarios(fecha).length(),0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}