package packVista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaInicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;


    private JButton btnNuevaPartida;
    private JButton btnInstrucciones;
    private JButton btnRanking;
    private JButton btnCargarPartida;
    private JButton btnCambiarContraseña;
    private JButton btnPersonalizar;


    /**
     * Create the frame.
     */
    public VentanaInicio() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 850, 620);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setBackground(new Color(21, 26, 35));
        setContentPane(contentPane);

        /* Centrar */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize(); //Tamaño del frame actual (ancho x alto)
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(21, 26, 35));
        contentPane.add(panelMenu, BorderLayout.SOUTH);


        this.btnNuevaPartida = new JButton("Nueva Partida");
        this.btnNuevaPartida.setBackground(new Color (51, 204, 204));
        panelMenu.add(btnNuevaPartida);

        this.btnCargarPartida = new JButton("Cargar Partida");
        this.btnCargarPartida.setBackground(new Color (51, 204, 204));
        panelMenu.add(btnCargarPartida);

        this.btnInstrucciones = new JButton("Instrucciones");
        this.btnInstrucciones.setBackground(new Color (51, 204, 204));
        panelMenu.add(btnInstrucciones);

        this.btnRanking = new JButton("Ranking");
        this.btnRanking.setBackground(new Color (51, 204, 204));
        panelMenu.add(btnRanking);

        this.btnPersonalizar = new JButton("Personalizar");
        this.btnPersonalizar.setBackground(new Color (51, 204, 204));
        panelMenu.add(btnPersonalizar);

        this.btnCambiarContraseña = new JButton("Cambiar contraseña");
        this.btnCambiarContraseña.setBackground(new Color (51, 204, 204));
        panelMenu.add(btnCambiarContraseña);

        JPanel panelImagenBar = new JPanel();
        contentPane.add(panelImagenBar, BorderLayout.CENTER);

        JLabel labelBar = new JLabel("");
        labelBar.setIcon(new ImageIcon(VentanaInicio.class.getResource("/images/Bar.png")));
        panelImagenBar.add(labelBar);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaInicio frame = new VentanaInicio();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void addNuevaPartidaListener(ActionListener listenForBtnNuevaPartida) {
        btnNuevaPartida.addActionListener(listenForBtnNuevaPartida);
    }

    public void addInstrucionesListener(ActionListener listenForBtnInstrucciones) {
        btnInstrucciones.addActionListener(listenForBtnInstrucciones);
    }

    public void addRankingListener(ActionListener listenForBtnRanking) {
        btnRanking.addActionListener(listenForBtnRanking);
    }

    public void addCargarPartidaListener(ActionListener listenForBtnCargarPartida) {
        btnCargarPartida.addActionListener(listenForBtnCargarPartida);
    }
    public void addCambiarContraseniaListener(ActionListener listenForBtnCambiarContrasenia) {
        btnNuevaPartida.addActionListener(listenForBtnCambiarContrasenia);
    }
    public void addPersonalizarListener(ActionListener listenForBtnPersonalizar) {
        btnNuevaPartida.addActionListener(listenForBtnPersonalizar);
    }


    public void showNombreErrorMessage() {
        JOptionPane.showMessageDialog(this,
                "Introduce un nombre.");
    }

    public void cerrarVentana() {
        setVisible(false);
        dispose();
    }

}
