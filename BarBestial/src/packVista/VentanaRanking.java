package packVista;

import org.json.JSONObject;
import packControlador.GestorRanking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

public class VentanaRanking extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;

    private JButton btnMisMejoresPartidas;
    private JButton btnMejorPuntuacionDia;
    private JButton btnMejoresPartidas;
    private JButton btnMejorMedia;



    /**
     * Create the frame.
     */
    public VentanaRanking() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 850, 620);

        //setBounds(100, 100, 800, 220);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

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
        contentPane.add(panelMenu, BorderLayout.SOUTH);


        this.btnMisMejoresPartidas = new JButton("Mis Mejores Partidas");
        //this.btnMejoresPartidas.setBackground(new Color (36, 47, 65));
        panelMenu.add(btnMisMejoresPartidas);

        this.btnMejorPuntuacionDia = new JButton("Puntuación día");
        panelMenu.add(btnMejorPuntuacionDia);

        this.btnMejoresPartidas = new JButton("Mejores partidas");
        panelMenu.add(btnMejoresPartidas);

        this.btnMejorMedia = new JButton("Mejor media");
        panelMenu.add(btnMejorMedia);


    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaRanking frame = new VentanaRanking();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public void addMisMejoresPartidas(ActionListener listenForBtnMisMejoresPartidas) {
        btnMisMejoresPartidas.addActionListener(listenForBtnMisMejoresPartidas);
    }

    public void addMejorPuntuacionDia(ActionListener listenForBtnMejorPuntuacionDia) {
        btnMejorPuntuacionDia.addActionListener(listenForBtnMejorPuntuacionDia);
    }

    public void addMejoresPartidas(ActionListener listenForBtnMejoresPartidas) {
        btnMejoresPartidas.addActionListener(listenForBtnMejoresPartidas);
    }

    public void addMejorMedia(ActionListener listenForBtnMejorMedia) {
        btnMejorMedia.addActionListener(listenForBtnMejorMedia);
    }


    public void obtenerMisMejoresPartidas(){
        Vector<String> puntuacion = new Vector<>();
        Vector<Vector<String>> puntuaciones = new Vector<>();

        JSONObject json = GestorRanking.getMiGestorRanking().obtenerMisMejoresPartidas();
        Iterator<String> itr = json.keys();
        while (itr.hasNext()){
            String key = itr.next();
            String value = (String) json.get(key);
            puntuacion.add(value);
        }

        puntuaciones.add(puntuacion);


        Vector<String> columnas = new Vector<>();
        columnas.add("Puntuacion");
        createTable(puntuaciones, columnas);
    }

    private void createTable(Vector<Vector<String>> pData, Vector<String> columnas){
        table = new JTable (pData, columnas);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(table.getTableHeader(), BorderLayout.PAGE_START);
        contentPane.add(table, BorderLayout.CENTER);
        table.setFillsViewportHeight(true);
    }

    public void obtenerMejorPuntuacionDia(){ ////SIN TERMINAR

        Vector<String> puntuacion = new Vector<>();
        Vector<Vector<String>> puntuaciones = new Vector<>();

        JSONObject json = GestorRanking.getMiGestorRanking().obtenerMejorPuntuacionDia();
        Iterator<String> itr = json.keys();

        puntuaciones.add(puntuacion);


        Vector<String> columnas = new Vector<>();
        columnas.add("Usuario");
        columnas.add("Puntuacion");
        createTable(puntuaciones, columnas);

    }

    public void obtenerMejoresPartidas(){ ////SIN TERMINAR
        Vector<String> columnas = new Vector<>();
        columnas.add("Usuario");
        columnas.add("Puntuacion");
        columnas.add("Fecha");


    }

    public void obtenerMejorMedia(){ ////SIN TERMINAR
        Vector<String> columnas = new Vector<>();
        columnas.add("Usuario");
        columnas.add("Media");

    }



}

