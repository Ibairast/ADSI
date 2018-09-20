package packModelo;

public class Foca extends Animal {
    public Foca() {
        super.fuerza = 6;
        super.especie = "Foca";
    }

    public void hacerAnimalada() {
        Tablero.getMiTablero().invertirCola();
    }
}
