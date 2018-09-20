package packModelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HipopotamoTest {
    Tablero tablero;
    Carta c1, c2, c3, leon, hipo, c5, c6;
    EsLoQueHay esLoQueHay;

    @Before
    public void setUp() throws Exception {
        tablero = Tablero.getMiTablero();
        c1 = new Carta(new Hipopotamo(), EnumColor.VERDE);
        c2 = new Carta(new Canguro(), EnumColor.AZUL);
        c3 = new Carta(new Cebra(), EnumColor.AZUL);
        leon = new Carta(new Leon(), EnumColor.VERDE);
        hipo = new Carta(new Hipopotamo(), EnumColor.VERDE);
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
        // Caso 1: con cebra y sin más hipopótamos

        tablero.anadirALaCola(leon);
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(c1);

        c1.getAnimal().hacerAnimalada();

        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(0), leon);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(1), c5);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(2), c6);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(3), c3);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(4), c1);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(5), c2);

        // Caso 2: con cebra y con más hipopótamos
        tablero.vaciar();
        tablero.anadirALaCola(leon);
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(hipo);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(c1);

        c1.getAnimal().hacerAnimalada();

        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(0), leon);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(1), c5);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(2), c6);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(3), c3);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(4), hipo);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(5), c1);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(6), c2);

        // Caso 3: sin cebras ni más hipopótamos
        tablero.vaciar();
        tablero.anadirALaCola(leon);
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(c1);

        c1.getAnimal().hacerAnimalada();

        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(0), leon);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(1), c1);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(2), c5);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(3), c6);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(4), c2);
    }

    @Test
    public void recurrir() {
    }
}