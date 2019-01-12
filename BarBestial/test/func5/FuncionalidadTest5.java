package func5;

import org.junit.Test;
import static org.junit.Assert.*;
import packModelo.SGBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import packControlador.GestorMazoPersonalizado;

public class FuncionalidadTest5 {


    @Test
    public void seleccionarMazo() {
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            stmt.executeUpdate("DELETE FROM MazoP");
            //Usuario de prueba
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda, IDMazo)" +
                    " VALUES('perso@gmail.com','123',0,'2019-01-01', 0, 'defecto')");
            //Insertar mazos
            stmt.executeUpdate("INSERT INTO MAZOP (IdMazoP,IdUsuario) VALUES ('defecto','perso@gmail.com')");
            stmt.executeUpdate("INSERT INTO MAZOP (IdMazoP,IdUsuario) VALUES ('mazoprueba','perso@gmail.com')");
            //Coger Idmazo del usuario
            ResultSet rs = stmt.executeQuery("SELECT IDMazo FROM USUARIO WHERE IdUsuario='perso@gmail.com'");
            String nombremazo = rs.getString("IDMazo");

            //Selecciona un mazo del desplegable, el cual muestra los nombres de sus mazos añadidos.
            //Tenemos Añadido el mazo 'defecto' y seleccionamos 'mazoprueba'
            GestorMazoPersonalizado.getMiGestorMazoPersonalizado().seleccionarPersonalizacion("mazoprueba", "perso@gmail.com");
            assertEquals("mazoprueba", nombremazo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarMazo() {
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            stmt.executeUpdate("DELETE FROM MazoP");
            //Usuario de prueba
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda, IDMazo)" +
                    " VALUES('perso@gmail.com','123',0,'2019-01-01', 0, 'defecto')");
            //Insertar mazos
            stmt.executeUpdate("INSERT INTO MAZOP (IdMazoP,IdUsuario) VALUES ('defecto','perso@gmail.com')");
            stmt.executeUpdate("INSERT INTO MAZOP (IdMazoP,IdUsuario) VALUES ('mazoprueba','perso@gmail.com')");
            stmt.executeUpdate("INSERT INTO MAZOP (IdMazoP,IdUsuario) VALUES ('mazoprueba2','perso@gmail.com')");

            //Borrar mazo defecto. Devolvera false porque no le deja eliminarlo.
            Boolean bol = GestorMazoPersonalizado.getMiGestorMazoPersonalizado().eliminarPersonalizacion("defecto", "perso@gmail.com");
            assertFalse(bol);

            //Borrar el mazo en uso. Devolvera True al eliminarse y se pondra por defecto el defecto.
            GestorMazoPersonalizado.getMiGestorMazoPersonalizado().seleccionarPersonalizacion("mazoprueba2", "perso@gmail.com");
            Boolean bol3 = GestorMazoPersonalizado.getMiGestorMazoPersonalizado().eliminarPersonalizacion("mazoprueba2", "perso@gmail.com");
            assertTrue(bol3);
            ResultSet rs = stmt.executeQuery(("SELECT IDMazo FROM USUARIO WHERE IdUsuario='perso@gmail.com'"));
            String nombremazo = rs.getString("IDMazo");
            assertEquals("defecto", nombremazo);

            //Borrar otro mazo. Devolvera True al eliminarse.
            Boolean bol2 = GestorMazoPersonalizado.getMiGestorMazoPersonalizado().eliminarPersonalizacion("mazoprueba", "perso@gmail.com");
            assertTrue(bol2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void anadirMazo() {
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement()) {
            //Limpieza inicial
            stmt.executeUpdate("DELETE FROM Usuario");
            stmt.executeUpdate("DELETE FROM MazoP");
            //Usuario de prueba
            stmt.executeUpdate("INSERT INTO Usuario(IdUsuario, Pass, Admin, LogFecha, Ayuda, IDMazo)" +
                    " VALUES('perso@gmail.com','123',0,'2019-01-01', 0, 'defecto')");
            //Insertar mazos
            stmt.executeUpdate("INSERT INTO MAZOP (IdMazoP,IdUsuario) VALUES ('defecto','perso@gmail.com')");
            stmt.executeUpdate("INSERT INTO MAZOP (IdMazoP,IdUsuario) VALUES ('mazoprueba','perso@gmail.com')");

            //Añadir nombre pero no path
            GestorMazoPersonalizado.getMiGestorMazoPersonalizado().anadirPersonalizacion("mazo1", null, "perso@gmail.com");
            //Añadir path pero no nombre
            GestorMazoPersonalizado.getMiGestorMazoPersonalizado().anadirPersonalizacion(null, "/home/alain", "perso@gmail.com");
            //No añadir ni path ni nombre
            GestorMazoPersonalizado.getMiGestorMazoPersonalizado().anadirPersonalizacion(null, null, "perso@gmail.com");
            //Usar fotos de diferente dimension : No se puede testear pero se realiza un resize mediante GestorMazoPersonalizado.resizeImageIcon


            //Añadir Un nombre de mazo ya añadido. Devolveria False
            Boolean bol = GestorMazoPersonalizado.getMiGestorMazoPersonalizado().anadirPersonalizacion("defecto", "/images", "perso@gmail.com");
            assertFalse(bol);

            /**Introducir un mazo con nuevo nombre y el path correcto con todas las fotos.**/
            //No he podido hacerlo funcionar por como meter blobs en la BD. setBlob y setBinaryStream no estan soportados.
            //Y con setBytes me da un java.lang.ArrayIndexOutOfBoundsException: Index 2 out of bounds for length 1
            //He probado, la length del Byte[] es de 12000, pero no logro arreglarlo y meter las fotos.

            /**Si el path es incorrecto (No tiene dichas fotos), los nombres estan mal, o no existe x foto -> Se sustituye por la de defecto **/
            //Por lo que si el path no tiene fotos o todos los nombres estan mal el caso se cubriria creando un mazo con las fotos por defecto
            // No se ha podido por el mismo motivo de los Byte[], al coger las imagenes de /resources/images como se hacia en el juego base
            // No se convierte en byte[]

            /** Para cargar las fotos en el juego, el metodo VentanaJUego.seleccionarImagenCarta(pinfocarta) llamaria a Controlador.SeleccionarImagenCarta(pInfoCarta)
             * El cual devuelve dicha carta de la BD, la redimensiona para que encaje en las dimensiones 160,250
             * No se le hace la llamada por no haber sido posible meter las imagenes en la BD.
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
