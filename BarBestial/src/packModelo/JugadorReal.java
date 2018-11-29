package packModelo;

public class JugadorReal extends Jugador {	
    public JugadorReal(String pNombre, EnumColor pColorJugador) {
    	super(pNombre, pColorJugador);
    }  
    
    public void elegirCartaMano(int pPos) {
    	super.cartaElegidaMano = super.obtenerCartaManoEnPosicion(pPos);
    }
    
    public void elegirSaltosCanguro() {
    	this.notificar("saltos- ");
    }

    public void elegirEspecieCola() {
    	Tablero tablero = Tablero.getMiTablero();
    	String especies = tablero.obtenerEspeciesDeAnimalesEnLaCola();
    	this.notificar("especiecola-" + especies);
    }
        
    public String obtenerInformacionCartasMano() {
    	return "jugadorreal-" + this.mano.obtenerInformacionCartas();
    }
    
    public void actualizarEspecieElegida(String pEspecie) {
    	this.especieElegidaCola = pEspecie;
    }

    public void actualizarSaltosCanguro(int pSaltos) {
    	this.saltosElegidos = pSaltos;
    }
}