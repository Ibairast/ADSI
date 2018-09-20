package packModelo;

public class Canguro extends Animal {
    public Canguro() {
        super.fuerza = 3;
        super.especie = "Canguro";
    }

    public void hacerAnimalada() {
        Jugador jugador = Partida.getMiPartida().obtenerJugadorTurnoActual();
        Tablero tablero = Tablero.getMiTablero();

        /* Pide al jugador el número de saltos. */
        jugador.elegirSaltosCanguro();
        int saltos = jugador.obtenerSaltosCanguro();

        /* Recoge la última carta (la del canguro). */
        Carta carta = tablero.obtenerUltimaCarta();

        /* Salta. */
        int ultimaPosicion = tablero.obtenerNumeroDeCartas() - 1;
        
        if (ultimaPosicion - saltos >= 0) {
            tablero.moverCartaAPosicion(ultimaPosicion - saltos, carta);
        } else {
            tablero.moverAlPrincipio(carta);
        }
    }
}
