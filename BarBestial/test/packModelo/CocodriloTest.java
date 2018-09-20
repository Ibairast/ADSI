package packModelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CocodriloTest {

    Tablero tablero;
    Carta c1, c2, c3, leon, coco, c5, c6;

    @Before
    public void setUp() throws Exception {
        tablero = Tablero.getMiTablero();
        c1 = new Carta(new Hipopotamo(), EnumColor.VERDE);
        c2 = new Carta(new Canguro(), EnumColor.AZUL);
        c3 = new Carta(new Cebra(), EnumColor.AZUL);
        leon = new Carta(new Leon(), EnumColor.VERDE);
        coco = new Carta(new Cocodrilo(), EnumColor.VERDE);
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

        // Caso 1: sin cebra,sin león y esta en medio. Se pone el primero.

        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(coco);
        tablero.anadirALaCola(c3);


        coco.getAnimal().hacerAnimalada();

        assertEquals(tablero.obtenerCartaEnPosicion(0), coco);
        assertEquals(tablero.obtenerNumeroDeCartas(), 2);

        // Caso 2: con cebra y sin león
        tablero.vaciar();
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(c3);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(coco);

        coco.getAnimal().hacerAnimalada();

        assertEquals(tablero.obtenerCartaEnPosicion(2), coco);
        assertEquals(Tablero.getMiTablero().obtenerNumeroDeCartas(), 3);

        // Caso 3: sin cebra y con león
        tablero.vaciar();
        tablero.anadirALaCola(c5);
        tablero.anadirALaCola(leon);
        tablero.anadirALaCola(c6);
        tablero.anadirALaCola(c2);
        tablero.anadirALaCola(coco);

        coco.getAnimal().hacerAnimalada();

        assertEquals(tablero.obtenerCartaEnPosicion(2), coco);
        assertEquals(Tablero.getMiTablero().obtenerNumeroDeCartas(), 3);

    }

    @Test
    public void recurrir() {
    }
}