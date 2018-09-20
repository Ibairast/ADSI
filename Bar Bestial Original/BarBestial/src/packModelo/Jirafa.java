package packModelo;

public class Jirafa extends Animal implements IRecurrente {
    public Jirafa() {
        super.fuerza = 8;
        super.especie = "Jirafa";
    }

    public void hacerAnimalada() {
        Tablero tablero = Tablero.getMiTablero();
        int posJirafa = this.obtenerMiPosicion();
        int siguientePos = posJirafa - 1;

        if (siguientePos >= 0) {
            Carta carta = tablero.obtenerCartaEnPosicion(siguientePos);

            if (carta.getAnimal().getFuerza() < fuerza) {
                tablero.intercambiarCartas(siguientePos, posJirafa);
            }
        }
    }
}
