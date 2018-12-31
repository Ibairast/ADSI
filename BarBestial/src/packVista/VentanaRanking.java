package packVista;

import org.json.JSONArray;
import org.json.JSONObject;
import packControlador.Controlador;
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


        this.btnMisMejoresPartidas = new JButton("Mis Mejores Partidas");
        this.btnMisMejoresPartidas.setBackground(new Color (51, 204, 204));
        panelMenu.add(btnMisMejoresPartidas);


        this.btnMejorPuntuacionDia = new JButton("Puntuación día");
        this.btnMejorPuntuacionDia.setBackground(new Color (51, 204, 204));
        panelMenu.add(btnMejorPuntuacionDia);

        this.btnMejoresPartidas = new JButton("Mejores partidas");
        this.btnMejoresPartidas.setBackground(new Color (51, 204, 204));
        panelMenu.add(btnMejoresPartidas);

        this.btnMejorMedia = new JButton("Mejor media");
        this.btnMejorMedia.setBackground(new Color (51, 204, 204));
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


    public void mostrarMisMejoresPartidas(){
        Vector<Vector<String>> puntuaciones = new Vector<>();

        JSONArray json = Controlador.getMiControlador().obtenerMisMejoresPartidas();

        for (int i = 0; i < json.length(); i++) {
            Vector<String> puntuacion = new Vector<>();
            JSONObject object = json.getJSONObject(i);
            int clave = object.getInt("Puntuacion");
            //System.out.println("Puntuacion: " + clave);
            puntuacion.add(Integer.toString(clave));
            //System.out.println("Size: " + puntuacion.size());
            puntuaciones.add(puntuacion);

        }

        //System.out.println(puntuaciones.toString());

        Vector<String> columnas = new Vector<>();
        //System.out.println(columnas.toString());
        columnas.add("Puntuacion");

        createTable(puntuaciones, columnas);
    }

    private void createTable(Vector<Vector<String>> pData, Vector<String> columnas){
        table = new JTable (pData, columnas);
        //Table design
        table.getTableHeader().setBackground(new Color (51, 204, 204));
        table.getTableHeader().setFont(new Font("SansSerif", Font.CENTER_BASELINE, 18));
        table.setFont(new Font("SansSerif", Font.ROMAN_BASELINE,  15));
        table.setBackground(new Color(21, 26, 35));
        table.setForeground(new Color(255, 255, 255));
        table.setRowHeight(30);
        contentPane.add(table.getTableHeader(), BorderLayout.NORTH);
        contentPane.add(table, BorderLayout.CENTER);
        table.setFillsViewportHeight(true);
    }

    public void mostrarMejorPuntuacionDia(){ ////SIN TERMINAR

        Vector<Vector<String>> puntuaciones = new Vector<>();

        JSONArray json = Controlador.getMiControlador().obtenerMejorPuntuacionDia();

        for (int i = 0; i < json.length(); i++) {
            Vector<String> puntuacion = new Vector<>();
            JSONObject object = json.getJSONObject(i);
            String id = object.getString("IdUsuario");
            int punt = object.getInt("Puntuacion");
            //System.out.println("Puntuacion: " + clave);
            puntuacion.add(id);
            puntuacion.add(Integer.toString(punt));
            //System.out.println("Size: " + puntuacion.size());
            puntuaciones.add(puntuacion);

        }

        //System.out.println(puntuaciones.toString());

        Vector<String> columnas = new Vector<>();
        //System.out.println(columnas.toString());
        columnas.add("IdUsuario");
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

