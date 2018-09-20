package packModelo;

public class Serpiente extends Animal {
    public Serpiente() {
        super.fuerza = 9;
        super.especie = "Serpiente";
    }

    public void hacerAnimalada() {
        Tablero.getMiTablero().ordenarPorFuerza();
    }
}
