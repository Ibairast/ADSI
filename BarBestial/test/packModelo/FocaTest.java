package packModelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FocaTest {
    Tablero tablero;
    Carta c1, c2, c3, leon, foca, c5, c6;

    @Before
    public void setUp() throws Exception {
        tablero = Tablero.getMiTablero();
        c1 = new Carta(new Hipopotamo(), EnumColor.VERDE);
        c2 = new Carta(new Canguro(), EnumColor.AZUL);
        c3 = new Carta(new Cebra(), EnumColor.AZUL);
        leon = new Carta(new Leon(), EnumColor.VERDE);
        foca = new Carta(new Foca(), EnumColor.VERDE);
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

        // Caso 1: Lista vacía
        tablero.anadirALaCola(foca);

        foca.hacerAnimalada();

        assertEquals(tablero.obtenerCartaEnPosicion(0), foca);
        assertEquals(tablero.obtenerUltimaCarta(), foca);

        //Caso 2: Lista no vacía

        tablero.vaciar();
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(leon);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(c1);
        tablero.anadirALaCola(foca);

        foca.hacerAnimalada();

        assertEquals(tablero.obtenerCartaEnPosicion(0), foca);
        assertEquals(tablero.obtenerUltimaCarta(), c5);


    }
}