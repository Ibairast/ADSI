package func1;

import org.junit.Test;
import packControlador.GestorUsuario;
import packModelo.SGBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;


public class Funcionalidad1Test {


    /**
     * Correo sin registrar y datos bien.
     */
    @Test
    public void registroPrueba01() {
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            String correo = "pguerrerolinares@gmail.com";
            boolean test;
            test =  GestorUsuario.getGestorUsuario().registrarUsuario(correo, "123", "123");
            assertTrue(test);
            ResultSet rs = stmt.executeQuery("SELECT IdUsuario FROM Usuario");
            assertTrue(rs.next());
            assertEquals(correo, rs.getString("IdUsuario"));
            assertFalse(rs.next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * Correo ya registrado.
     *
     */
    @Test
    public void registroPrueba02() {
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            String correo = "pguerrerolinares@gmail.com";
            String contraBuena = "123";
            int admin = 0;
            boolean test;
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo + "','" + contraBuena + "'," + admin + ",'2019-01-01', 0)");
            test =  GestorUsuario.getGestorUsuario().registrarUsuario(correo, "123", "123");
            assertFalse(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * Contraseñas no coinciden
     */
    @Test
    public void registroPrueba03() {
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            String correo = "pguerrerolinares@gmail.com";
            boolean test;
            test = GestorUsuario.getGestorUsuario().registrarUsuario(correo, "123", "312");
            assertFalse(test);
            ResultSet rs = stmt.executeQuery("SELECT IdUsuario FROM Usuario");
            assertFalse(rs.next());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sintaxis correo incorrecta.
     */

    @Test
    public void registroPrueba04() {
        String emailCorrecto = "pguerrerolinares@gmail.com";
        String emailIncorrecto01 = "123";
        String emailIncorrecto02 = "123..@gmail.com";
        String emailIncorrecto03 = "123@gmail";
        String emailVacio = "";
        assertTrue(GestorUsuario.getGestorUsuario().validarFormatoEmail(emailCorrecto));
        assertFalse(GestorUsuario.getGestorUsuario().validarFormatoEmail(emailIncorrecto01));
        assertFalse(GestorUsuario.getGestorUsuario().validarFormatoEmail(emailIncorrecto02));
        assertFalse(GestorUsuario.getGestorUsuario().validarFormatoEmail(emailIncorrecto03));
        assertFalse(GestorUsuario.getGestorUsuario().validarFormatoEmail(emailVacio));
    }

    /**
     * Usuario RRSS, depende de la API de GOOGLE.
     */
    @Test
    public void registroPrueba05_06() {
    }

    /**
     * Correo usuario y contraseña correcto.
     */
    @Test
    public void identificacionPrueba01() {
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            String correo = "pguerrerolinares@gmail.com";
            String contraBuena = "123";
            //No admin
            int admin = 0;
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo + "','" + contraBuena + "'," + admin + ",'2019-01-01', 0)");
            assertEquals(GestorUsuario.getGestorUsuario().identificarCorreo(correo, contraBuena), -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Correo admin y contraseña correcto.
     */
    @Test
    public void identificacionPrueba02() {
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            String correo = "pguerrerolinares@gmail.com";
            String contraBuena = "123";
            //Si admin
            int admin = 1;
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo + "','" + contraBuena + "'," + admin + ",'2019-01-01', 0)");
            assertEquals(GestorUsuario.getGestorUsuario().identificarCorreo(correo, contraBuena), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Contraseña incorrecta
     */
    @Test
    public void identificacionPrueba03() {
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            String correo = "pguerrerolinares@gmail.com";
            String contraBuena = "123";
            String contraErreonea = "321";
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo + "','" + contraBuena + "', 0 ,'2019-01-01', 0)");
            assertEquals(GestorUsuario.getGestorUsuario().identificarCorreo(correo, contraErreonea), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ambas contraseñas coinciden.
     */
    @Test
    public void cambiarContraPrueba01() {
        String correo = "pguerrerolinares@gmail.com";
        String contraActual = "123";
        String nuevaContra1 = "321";

        String nuevaContra2 = "321";
        assertTrue(GestorUsuario.getGestorUsuario().contrasenaValida(nuevaContra1,nuevaContra2));
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo + "','" + contraActual + "', 0 ,'2019-01-01', 0)");
            GestorUsuario.getGestorUsuario().cambiarContrasena(correo, nuevaContra1);
            ResultSet rs = stmt.executeQuery("SELECT Pass FROM Usuario");
            assertEquals(rs.getString("Pass"), nuevaContra1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Las contraseñas no coinciden.
     */
    @Test
    public void cambiarContraPrueba02() {
        String nuevaContra1 = "123";
        String nuevaContra2 = "321";
        assertFalse(GestorUsuario.getGestorUsuario().contrasenaValida(nuevaContra1,nuevaContra2));

    }

    /**
     * Correo correcto (registrado)
     */

    @Test
    public void recuperarContraPrueba02() {
        String correo = "pguerrerolinares@gmail.com";
        String contra = "123";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda)" +
                    " VALUES('" + correo + "','" + contra + "', 0 ,'2019-01-01', 0)");
            assertTrue(GestorUsuario.getGestorUsuario().recuperarContrasena(correo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Correo incorrecto (no registrado).
     */

    @Test
    public void recuperarContraPrueba03() {
        String correoNoRegistrado = "123@gmail.com";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            assertFalse(GestorUsuario.getGestorUsuario().recuperarContrasena(correoNoRegistrado));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}