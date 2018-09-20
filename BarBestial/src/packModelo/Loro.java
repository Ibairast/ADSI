package packModelo;

public class Loro extends Animal {
    public Loro() {
        super.fuerza = 2;
        super.especie = "Loro";
    }

    public void hacerAnimalada() {
        Tablero tablero = Tablero.getMiTablero();
        Jugador jugador = Partida.getMiPartida().obtenerJugadorTurnoActual();
        jugador.elegirEspecieCola();

        /* Elige un animal de entre los de la cola. */
        if (tablero.obtenerNumeroDeCartas() > 1) {
            /* Sacar todos los animales de la especie elegida. */
            tablero.sacarTodasLasCartasDeEspecie(jugador.obtenerEspecieElegidaCola());
        }
    }
}
