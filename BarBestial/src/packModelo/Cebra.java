package packModelo;

public class Cebra extends Animal implements IRecurrente {
    public Cebra() {
        super.fuerza = 7;
        super.especie = "Cebra";
    }

    public void hacerAnimalada() { }
}
