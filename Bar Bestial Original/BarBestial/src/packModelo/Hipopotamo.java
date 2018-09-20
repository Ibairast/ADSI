package packModelo;

public class Hipopotamo extends Animal implements IRecurrente {
    public Hipopotamo() {
        super.fuerza = 11;
        super.especie = "Hipopotamo";
    }

    public void hacerAnimalada() {
        Tablero tablero = Tablero.getMiTablero();
        int posHipo = this.obtenerMiPosicion();
        int siguientePosicion = posHipo - 1;

        if (siguientePosicion >= 0) {
            Carta carta = tablero.obtenerCartaEnPosicion(siguientePosicion);

            while (siguientePosicion >= 0 && carta.getAnimal().getFuerza() < fuerza && !(carta.getAnimal() instanceof Cebra)) {
                tablero.intercambiarCartas(siguientePosicion, posHipo);
                posHipo = siguientePosicion;
                siguientePosicion--;
                if (siguientePosicion >= 0) {
                    carta = tablero.obtenerCartaEnPosicion(siguientePosicion);
                }
            }
        }
    }
}
