package packModelo;

public class Partida {

	private Partida miPartida;
	private int turnoActual;
	private ArrayList<Jugador> listaJugadores;

	private Partida() {
		// TODO - implement Partida.Partida
		throw new UnsupportedOperationException();
	}

	public Partida getMiPartida() {
		return this.miPartida;
	}

	/**
	 * 
	 * @param pNombre
	 */
	public void inicializarPartida(String pNombre) {
		// TODO - implement Partida.inicializarPartida
		throw new UnsupportedOperationException();
	}

	public void jugarTurno() {
		// TODO - implement Partida.jugarTurno
		throw new UnsupportedOperationException();
	}

	public ArrayList<Jugador> obtenerJugadores() {
		// TODO - implement Partida.obtenerJugadores
		throw new UnsupportedOperationException();
	}

	public Jugador obtenerJugadorTurnoActual() {
		// TODO - implement Partida.obtenerJugadorTurnoActual
		throw new UnsupportedOperationException();
	}

	private void repartirCartas() {
		// TODO - implement Partida.repartirCartas
		throw new UnsupportedOperationException();
	}

	private void avanzarTurno() {
		// TODO - implement Partida.avanzarTurno
		throw new UnsupportedOperationException();
	}

	private void finalizar() {
		// TODO - implement Partida.finalizar
		throw new UnsupportedOperationException();
	}

	private boolean comprobarFinalizacion() {
		// TODO - implement Partida.comprobarFinalizacion
		throw new UnsupportedOperationException();
	}

	private String obtenerInformacionGanador() {
		// TODO - implement Partida.obtenerInformacionGanador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pColor
	 */
	private int obtenerNumeroDeCartasColorEnBar(EnumColor pColor) {
		// TODO - implement Partida.obtenerNumeroDeCartasColorEnBar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pColor
	 */
	private int obtenerFuerzaColorEnBar(EnumColor pColor) {
		// TODO - implement Partida.obtenerFuerzaColorEnBar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pInformacionGanador
	 */
	private void anadirGanadorDatabase(String pInformacionGanador) {
		// TODO - implement Partida.anadirGanadorDatabase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pInformacion
	 */
	private void notificar(String pInformacion) {
		// TODO - implement Partida.notificar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param NombreP
	 */
	public void guardarPartida(String NombreP) {
		// TODO - implement Partida.guardarPartida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Mazo
	 * @param Ayudas
	 */
	public void setMazoAyudas(String Mazo, int Ayudas) {
		// TODO - implement Partida.setMazoAyudas
		throw new UnsupportedOperationException();
	}

}