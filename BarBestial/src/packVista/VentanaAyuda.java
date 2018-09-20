package packVista;

import javax.swing.*;
import java.awt.*;

public class VentanaAyuda extends JFrame {
	private static final long serialVersionUID = 1L;

	public VentanaAyuda() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Bar Bestial - Ayuda");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JTextArea ayuda = new JTextArea();
        ayuda.setEditable(false);
        ayuda.setLineWrap(true);
        ayuda.setSize(600, 600);
        ayuda.setText("1. Jugar una carta\n" +
                "Elige una carta de animal de tu mano y colócala boca arriba al final de la cola, en la cola" +
                "de entrada. Si la cola está vacía, el animal jugado es el primero de la (nueva) cola ante la" +
                "Puerta del Cielo.\n\n" +
                "2. Realizar la animalada de la carta jugada\n" +
                "Seguidamente, si es posible, realiza la animalada del animal que acabas de jugar.\n\n" +
                "3. Efectuar animaladas «recurrentes»\n" +
                "Hay cuatro clases de animales que muestran en su carta un símbolo circular. Las animaladas «re" +
                "currentes» del hipopótamo, del cocodrilo y de la cebra deben repetirse en cada turno sucesivo" +
                "de cada jugador, si es posible. Lo mismo se aplica en el caso de la jirafa, salvo en el turno en el" +
                "que se ha puesto en la cola." +
                "Empieza el animal más cercano a la puerta y acaba el animal más cercano a la carta de patada.\n\n" +
                "4. Abrir la Puerta del Cielo y provocar una patada\n" +
                "Una vez realizadas todas las animaladas recurrentes, comprueba si hay 5 animales en la cola." +
                "Si hay menos de 5 animales, no sucede nada; el juego continúa con la acción 5: «Robar otra" +
                "carta». Si hay 5 animales en la cola, la Puerta del Cielo se abre; y alguien recibirá una patada." +
                "• Los dos animales que están más cerca de la Puerta del Cielo han conseguido que los dejen\n" +
                "entrar en el bar: ¡colócalos boca abajo sobre la carta de Bar Bestial! Permanecerán ahí hasta" +
                "el final de la partida.\n" +
                "• Al último animal de la cola, le dan la patada: colócalo boca abajo sobre la carta «ES LO QUE" +
                "HAY». Permanecerá ahí hasta el final de la partida. Mala suerte... ¡ese animal se perderá la" +
                "fiesta!\n" +
                "• Los animales que quedan en la cola de entrada avanzan (sin cambiar el orden) hacia la" +
                "Puerta del Cielo.\n\n" +
                "A tener en cuenta: los animales también pueden ir directamente desde la cola de entrada" +
                "hasta la carta «ES LO QUE HAY» debido a las animaladas de otros animales. En tal caso, los" +
                "animales que quedan en la cola de entrada también avanzan hacia la Puerta del Cielo.\n\n" +
                "5. Robar otra carta\n" +
                "Al finalizar su turno, el jugador roba la carta superior de su mazo y la añade a su mano. Si su" +
                "mazo ya se ha agotado, esta acción se omite.\n\n" +
                "Final de la partida y victoria\n" +
                "La partida finaliza en cuanto todos los jugadores han jugado todas sus cartas de animales. El" +
                "jugador con más clientela (sus propios animales) en el Bar Bestial, gana. Si se produce un empate" +
                "entre varios jugadores, los implicados sumarán los valores de carta de sus clientes en el interior" +
                "del bar; gana el jugador cuyos clientes sumen el valor más bajo. Puede haber más de un ganador.");

        add(ayuda);
        pack();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaAyuda frame = new VentanaAyuda();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}