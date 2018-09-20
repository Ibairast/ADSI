package packModelo;

public class Mofeta extends Animal {
    public Mofeta() {
        super.fuerza = 1;
        super.especie = "Mofeta";
    }

    public void hacerAnimalada() {
        Tablero tablero = Tablero.getMiTablero();
        int i = 0;
        String especieMasFuerte = tablero.obtenerEspecieMasFuerteDeLaCola();

        while (i < 2 && !especieMasFuerte.equals("Mofeta")) {
            tablero.sacarTodasLasCartasDeEspecie(especieMasFuerte);
            especieMasFuerte = tablero.obtenerEspecieMasFuerteDeLaCola();
            i++;
        }
    }
}
