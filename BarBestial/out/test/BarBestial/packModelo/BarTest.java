package packModelo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BarTest {
	Bar b;
	Carta c1, c2, c3, c4, c5;
	
	@Before
	public void setUp() throws Exception {
		b = Bar.getMiBar();
	    c1 = new Carta(new Camaleon(), EnumColor.VERDE);
	    c2 = new Carta(new Canguro(), EnumColor.AZUL);
	    c3 = new Carta(new Cebra(), EnumColor.AZUL);
	    c4 = new Carta(new Leon(), EnumColor.AZUL);
	    c5 = new Carta(new Leon(), EnumColor.VERDE);
	}

	@After
	public void tearDown() throws Exception {
		b.vaciar();
        c1 = null;
        c2 = null;
        c3 = null;
        c4 = null;
	}

	@Test
	public void testCalcularGanador() {
		/* Caso 1: no hay cartas en el bar. */
		assertNull(b.calcularGanador());
		
		/* Caso 2: hay más cartas azules. */
		b.anadirCarta(c1);
		b.anadirCarta(c2);
		b.anadirCarta(c3);

		assertEquals(EnumColor.AZUL, b.calcularGanador());
		
		/* Caso 2: hay el mismo número de cartas verdes y azules
		 * pero las verdes suman menos fuerza. */
		b.vaciar();
		b.anadirCarta(c1);
		b.anadirCarta(c4);
		assertEquals(EnumColor.VERDE, b.calcularGanador());
		
		/* Caso 3: hay el mismo número de cartas verdes y azules
		 * y ambas suman la misma fuerza */
		b.vaciar();
		b.anadirCarta(c4);
		b.anadirCarta(c5);
		assertNull(b.calcularGanador());
	}
}
