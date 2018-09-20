package packModelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class PartidaTest {
	Partida p = Partida.getMiPartida();
	Jugador j1, j2;

	@Test
	public void testInicializarPartida() {
		p.inicializarPartida("Owen");
		j1 = p.obtenerJugadores().get(0);
		j2 = p.obtenerJugadores().get(1);

		assertEquals("Owen", j1.getNombre());
		assertEquals("Maquina", j2.getNombre());
		
		assertEquals(4, j1.obtenerNumeroDeCartasEnMano());
		
		assertEquals(8, j1.obtenerNumeroDeCartasEnMazo());
		
		assertEquals(4, j2.obtenerNumeroDeCartasEnMano());
		
		assertEquals(8, j2.obtenerNumeroDeCartasEnMazo());
	}
}
