package packModelo;

public class Camaleon extends Animal {
    public Camaleon() {
        super.fuerza = 5;
        super.especie = "Camaleon";
    }

    public void hacerAnimalada() {
        Jugador jugador = Partida.getMiPartida().obtenerJugadorTurnoActual();
        Tablero tablero = Tablero.getMiTablero();
        AnimalFactory factory = AnimalFactory.getMiAnimalFactory();

        if (tablero.obtenerNumeroDeCartas() > 1) {
            /* Se elige un animal de la cola. */
            jugador.elegirEspecieCola();
            String especieElegida = jugador.obtenerEspecieElegidaCola();
            
            /* Se guarda el camaleon para luego volver a ponerlo. */
            Carta camaleon = tablero.obtenerUltimaCarta();

            /* Se crea la carta elegida. */
            Carta cartaTemporal = new Carta(factory.crearAnimal(especieElegida), jugador.getColorJugador());

            /* Se elimina y sustituye por el camaleón. */
            tablero.sustituirCartaEnUltimaPosicion(cartaTemporal);

            /* Se realiza la animalada del nuevo animal (del último). */
            tablero.hacerUltimaAnimalada();

            /* Se localiza el nuevo animal. */
            int i = 0;
            boolean enc = false;

            while (i < tablero.obtenerNumeroDeCartas() && !enc) {
                Carta carta = tablero.obtenerCartaEnPosicion(i);

                if (carta.equals(cartaTemporal)) {
                    enc = true;
                }
                i++;
            }

            /* Se sustituye por el camaleón original. */
            tablero.sustituirCartaEnPosicion(i - 1, camaleon);
        }
    }
}
