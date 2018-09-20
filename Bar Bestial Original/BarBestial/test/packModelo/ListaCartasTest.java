package packModelo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListaCartasTest {
	ListaCartas l;
	Carta c1, c2, c3, c4, c5, c6;

	@Before
	public void setUp() throws Exception {
		l = new ListaCartas();
	    c1 = new Carta(new Camaleon(), EnumColor.VERDE);
	    c2 = new Carta(new Canguro(), EnumColor.AZUL);
	    c3 = new Carta(new Cebra(), EnumColor.AZUL);
	    c4 = new Carta(new Leon(), EnumColor.AZUL);
	    c5 = new Carta(new Leon(), EnumColor.VERDE);
	    c6 = new Carta(new Cocodrilo(), EnumColor.VERDE);
	}

	@After
	public void tearDown() throws Exception {
		l = null;
	}

	@Test
	public void testAnadirCarta() {
		assertEquals(0, l.obtenerNumeroDeCartas());
		
		l.anadirCarta(c1);
		assertEquals(1, l.obtenerNumeroDeCartas());
	}

	@Test
	public void testMoverCartaAPosicion() {
		l.anadirCarta(c1);
		l.anadirCarta(c2);
		l.anadirCarta(c3);
		
		l.moverCartaAPosicion(1, c3);
		
		assertEquals(c1, l.obtenerCartaEnPosicion(0));
		assertEquals(c3, l.obtenerCartaEnPosicion(1));
		assertEquals(c2, l.obtenerCartaEnPosicion(2));
	}

	@Test
	public void testMoverAlPrincipio() {
		l.anadirCarta(c1);
		l.anadirCarta(c2);
		l.anadirCarta(c3);
		
		l.moverAlPrincipio(c3);
		
		assertEquals(c3, l.obtenerCartaEnPosicion(0));
		assertEquals(c1, l.obtenerCartaEnPosicion(1));
		assertEquals(c2, l.obtenerCartaEnPosicion(2));
		
	}

	@Test
	public void testSustituirCartaEnPosicion() {
		l.anadirCarta(c1);
		l.anadirCarta(c2);
		
		l.sustituirCartaEnPosicion(0, c3);
		
		assertEquals(c3, l.obtenerCartaEnPosicion(0));
		assertEquals(c2, l.obtenerCartaEnPosicion(1));
	}

	@Test
	public void testSustituirCartaEnUltimaPosicion() {
		l.anadirCarta(c1);
		l.anadirCarta(c2);
		
		l.sustituirCartaEnUltimaPosicion(c3);
		
		assertEquals(c1, l.obtenerCartaEnPosicion(0));
		assertEquals(c3, l.obtenerCartaEnPosicion(1));
	}

	@Test
	public void testSacarCarta() {
		l.anadirCarta(c1);		
		assertFalse(l.vacia());		
		l.sacarCarta(c1);
		
		assertTrue(l.vacia());
	}

	@Test
	public void testVaciar() {
		assertTrue(l.vacia());
		
		l.anadirCarta(c1);		
		assertFalse(l.vacia());
		
		l.vaciar();
		assertTrue(l.vacia());
	}

	@Test
	public void testObtenerCartaEnPosicion() {
		l.anadirCarta(c1);
		
		assertEquals(c1, l.obtenerCartaEnPosicion(0));
		
		l.anadirCarta(c2);
		l.anadirCarta(c3);
		
		assertEquals(c2, l.obtenerCartaEnPosicion(1));
		assertEquals(c3, l.obtenerCartaEnPosicion(2));
	}

	@Test
	public void testObtenerUltimaCarta() {
		l.anadirCarta(c1);
		
		assertEquals(c1, l.obtenerUltimaCarta());
		
		l.anadirCarta(c2);
		l.anadirCarta(c3);
		assertEquals(c3, l.obtenerUltimaCarta());
	}

	@Test
	public void testObtenerNumeroDeAnimalesDeEspecie() {		
		assertEquals(0, l.obtenerNumeroDeAnimalesDeEspecie("Leon"));
		
		l.anadirCarta(c4);
		assertEquals(1, l.obtenerNumeroDeAnimalesDeEspecie("Leon"));
		
		l.anadirCarta(c5);
		assertEquals(2, l.obtenerNumeroDeAnimalesDeEspecie("Leon"));

	}

	@Test
	public void testObtenerEspecieMasFuerte() {
		l.anadirCarta(c3);
		l.anadirCarta(c2);
		assertEquals("Cebra", l.obtenerEspecieMasFuerte());
		
		l.anadirCarta(c5);
		assertEquals("Leon", l.obtenerEspecieMasFuerte());
	}

	@Test
	public void testObtenerEspeciesDeAnimales() {
		assertEquals("", l.obtenerEspeciesDeAnimales());
		
		l.anadirCarta(c3);
		assertEquals("Cebra ", l.obtenerEspeciesDeAnimales());
		
		l.anadirCarta(c5);
		assertEquals("Cebra Leon ", l.obtenerEspeciesDeAnimales());
	}

	@Test
	public void testObtenerInformacionCartas() {
		assertEquals("", l.obtenerInformacionCartas());
		
		l.anadirCarta(c3);
		assertEquals("CebraAzul", l.obtenerInformacionCartas());
		
		l.anadirCarta(c5);
		assertEquals("CebraAzul LeonVerde", l.obtenerInformacionCartas());
	}

	@Test
	public void testObtenerNumeroDeCartas() {
		assertEquals(0, l.obtenerNumeroDeCartas());
		
		l.anadirCarta(c5);
		assertEquals(1, l.obtenerNumeroDeCartas());
		
		l.anadirCarta(c4);
		assertEquals(2, l.obtenerNumeroDeCartas());
	}

	@Test
	public void testObtenerNumeroDeCartasColor() {
		assertEquals(0, l.obtenerNumeroDeCartasColor(EnumColor.AZUL));
		
		l.anadirCarta(c5);
		assertEquals(1, l.obtenerNumeroDeCartasColor(EnumColor.VERDE));
		assertEquals(0, l.obtenerNumeroDeCartasColor(EnumColor.AZUL));

		
		l.anadirCarta(c4);
		assertEquals(1, l.obtenerNumeroDeCartasColor(EnumColor.VERDE));
		assertEquals(1, l.obtenerNumeroDeCartasColor(EnumColor.AZUL));
	}
}
