package packModelo;

import java.util.Random;

public class Maquina extends Jugador {
    public Maquina(String pNombre, EnumColor colorJugador) {
        super(pNombre, colorJugador);
    }

    public void elegirCartaMano(int pPos) {
        super.cartaElegidaMano = super.mano.obtenerCartaAleatoria();
    }

    public void elegirEspecieCola() {
        Tablero tablero = Tablero.getMiTablero();
        this.especieElegidaCola = tablero.obtenerCartaAleatoria().getEspecie();
    }

    public void elegirSaltosCanguro() {
        int max = 2;
        int min = 1;
        this.saltosElegidos = new Random().nextInt(max - min) + min;
    }

    public String obtenerInformacionCartasMano() {
        return "maquina-" + this.mano.obtenerNumeroDeCartas();
    }
}