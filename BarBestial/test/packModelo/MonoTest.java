package packModelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonoTest {

    Tablero tablero;
    Carta c1, c2, c3, mono1, mono2, c5, mono3;
    EsLoQueHay esLoQueHay;

    @Before
    public void setUp() throws Exception {
        tablero = Tablero.getMiTablero();
        c1 = new Carta(new Hipopotamo(), EnumColor.VERDE);
        c2 = new Carta(new Canguro(), EnumColor.AZUL);
        c3 = new Carta(new Cebra(), EnumColor.AZUL);
        mono1 = new Carta(new Mono(), EnumColor.VERDE);
        mono2 = new Carta(new Mono(), EnumColor.VERDE);
        c5 = new Carta(new Leon(), EnumColor.VERDE);
        mono3 = new Carta(new Mono(), EnumColor.VERDE);
        esLoQueHay = EsLoQueHay.getMiEsLoQueHay();
    }

    @After
    public void tearDown() throws Exception {
        tablero = null;
        c1 = null;
        c2 = null;
        c3 = null;
        c5 = null;
        mono3 = null;
        esLoQueHay = null;
    }

    @Test
    public void hacerAnimalada() {        
        /* Caso 1: Sólo 1 mono en la cola. */
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(c1);
        tablero.anadirALaCola(mono3);

        mono3.getAnimal().hacerAnimalada();

        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(0), c5);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(1), c3);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(2), c2);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(3), c1);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(4), mono3);

        /* Caso 2: Más de un mono en la cola sin hipotamos ni cocodrilos. */
        tablero.vaciar();
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(mono1);
        tablero.anadirALaCola(mono2);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(mono3);

        mono3.getAnimal().hacerAnimalada();

        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(0), mono3);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(1), mono2);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(2), mono1);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(3), c5);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(4), c3);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(5), c2);

        /* Caso 3: Más de un mono en la cola con hipos o cocos. */
        tablero.vaciar();
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(mono1);
        tablero.anadirALaCola(c1);
        tablero.anadirALaCola(mono2);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(mono3);

        mono3.getAnimal().hacerAnimalada();

        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(0), mono3);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(1), mono2);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(2), mono1);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(3), c5);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(4), c3);
        assertEquals(Tablero.getMiTablero().obtenerCartaEnPosicion(5), c2);
    }
}