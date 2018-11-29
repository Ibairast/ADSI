package packModelo;

public class Cocodrilo extends Animal implements IRecurrente {
    public Cocodrilo() {
        super.fuerza = 10;
        super.especie = "Cocodrilo";
    }

    public void hacerAnimalada() {
        Tablero tablero = Tablero.getMiTablero();

        int siguientePosicion = this.obtenerMiPosicion() - 1;
        
        if (siguientePosicion >= 0) {
            Carta cartaSiguiente = tablero.obtenerCartaEnPosicion(siguientePosicion);
            while (siguientePosicion >= 0 && cartaSiguiente.getFuerza() < fuerza && !(cartaSiguiente.getAnimal() instanceof Cebra)) {
                tablero.sacarDeLaCola(cartaSiguiente);
                siguientePosicion--;
            }
        }
    }
}
