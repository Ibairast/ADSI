package packModelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JirafaTest {
    Tablero tablero;
    Carta c1, c2, c3, leon, jirafa, c5, c6;
    EsLoQueHay esLoQueHay;

    @Before
    public void setUp() throws Exception {
        tablero = Tablero.getMiTablero();
        c1 = new Carta(new Hipopotamo(), EnumColor.VERDE);
        c2 = new Carta(new Canguro(), EnumColor.AZUL);
        c3 = new Carta(new Cebra(), EnumColor.AZUL);
        leon = new Carta(new Leon(), EnumColor.VERDE);
        jirafa = new Carta(new Jirafa(), EnumColor.VERDE);
        c5 = new Carta(new Mono(), EnumColor.VERDE);
        c6 = new Carta(new Mono(), EnumColor.VERDE);
        esLoQueHay = EsLoQueHay.getMiEsLoQueHay();
    }

    @After
    public void tearDown() throws Exception {
        tablero = null;
        c1 = null;
        c2 = null;
        c3 = null;
        c5 = null;
        c6 = null;
        esLoQueHay = null;
    }

    @Test
    public void hacerAnimalada() {

        // Caso 1: al final de la cola
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(jirafa);

        jirafa.getAnimal().hacerAnimalada();

        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(0), c5);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(1), c6);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(2), c3);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(3), jirafa);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(4), c2);

        // Caso 2: en medio de la cola
        tablero.vaciar();
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(jirafa);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(c2);


        jirafa.getAnimal().hacerAnimalada();

        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(0), c5);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(1), jirafa);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(2), c6);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(3), c3);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(4), c2);

    }

    @Test
    public void recurrir() {
    }
}