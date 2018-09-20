package packModelo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JugadorTest {
	Jugador jugador;
    Carta carta1, carta2, carta3;
    Animal camaleon, leon, mofeta;
    Tablero t = Tablero.getMiTablero();

	@Before
	public void setUp() throws Exception {
		jugador = new JugadorReal("Revi", EnumColor.VERDE);
		
        camaleon = new Camaleon();
        leon = new Leon();
        mofeta = new Mofeta();

        carta1 = new Carta(camaleon, EnumColor.VERDE);
        carta2 = new Carta(leon, EnumColor.VERDE);
        carta3 = new Carta(mofeta, EnumColor.VERDE);
	}

	@After
	public void tearDown() throws Exception {
		jugador = null;
	}

	@Test
	public void testRobarCarta() {
		/* Caso 1: el mazo está vacío. */
		jugador.robarCarta();
        assertEquals(0, jugador.obtenerNumeroDeCartasEnMano());

        jugador.repartirCartas();

        /* Caso 2: el mazo contiene cartas. */
        assertEquals(4, jugador.obtenerNumeroDeCartasEnMano());
        
        jugador.robarCarta();
        assertEquals(5, jugador.obtenerNumeroDeCartasEnMano());
	}

	@Test
	public void testRepartirCartas() {
		/* Caso 1: se reparten las cartas. */		
		jugador.repartirCartas();
		
        assertEquals(8, jugador.obtenerNumeroDeCartasEnMazo());
		
        assertEquals(4, jugador.obtenerNumeroDeCartasEnMano());        
	}

	@Test
	public void testEcharCarta() {
		t.vaciar();
		
		/* Caso 1: se echa una carta de la mano. */
		jugador.echarCarta(carta1);
		assertEquals(carta1, t.obtenerUltimaCarta());
		assertEquals(1, t.obtenerNumeroDeCartas());
	}

}
