package packModelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MofetaTest {

    Tablero tablero;
    Carta c1, c2, c3, leon, mofeta, c5, c6;

    @Before
    public void setUp() throws Exception {
        tablero = Tablero.getMiTablero();
        c1 = new Carta(new Camaleon(), EnumColor.VERDE);
        c2 = new Carta(new Canguro(), EnumColor.AZUL);
        c3 = new Carta(new Cebra(), EnumColor.AZUL);
        leon = new Carta(new Leon(), EnumColor.VERDE);
        mofeta = new Carta(new Mofeta(), EnumColor.VERDE);
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
        /* Caso 1: animalada sin más mofetas. */
        tablero.anadirALaCola(c1);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(mofeta);

        mofeta.getAnimal().hacerAnimalada();

        assertEquals(tablero.obtenerNumeroDeCartas(), 2);
        assertEquals(tablero.obtenerCartaEnPosicion(0), c2);
        assertEquals(tablero.obtenerUltimaCarta(), mofeta);

        /* Caso 2: animalada con más mofetas. */
        tablero.vaciar();
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(c1);
        tablero.anadirALaCola(mofeta);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(c3);

        tablero.anadirALaCola(mofeta);
        tablero.anadirALaCola(leon);

        mofeta.getAnimal().hacerAnimalada();

        assertEquals(tablero.obtenerNumeroDeCartas(), 6);
        assertEquals(tablero.obtenerCartaEnPosicion(0), c5);
        assertEquals(tablero.obtenerUltimaCarta(), mofeta);


    }
}