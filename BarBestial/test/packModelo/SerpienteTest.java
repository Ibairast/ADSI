package packModelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SerpienteTest {
    Tablero tablero;
    Carta c1, c2, c3, leon, serpiente, c5, c6;

    @Before
    public void setUp() throws Exception {
        tablero = Tablero.getMiTablero();
        c1 = new Carta(new Hipopotamo(), EnumColor.VERDE);
        c2 = new Carta(new Canguro(), EnumColor.AZUL);
        c3 = new Carta(new Cebra(), EnumColor.AZUL);
        leon = new Carta(new Leon(), EnumColor.VERDE);
        serpiente = new Carta(new Serpiente(), EnumColor.VERDE);
        c5 = new Carta(new Mono(), EnumColor.VERDE);
        c6 = new Carta(new Mono(), EnumColor.VERDE);
    }

    @After
    public void tearDown() throws Exception {
        tablero = null;
        c1 = null;
        c2 = null;
        c3 = null;
        c5 = null;
        c6 = null;
    }


    @Test
    public void hacerAnimalada() {

        /* Caso 1: Animales de la misma especie. Entre las mismas especies,
         * se tienen que conservar los órdenes. */
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(serpiente);

        serpiente.hacerAnimalada();

        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(0), serpiente);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(1), c5);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(2), c6);

        /* Caso 2: Animales de distinta especie. */
        tablero.vaciar();
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(leon);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(c1);
        tablero.anadirALaCola(serpiente);

        serpiente.hacerAnimalada();

        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(0), leon);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(1), c1);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(2), serpiente);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(3), c3);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(4), c5);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(5), c6);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(6), c2);


    }
}