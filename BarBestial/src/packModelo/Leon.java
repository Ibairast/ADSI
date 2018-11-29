package packModelo;

public class Leon extends Animal {
    public Leon() {
        super.fuerza = 12;
        super.especie = "Leon";
    }

    public void hacerAnimalada() {
        Tablero tablero = Tablero.getMiTablero();
        if (tablero.obtenerNumeroDeAnimalesDeEspecie("Leon") > 1) {
            /* Como el león jugado estará en la última posición, si hay más leones
             * en la cola directamente que salga de la cola el último. */
            tablero.provocarPatada();
        }
        else {
            /* Poner al principio de la cola el león y desplazar a todos los demás
        	 * un puesto hacia atrás. */
            Carta carta = tablero.obtenerUltimaCarta(); // El leon
            tablero.moverAlPrincipio(carta);

            /* Sacar a todos los monos de la cola y enviarlos a EsLoQueHay */
            tablero.sacarTodasLasCartasDeEspecie("Mono");
        }
    }
}
