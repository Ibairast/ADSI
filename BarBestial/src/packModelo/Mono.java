package packModelo;

public class Mono extends Animal {
    public Mono() {
        super.fuerza = 4;
        super.especie = "Mono";
    }

    public void hacerAnimalada() {
        Tablero tablero = Tablero.getMiTablero();
        AnimalFactory factory = AnimalFactory.getMiAnimalFactory();

        if (tablero.obtenerNumeroDeAnimalesDeEspecie("Mono") > 1) {
            /* Todos los hipopótamos se van. */
            tablero.sacarTodasLasCartasDeEspecie("Hipopotamo");

            /* Todos los cocodrilos se van. */
            tablero.sacarTodasLasCartasDeEspecie("Cocodrilo");

            /* Todos los monos se cuelan y se ponen los primeros en orden inverso. */
            int i = 0;

            Animal animal = factory.crearAnimal("Mono");

            while (i < tablero.obtenerNumeroDeCartas()) {
                Carta carta = tablero.obtenerCartaEnPosicion(i);

                if (carta.getAnimal().getClass().equals(animal.getClass())) {
                    tablero.moverAlPrincipio(carta);
                }
                i++;
            }
        }

    }
}
