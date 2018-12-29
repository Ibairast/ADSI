package packModelo;

public abstract class Jugador {

	private String nombre;
	private EnumColor colorJugador;
	private ListaCartas mazo;
	private ListaCartas mano;
	protected Carta cartaElegidaMano;
	protected int saltosElegidos;
	protected String especieElegidaCola;

	/**
	 * 
	 * @param pNombre
	 * @param pColorJugador
	 */
	public Jugador(String pNombre, EnumColor pColorJugador) {
		// TODO - implement Jugador.Jugador
		throw new UnsupportedOperationException();
	}

	public void robarCarta() {
		// TODO - implement Jugador.robarCarta
		throw new UnsupportedOperationException();
	}

	public void repartirCartas() {
		// TODO - implement Jugador.repartirCartas
		throw new UnsupportedOperationException();
	}

	public boolean tieneCartas() {
		// TODO - implement Jugador.tieneCartas
		throw new UnsupportedOperationException();
	}

	public int obtenerNumeroDeCartasEnMano() {
		// TODO - implement Jugador.obtenerNumeroDeCartasEnMano
		throw new UnsupportedOperationException();
	}

	public int obtenerNumeroDeCartasEnMazo() {
		// TODO - implement Jugador.obtenerNumeroDeCartasEnMazo
		throw new UnsupportedOperationException();
	}

	public String getNombre() {
		return this.nombre;
	}

	public EnumColor getColorJugador() {
		return this.colorJugador;
	}

	public String obtenerEspeciesDeAnimalesEnMano() {
		// TODO - implement Jugador.obtenerEspeciesDeAnimalesEnMano
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pInformacion
	 */
	public void notificar(String pInformacion) {
		// TODO - implement Jugador.notificar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pObersvador
	 */
	public void anadirObservador(Observer pObersvador) {
		// TODO - implement Jugador.anadirObservador
		throw new UnsupportedOperationException();
	}

	public String obtenerInformacionCartas() {
		// TODO - implement Jugador.obtenerInformacionCartas
		throw new UnsupportedOperationException();
	}

	public String obtenerEspecieElegidaCola() {
		// TODO - implement Jugador.obtenerEspecieElegidaCola
		throw new UnsupportedOperationException();
	}

	public int obtenerSaltosCanguro() {
		// TODO - implement Jugador.obtenerSaltosCanguro
		throw new UnsupportedOperationException();
	}

	public void jugarTurno() {
		// TODO - implement Jugador.jugarTurno
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pPos
	 */
	public abstract void elegirCartaMano(int pPos);

	public abstract void elegirEspecieCola();

	public abstract void elegirSaltosCanguro();

	public abstract String obtenerInformacioCartasMano();

	/**
	 * 
	 * @param pCarta
	 */
	protected void echarCarta(Carta pCarta) {
		// TODO - implement Jugador.echarCarta
		throw new UnsupportedOperationException();
	}

	protected boolean hayCartasEnMazo() {
		// TODO - implement Jugador.hayCartasEnMazo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pPosCarta
	 */
	protected Carta obtenerCartaManoEnPosicion(int pPosCarta) {
		// TODO - implement Jugador.obtenerCartaManoEnPosicion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pCarta
	 */
	private void anadirCartaALaMano(Carta pCarta) {
		// TODO - implement Jugador.anadirCartaALaMano
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pCarta
	 */
	private void sacarCartaDeLaMano(Carta pCarta) {
		// TODO - implement Jugador.sacarCartaDeLaMano
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pCarta
	 */
	private void anadirCartaAlMazo(Carta pCarta) {
		// TODO - implement Jugador.anadirCartaAlMazo
		throw new UnsupportedOperationException();
	}

	private boolean hayCartasEnMano() {
		// TODO - implement Jugador.hayCartasEnMano
		throw new UnsupportedOperationException();
	}

	public void guardarMano() {
		// TODO - implement Jugador.guardarMano
		throw new UnsupportedOperationException();
	}

	public void guardarMazo() {
		// TODO - implement Jugador.guardarMazo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Mano
	 */
	public void setMano(ListaCartas Mano) {
		// TODO - implement Jugador.setMano
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Mazo
	 */
	public void setMazo(Listacartas Mazo) {
		// TODO - implement Jugador.setMazo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param JSON
	 */
	public void eliminarJugadores(int JSON) {
		// TODO - implement Jugador.eliminarJugadores
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fecha
	 */
	public JSON buscarJugadore(Date fecha) {
		// TODO - implement Jugador.buscarJugadore
		throw new UnsupportedOperationException();
	}

}