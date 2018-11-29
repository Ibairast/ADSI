package packModelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeonTest {

    Tablero tablero;
    Carta c1, c2, c3, leon, leon2, c5, c6;
    EsLoQueHay esLoQueHay;

    @Before
    public void setUp() throws Exception {
        tablero = Tablero.getMiTablero();
        c1 = new Carta(new Camaleon(), EnumColor.VERDE);
        c2 = new Carta(new Canguro(), EnumColor.AZUL);
        c3 = new Carta(new Cebra(), EnumColor.AZUL);
        leon = new Carta(new Leon(), EnumColor.VERDE);
        leon2 = new Carta(new Leon(), EnumColor.VERDE);
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
    	
        /* Caso 1: animalada sin monos. */
    	tablero.vaciar();
        tablero.anadirALaCola(c1);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(leon);

        assertEquals(tablero.obtenerNumeroDeCartas(), 4);
        assertEquals(tablero.obtenerCartaEnPosicion(0), c1);
        assertEquals(tablero.obtenerUltimaCarta(), leon);

        leon.getAnimal().hacerAnimalada();

        assertEquals(tablero.obtenerNumeroDeCartas(), 4);
        assertEquals(tablero.obtenerCartaEnPosicion(0), leon);
        assertEquals(tablero.obtenerUltimaCarta(), c3);


        /* Caso 2: animalada con monos. */
        tablero.vaciar();
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(c1);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(leon);

        assertEquals(tablero.obtenerNumeroDeCartas(), 6);
        assertEquals(tablero.obtenerCartaEnPosicion(0), c5);
        assertEquals(tablero.obtenerUltimaCarta(), leon);

        leon.getAnimal().hacerAnimalada();

        assertEquals(tablero.obtenerNumeroDeCartas(), 4);
        assertEquals(tablero.obtenerCartaEnPosicion(0), leon);
        assertEquals(tablero.obtenerUltimaCarta(), c3);


        /* Caso 3: animalada con 1 león en la cola. */
        tablero.vaciar();
        tablero.anadirALaCola(c1);
        tablero.anadirALaCola(leon);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(leon2);

        assertEquals(tablero.obtenerNumeroDeCartas(), 5);
        assertEquals(tablero.obtenerCartaEnPosicion(0), c1);
        assertEquals(tablero.obtenerUltimaCarta(), leon2);

        leon2.getAnimal().hacerAnimalada();

        assertEquals(tablero.obtenerNumeroDeCartas(), 4);
        assertEquals(tablero.obtenerCartaEnPosicion(0), c1);
        assertEquals(tablero.obtenerUltimaCarta(), c3);
    }
}