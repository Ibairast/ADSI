package packVista;

import org.json.JSONArray;
import org.json.JSONObject;
import packControlador.Controlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

public class I_Ranking extends JFrame {
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
    public I_Ranking() {
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
        this.btnMisMejoresPartidas.setBackground(new Color(51, 204, 204));
        this.btnMisMejoresPartidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarMisMejoresPartidas();
            }
        });
        panelMenu.add(btnMisMejoresPartidas);


        this.btnMejorPuntuacionDia = new JButton("Puntuación día");
        this.btnMejorPuntuacionDia.setBackground(new Color(51, 204, 204));
        panelMenu.add(btnMejorPuntuacionDia);

        this.btnMejoresPartidas = new JButton("Mejores partidas");
        this.btnMejoresPartidas.setBackground(new Color(51, 204, 204));
        panelMenu.add(btnMejoresPartidas);

        this.btnMejorMedia = new JButton("Mejor media");
        this.btnMejorMedia.setBackground(new Color(51, 204, 204));
        panelMenu.add(btnMejorMedia);


    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                I_Ranking frame = new I_Ranking();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


//        public void addMisMejoresPartidas(ActionListener listenForBtnMisMejoresPartidas) {
//            mostrarMisMejoresPartidas();
//            //btnMisMejoresPartidas.addActionListener(listenForBtnMisMejoresPartidas);
//        }

    public void addMejorPuntuacionDia(ActionListener listenForBtnMejorPuntuacionDia) {
        btnMejorPuntuacionDia.addActionListener(listenForBtnMejorPuntuacionDia);
    }

    public void addMejoresPartidas(ActionListener listenForBtnMejoresPartidas) {
        btnMejoresPartidas.addActionListener(listenForBtnMejoresPartidas);
    }

    public void addMejorMedia(ActionListener listenForBtnMejorMedia) {
        btnMejorMedia.addActionListener(listenForBtnMejorMedia);
    }


    public void mostrarMisMejoresPartidas() {
        Vector<Vector<String>> puntuaciones = new Vector<>();

        JSONArray json = Controlador.getMiControlador().obtenerMisMejoresPartidas();

        if (json.length() > 0) {
            for (int i = 0; i < json.length(); i++) {
                Vector<String> puntuacion = new Vector<>();
                JSONObject object = json.getJSONObject(i);
                int clave = object.getInt("Puntuacion");
                //System.out.println("Puntuacion: " + clave);
                puntuacion.add(Integer.toString(clave));
                //System.out.println("Size: " + puntuacion.size());
                puntuaciones.add(puntuacion);

            }
        } else {
            Vector<String> puntuacion = new Vector<>();
            puntuacion.add("No hay datos");
            puntuaciones.add(puntuacion);
        }

        Vector<String> columnas = new Vector<>();
        columnas.add("Puntuacion");

        createTable(puntuaciones, columnas);
    }

    private void createTable(Vector<Vector<String>> pData, Vector<String> columnas) {
        table = new JTable(pData, columnas);
        //Table design
        table.getTableHeader().setBackground(new Color(51, 204, 204));
        table.getTableHeader().setFont(new Font("SansSerif", Font.CENTER_BASELINE, 18));
        table.setFont(new Font("SansSerif", Font.ROMAN_BASELINE, 15));
        table.setBackground(new Color(21, 26, 35));
        table.setForeground(new Color(255, 255, 255));
        table.setRowHeight(30);
        contentPane.add(table.getTableHeader(), BorderLayout.NORTH);
        contentPane.add(table, BorderLayout.CENTER);
        table.setFillsViewportHeight(true);
    }

    public void mostrarMejorPuntuacionDia() {

        Vector<Vector<String>> puntuaciones = new Vector<>();

        JSONArray json = Controlador.getMiControlador().obtenerMejorPuntuacionDia();
        if (json.length() > 0) {
            for (int i = 0; i < json.length(); i++) {
                Vector<String> puntuacion = new Vector<>();
                JSONObject object = json.getJSONObject(i);
                String id = object.getString("IdUsuario");
                int punt = object.getInt("Puntuacion");
                puntuacion.add(id);
                puntuacion.add(Integer.toString(punt));
                puntuaciones.add(puntuacion);

            }
        } else {
            Vector<String> puntuacion = new Vector<>();
            puntuacion.add("No hay datos");
            puntuaciones.add(puntuacion);
        }

        Vector<String> columnas = new Vector<>();
        columnas.add("IdUsuario");
        columnas.add("Puntuacion");

        createTable(puntuaciones, columnas);
    }

    public void mostrarMejoresPartidas() {

        Vector<Vector<String>> puntuaciones = new Vector<>();

        JSONArray json = Controlador.getMiControlador().obtenerMejoresPartidas();

        if (json.length() > 0) {
            for (int i = 0; i < json.length(); i++) {
                Vector<String> puntuacion = new Vector<>();
                JSONObject object = json.getJSONObject(i);
                String id = object.getString("IdUsuario");
                int punt = object.getInt("Puntuacion");
                String fecha = object.getString("Fecha");
                //System.out.println("Puntuacion: " + clave);
                puntuacion.add(id);
                puntuacion.add(Integer.toString(punt));
                puntuacion.add(fecha);
                //System.out.println("Size: " + puntuacion.size());
                puntuaciones.add(puntuacion);
            }

        } else {
            Vector<String> puntuacion = new Vector<>();
            puntuacion.add("No hay datos");
            puntuaciones.add(puntuacion);
        }

        Vector<String> columnas = new Vector<>();
        columnas.add("IdUsuario");
        columnas.add("Puntuacion");
        columnas.add("Fecha");

        createTable(puntuaciones, columnas);

    }

    public void mostrarMejorMedia() {

        Vector<Vector<String>> puntuaciones = new Vector<>();

        JSONArray json = Controlador.getMiControlador().obtenerMejorMedia();

        if (json.length() > 0) {
            for (int i = 0; i < json.length(); i++) {
                Vector<String> puntuacion = new Vector<>();

                JSONObject object = json.getJSONObject(i);
                String id = object.getString("IdUsuario");
                String media = object.getString("Media");

                puntuacion.add(id);
                puntuacion.add(media);
                puntuaciones.add(puntuacion);
            }
        } else {
            Vector<String> puntuacion = new Vector<>();
            puntuacion.add("No hay datos");
            puntuaciones.add(puntuacion);
        }

        Vector<String> columnas = new Vector<>();
        columnas.add("IdUsuario");
        columnas.add("Media");

        createTable(puntuaciones, columnas);


    }


}

