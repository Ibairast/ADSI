package packModelo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TableroTest {
	Tablero t = Tablero.getMiTablero();;
    Carta c1, c2, c3, c4, c5;
    
	@Before
	public void setUp() throws Exception {
        c1 = new Carta(new Camaleon(), EnumColor.VERDE);
        c2 = new Carta(new Canguro(), EnumColor.AZUL);
        c3 = new Carta(new Cebra(), EnumColor.AZUL);
        c4 = new Carta(new Jirafa(), EnumColor.VERDE);
        c5 = new Carta(new Leon(), EnumColor.AZUL);
	}

	@After
	public void tearDown() throws Exception {
		t.vaciar();
        c1 = null;
        c2 = null;
        c3 = null;
        c4 = null;
        c5 = null;
	}

	@Test
	public void testAnadirALaCola() {
		/* Caso 1: se añade una carta cualquiera a la cola. */
        t.anadirALaCola(c1);
        assertEquals(1, t.obtenerNumeroDeCartas());
	}

	@Test
	public void testRevisarCola() {
        /* Caso 1: la cola está vacía. */
        t.revisarCola();
        assertEquals(0, t.obtenerNumeroDeCartas());

        /* Caso 2: la cola contiene solo 4 animales. */
        t.anadirALaCola(c1); // Camaleon
        t.anadirALaCola(c2); // Canguro
        t.anadirALaCola(c3); // Cebra
        t.anadirALaCola(c4); // Jirafa
        t.revisarCola();
        assertEquals(4, t.obtenerNumeroDeCartas());

        /* Caso 3: la cola contiene 5 animales. */
        t.anadirALaCola(c5); // Leon
        t.revisarCola();
        assertEquals(2,  t.obtenerNumeroDeCartas());
        assertTrue(t.obtenerCartaEnPosicion(0).getAnimal() instanceof Cebra);
        assertTrue(t.obtenerCartaEnPosicion(1).getAnimal() instanceof Jirafa);
	}

	@Test
	public void testVaciar() {
		/* Caso 1: la cola está vacía. */
        t.vaciar();
        assertEquals(0, t.obtenerNumeroDeCartas());

        /* Caso 2: la cola no está vacía. */
        t.anadirALaCola(c1);
        t.anadirALaCola(c2);
        t.vaciar();
        assertEquals(0, t.obtenerNumeroDeCartas());
	}

}
