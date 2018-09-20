package packVista;

import packModelo.JugadorReal;
import packModelo.Partida;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class VentanaJuego extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JPanel panelJugadorReal;
    private JLabel labelCartaJugReal1;
    private JPanel panelCola;
    private JLabel labelCartaCola1;
    private JPanel panelPuertaCielo;
    private JLabel labelPuertaCielo;
    private JPanel panelPatada;
    private JLabel labelPatada;
    private JLabel labelCartaCola2;
    private JLabel labelCartaCola3;
    private JLabel labelCartaCola4;
    private JLabel labelCartaCola5;
    private JLabel labelCartaJugReal2;
    private JLabel labelCartaJugReal3;
    private JLabel labelCartaJugReal4;
    private JLabel labelMazoJugReal;
    private JPanel panelMaquina;
    private JLabel labelCartaMaq1;
    private JLabel labelCartaMaq2;
    private JLabel labelCartaMaq3;
    private JLabel labelCartaMaq4;
    private JLabel labelMazoMaq;
    private JPanel panelCartasJugadorReal;
    private JPanel panelElegirCartas;
    private JButton btnElegirCarta1;
    private JButton btnElegirCarta2;
    private JButton btnElegirCarta3;
    private JButton btnElegirCarta4;
    private JLabel bestLabel3;
    private JLabel bestLabel4;
    private JButton btnJugarTurno;
    private JButton btnSiguiente;

    /**
     * Create the frame.
     */
    public VentanaJuego() {
        setTitle("BarBestial");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width - 100, screenSize.height - 100);

        setBounds(0, 0, 1300, 900);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        panelJugadorReal = new JPanel();
        contentPane.add(panelJugadorReal, BorderLayout.SOUTH);
        panelJugadorReal.setLayout(new BorderLayout(0, 0));

        panelCartasJugadorReal = new JPanel();
        panelJugadorReal.add(panelCartasJugadorReal, BorderLayout.NORTH);
        panelCartasJugadorReal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        labelCartaJugReal1 = new JLabel("");
        panelCartasJugadorReal.add(labelCartaJugReal1);
        labelCartaJugReal1.setPreferredSize(new Dimension(160, 250));

        labelCartaJugReal2 = new JLabel("");
        panelCartasJugadorReal.add(labelCartaJugReal2);
        labelCartaJugReal2.setPreferredSize(new Dimension(160, 250));

        labelCartaJugReal3 = new JLabel("");
        panelCartasJugadorReal.add(labelCartaJugReal3);
        labelCartaJugReal3.setPreferredSize(new Dimension(160, 250));

        labelCartaJugReal4 = new JLabel("");
        panelCartasJugadorReal.add(labelCartaJugReal4);
        labelCartaJugReal4.setPreferredSize(new Dimension(160, 250));

        bestLabel3 = new JLabel("");
        panelCartasJugadorReal.add(bestLabel3);
        bestLabel3.setPreferredSize(new Dimension(80, 250));

        labelMazoJugReal = new JLabel("");
        panelCartasJugadorReal.add(labelMazoJugReal);
        labelMazoJugReal.setPreferredSize(new Dimension(160, 250));

        panelElegirCartas = new JPanel();
        panelJugadorReal.add(panelElegirCartas, BorderLayout.SOUTH);

        //Tablero.getMiTablero().anadirObservador(this);

        btnElegirCarta1 = new JButton("Elegir");
        btnElegirCarta1.setPreferredSize(new Dimension(160, 25));
        panelElegirCartas.add(btnElegirCarta1);


        btnElegirCarta2 = new JButton("Elegir");
        btnElegirCarta2.setPreferredSize(new Dimension(160, 25));
        panelElegirCartas.add(btnElegirCarta2);

        btnElegirCarta3 = new JButton("Elegir");
        btnElegirCarta3.setPreferredSize(new Dimension(160, 25));
        panelElegirCartas.add(btnElegirCarta3);

        btnElegirCarta4 = new JButton("Elegir");
        btnElegirCarta4.setPreferredSize(new Dimension(160, 25));
        panelElegirCartas.add(btnElegirCarta4);

        btnJugarTurno = new JButton("Jugar turno");
        btnJugarTurno.setPreferredSize(new Dimension(120, 25));
        panelElegirCartas.add(btnJugarTurno);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setPreferredSize(new Dimension(120, 25));
        panelElegirCartas.add(btnSiguiente);

        panelCola = new JPanel();
        contentPane.add(panelCola, BorderLayout.CENTER);

        labelCartaCola1 = new JLabel("");
        labelCartaCola1.setHorizontalAlignment(SwingConstants.CENTER);
        labelCartaCola1.setPreferredSize(new Dimension(160, 250));
        panelCola.setLayout(new GridLayout(0, 5, 0, 0));
        panelCola.add(labelCartaCola1);

        labelCartaCola2 = new JLabel("");
        labelCartaCola2.setPreferredSize(new Dimension(160, 250));
        labelCartaCola2.setHorizontalAlignment(SwingConstants.CENTER);
        panelCola.add(labelCartaCola2);

        labelCartaCola3 = new JLabel("");
        labelCartaCola3.setPreferredSize(new Dimension(160, 250));
        labelCartaCola3.setHorizontalAlignment(SwingConstants.CENTER);
        panelCola.add(labelCartaCola3);

        labelCartaCola4 = new JLabel("");
        labelCartaCola4.setPreferredSize(new Dimension(160, 250));
        labelCartaCola4.setHorizontalAlignment(SwingConstants.CENTER);
        panelCola.add(labelCartaCola4);

        labelCartaCola5 = new JLabel("");
        labelCartaCola5.setPreferredSize(new Dimension(160, 250));
        labelCartaCola5.setHorizontalAlignment(SwingConstants.CENTER);
        panelCola.add(labelCartaCola5);

        panelPuertaCielo = new JPanel();
        contentPane.add(panelPuertaCielo, BorderLayout.WEST);
        panelPuertaCielo.setLayout(new GridLayout(0, 1, 0, 0));

        labelPuertaCielo = new JLabel("");
        labelPuertaCielo.setIcon(new ImageIcon(VentanaJuego.class.getResource("/images/PuertaCielo.jpg")));
        panelPuertaCielo.add(labelPuertaCielo);

        panelPatada = new JPanel();
        contentPane.add(panelPatada, BorderLayout.EAST);
        panelPatada.setLayout(new GridLayout(0, 1, 0, 0));

        labelPatada = new JLabel("");
        labelPatada.setIcon(new ImageIcon(VentanaJuego.class.getResource("/images/Patada.jpg")));
        panelPatada.add(labelPatada);

        panelMaquina = new JPanel();
        contentPane.add(panelMaquina, BorderLayout.NORTH);

        labelMazoMaq = new JLabel("");
        labelMazoMaq.setPreferredSize(new Dimension(160, 250));
        panelMaquina.add(labelMazoMaq);

        bestLabel4 = new JLabel("");
        bestLabel4.setPreferredSize(new Dimension(80, 250));
        panelMaquina.add(bestLabel4);

        labelCartaMaq1 = new JLabel("");
        labelCartaMaq1.setPreferredSize(new Dimension(160, 250));
        panelMaquina.add(labelCartaMaq1);

        labelCartaMaq2 = new JLabel("");
        labelCartaMaq2.setPreferredSize(new Dimension(160, 250));
        panelMaquina.add(labelCartaMaq2);

        labelCartaMaq3 = new JLabel("");
        labelCartaMaq3.setPreferredSize(new Dimension(160, 250));
        panelMaquina.add(labelCartaMaq3);

        labelCartaMaq4 = new JLabel("");
        labelCartaMaq4.setPreferredSize(new Dimension(160, 250));
        panelMaquina.add(labelCartaMaq4);

        /* Centrar */
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize(); //Tamaño del frame actual (ancho x alto)
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaJuego frame = new VentanaJuego();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /* Este método llamara a render */
    @Override
    public void update(Observable o, Object arg) {
        /* Los metodos de notificacion incluyen un identificador
         * en forma de String que nos indica que es lo que ha cambiado
         * sin conocer siquiera que la clase que nos notifica el cambio existe
         *
         * Casos posibles:
         * -Actualizar tablero
         * tablero-FocaAzul LoroVerde FocaVerde
         *
         * -Actualizar mano del JugadorReal
         * jugadorreal-FocaAzul LoroVerde FocaVerde_mazovacio
         *
         * -Actualizar mano máquina (solo se muestran los reversos, por lo
         * que es suficiente con saber el número de cartas que tiene
         * maquina-3_mazonovacio
         *
         * -Pedir elegir especie de la cola
         * especiecola-Leon Cebra Loro
         *
         * -Pedir saltos
         * saltos-
         *
         * -Notificar finalización (nombre, seguido de numero de cartas en
         * Bar, seguido de fuerza de las cartas en Bar
         * fin-Owen 4 25
         */

        String motivoDelCambio = ((String) arg).split("-")[0];
        String informacionCambio = ((String) arg).split("-")[1];

        switch (motivoDelCambio) {
            case "tablero":
                this.renderCola(informacionCambio);
                break;
            case "jugadorreal":
                this.renderManoJugadorReal(informacionCambio);
                break;
            case "maquina":
                this.renderManoMaquina(informacionCambio);
                break;
            case "especiecola":
                this.popUpEspeciesCola(informacionCambio);
                break;
            case "saltos":
                this.popUpSaltosCanguro();
                break;
            case "fin":
                this.mostrarGanador(informacionCambio);

        }
    }

    public void activarBotonesElegir() {
        if (labelCartaJugReal1.getIcon() != null) {
            btnElegirCarta1.setEnabled(true);
        }
        if (labelCartaJugReal2.getIcon() != null) {
            btnElegirCarta2.setEnabled(true);
        }
        if (labelCartaJugReal3.getIcon() != null) {
            btnElegirCarta3.setEnabled(true);
        }
        if (labelCartaJugReal4.getIcon() != null) {
            btnElegirCarta4.setEnabled(true);
        }
    }

    public void desactivarBotonesElegir() {
        btnElegirCarta1.setEnabled(false);
        btnElegirCarta2.setEnabled(false);
        btnElegirCarta3.setEnabled(false);
        btnElegirCarta4.setEnabled(false);
    }

    public void activarBotonJugarTurno() {
        btnJugarTurno.setEnabled(true);
    }

    public void desactivarBotonJugarTurno() {
        btnJugarTurno.setEnabled(false);
    }

    public void activarBotonSiguiente() {
        btnSiguiente.setEnabled(true);
    }

    public void desactivarBotonSiguiente() {
        btnSiguiente.setEnabled(false);
    }

    public void addJugarTurnoListener(ActionListener listenForBtnJugarTurno) {
        btnJugarTurno.addActionListener(listenForBtnJugarTurno);
    }

    public void addSiguienteListener(ActionListener listenForBtnSiguiente) {
        btnSiguiente.addActionListener(listenForBtnSiguiente);
    }

    public void addElegirCarta1Listener(ActionListener listenForBtnElegirCarta1) {
        btnElegirCarta1.addActionListener(listenForBtnElegirCarta1);
    }

    public void addElegirCarta2Listener(ActionListener listenForBtnElegirCarta2) {
        btnElegirCarta2.addActionListener(listenForBtnElegirCarta2);
    }

    public void addElegirCarta3Listener(ActionListener listenForBtnElegirCarta3) {
        btnElegirCarta3.addActionListener(listenForBtnElegirCarta3);
    }

    public void addElegirCarta4Listener(ActionListener listenForBtnElegirCarta4) {
        btnElegirCarta4.addActionListener(listenForBtnElegirCarta4);
    }

    private void popUpEspeciesCola(String pInfoEspeciesCola) {
        Object[] posibilidades = pInfoEspeciesCola.split(" ");
        String especie = (String) JOptionPane.showInputDialog(
                this,
                "Elige un animal de la cola:",
                "Elegir animal",
                JOptionPane.PLAIN_MESSAGE,
                null,
                posibilidades,
                "ham");
        Partida partida = Partida.getMiPartida();
        JugadorReal j = (JugadorReal) partida.obtenerJugadorTurnoActual();
        j.actualizarEspecieElegida(especie);
    }

    private void popUpSaltosCanguro() {
        Object[] posibilidades = {"1", "2"};
        String saltos = (String) JOptionPane.showInputDialog(
                this,
                "Elige el número de saltos:",
                "Elegir saltos",
                JOptionPane.PLAIN_MESSAGE,
                null,
                posibilidades,
                "ham");
        Partida partida = Partida.getMiPartida();
        JugadorReal j = (JugadorReal) partida.obtenerJugadorTurnoActual();
        j.actualizarSaltosCanguro(Integer.parseInt(saltos));
    }

    private void renderCola(String pInformacionCartasCola) {
        String[] informacionCartasCola = pInformacionCartasCola.split(" ");
        ArrayList<String> arrayListInformacionCartasCola = new ArrayList<>();
        arrayListInformacionCartasCola.addAll(Arrays.asList(informacionCartasCola));

        switch (arrayListInformacionCartasCola.size()) {
            default:
                labelCartaCola1.setIcon(null);
                labelCartaCola2.setIcon(null);
                labelCartaCola3.setIcon(null);
                labelCartaCola4.setIcon(null);
                labelCartaCola5.setIcon(null);
                break;
            case 1:
                labelCartaCola1.setIcon(this.seleccionarImagenCarta(informacionCartasCola[0]));
                labelCartaCola2.setIcon(null);
                labelCartaCola3.setIcon(null);
                labelCartaCola4.setIcon(null);
                labelCartaCola5.setIcon(null);
                break;
            case 2:
                labelCartaCola1.setIcon(this.seleccionarImagenCarta(informacionCartasCola[0]));
                labelCartaCola2.setIcon(this.seleccionarImagenCarta(informacionCartasCola[1]));
                labelCartaCola3.setIcon(null);
                labelCartaCola4.setIcon(null);
                labelCartaCola5.setIcon(null);
                break;
            case 3:
                labelCartaCola1.setIcon(this.seleccionarImagenCarta(informacionCartasCola[0]));
                labelCartaCola2.setIcon(this.seleccionarImagenCarta(informacionCartasCola[1]));
                labelCartaCola3.setIcon(this.seleccionarImagenCarta(informacionCartasCola[2]));
                labelCartaCola4.setIcon(null);
                labelCartaCola5.setIcon(null);
                break;
            case 4:
                labelCartaCola1.setIcon(this.seleccionarImagenCarta(informacionCartasCola[0]));
                labelCartaCola2.setIcon(this.seleccionarImagenCarta(informacionCartasCola[1]));
                labelCartaCola3.setIcon(this.seleccionarImagenCarta(informacionCartasCola[2]));
                labelCartaCola4.setIcon(this.seleccionarImagenCarta(informacionCartasCola[3]));
                labelCartaCola5.setIcon(null);
                break;
            case 5:
                labelCartaCola1.setIcon(this.seleccionarImagenCarta(informacionCartasCola[0]));
                labelCartaCola2.setIcon(this.seleccionarImagenCarta(informacionCartasCola[1]));
                labelCartaCola3.setIcon(this.seleccionarImagenCarta(informacionCartasCola[2]));
                labelCartaCola4.setIcon(this.seleccionarImagenCarta(informacionCartasCola[3]));
                labelCartaCola5.setIcon(this.seleccionarImagenCarta(informacionCartasCola[4]));
                break;

        }

    }

    private void renderManoJugadorReal(String pInformacionCartas) {
        String infoMano = pInformacionCartas.split("_")[0];
        String infoMazo = pInformacionCartas.split("_")[1];

        if (infoMazo.equals("mazonovacio")) {
            labelMazoJugReal.setIcon(seleccionarImagenCarta("Reverso"));
        } else {
            labelMazoJugReal.setIcon(null);
        }

        String[] informacionCartas = infoMano.split(" ");
        ArrayList<String> arrayListInformacionCartas = new ArrayList<>();
        arrayListInformacionCartas.addAll(Arrays.asList(informacionCartas));

        switch (arrayListInformacionCartas.size()) {
            default:
                labelCartaJugReal1.setIcon(null);
                labelCartaJugReal2.setIcon(null);
                labelCartaJugReal3.setIcon(null);
                labelCartaJugReal4.setIcon(null);
                break;
            case 1:
                labelCartaJugReal1.setIcon(this.seleccionarImagenCarta(informacionCartas[0]));
                labelCartaJugReal2.setIcon(null);
                labelCartaJugReal3.setIcon(null);
                labelCartaJugReal4.setIcon(null);
                break;
            case 2:
                labelCartaJugReal1.setIcon(this.seleccionarImagenCarta(informacionCartas[0]));
                labelCartaJugReal2.setIcon(this.seleccionarImagenCarta(informacionCartas[1]));
                labelCartaJugReal3.setIcon(null);
                labelCartaJugReal4.setIcon(null);
                break;
            case 3:
                labelCartaJugReal1.setIcon(this.seleccionarImagenCarta(informacionCartas[0]));
                labelCartaJugReal2.setIcon(this.seleccionarImagenCarta(informacionCartas[1]));
                labelCartaJugReal3.setIcon(this.seleccionarImagenCarta(informacionCartas[2]));
                labelCartaJugReal4.setIcon(null);
                break;
            case 4:
                labelCartaJugReal1.setIcon(this.seleccionarImagenCarta(informacionCartas[0]));
                labelCartaJugReal2.setIcon(this.seleccionarImagenCarta(informacionCartas[1]));
                labelCartaJugReal3.setIcon(this.seleccionarImagenCarta(informacionCartas[2]));
                labelCartaJugReal4.setIcon(this.seleccionarImagenCarta(informacionCartas[3]));
                break;
        }
    }

    private void renderManoMaquina(String pInformacionCartas) {
        String infoMano = pInformacionCartas.split("_")[0];
        String infoMazo = pInformacionCartas.split("_")[1];

        if (infoMazo.equals("mazonovacio")) {
            labelMazoMaq.setIcon(seleccionarImagenCarta("Reverso"));
        } else {
            labelMazoMaq.setIcon(null);
        }

        switch (infoMano) {
            default:
                labelCartaMaq1.setIcon(null);
                labelCartaMaq2.setIcon(null);
                labelCartaMaq3.setIcon(null);
                labelCartaMaq4.setIcon(null);
                break;
            case "1":
                labelCartaMaq1.setIcon(this.seleccionarImagenCarta("Reverso"));
                labelCartaMaq2.setIcon(null);
                labelCartaMaq3.setIcon(null);
                labelCartaMaq4.setIcon(null);
                break;
            case "2":
                labelCartaMaq1.setIcon(this.seleccionarImagenCarta("Reverso"));
                labelCartaMaq2.setIcon(this.seleccionarImagenCarta("Reverso"));
                labelCartaMaq3.setIcon(null);
                labelCartaMaq4.setIcon(null);
                break;
            case "3":
                labelCartaMaq1.setIcon(this.seleccionarImagenCarta("Reverso"));
                labelCartaMaq2.setIcon(this.seleccionarImagenCarta("Reverso"));
                labelCartaMaq3.setIcon(this.seleccionarImagenCarta("Reverso"));
                labelCartaMaq4.setIcon(null);
                break;
            case "4":
                labelCartaMaq1.setIcon(this.seleccionarImagenCarta("Reverso"));
                labelCartaMaq2.setIcon(this.seleccionarImagenCarta("Reverso"));
                labelCartaMaq3.setIcon(this.seleccionarImagenCarta("Reverso"));
                labelCartaMaq4.setIcon(this.seleccionarImagenCarta("Reverso"));
                break;
        }
    }

    private void mostrarGanador(String pInfoGanador) {
        String ganador = pInfoGanador.split(" ")[0];
        if (ganador.equals("Empate")) {
            JOptionPane.showMessageDialog(this,
                    "No quedan más cartas, ha habido un EMPATE");
        } else {
            String numeroDeCartasGanador = pInfoGanador.split(" ")[1];
            String fuerzaGanador = pInfoGanador.split(" ")[2];

            JOptionPane.showMessageDialog(this,
                    "No quedan más cartas, el ganador es: " + ganador
                            + " con un total de " + numeroDeCartasGanador + " cartas"
                            + " y " + fuerzaGanador + " puntos de fuerza.");
        }

    }

    private ImageIcon seleccionarImagenCarta(String pInformacionCarta) {
        try {
            return new ImageIcon(VentanaJuego.class.getResource("/images/" + pInformacionCarta + ".jpg"));
        } catch (NullPointerException e) {
            return null;
        }
    }
}
