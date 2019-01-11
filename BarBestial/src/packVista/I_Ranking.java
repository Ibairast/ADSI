package packVista;

import org.json.JSONArray;
import org.json.JSONObject;
import packControlador.Controlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Vector;

public class I_Ranking extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    private JButton btnMisMejoresPartidas;
    private JButton btnMejorPuntuacionDia;
    private JButton btnMejoresPartidas;
    private JButton btnMejorMedia;
    private JButton btnCancelar;

    private boolean accesoJuego;


    /**
     * Create the frame.
     */
    public I_Ranking() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        /* Botones */
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
        this.btnMejorPuntuacionDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarMejorPuntuacionDia();
            }
        });
        panelMenu.add(btnMejorPuntuacionDia);

        this.btnMejoresPartidas = new JButton("Mejores partidas");
        this.btnMejoresPartidas.setBackground(new Color(51, 204, 204));
        this.btnMejoresPartidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarMejoresPartidas();
            }
        });
        panelMenu.add(btnMejoresPartidas);

        this.btnMejorMedia = new JButton("Mejor media");
        this.btnMejorMedia.setBackground(new Color(51, 204, 204));
        this.btnMejorMedia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarMejorMedia();
            }
        });
        panelMenu.add(btnMejorMedia);

        this.btnCancelar = new JButton("Cancelar");
        this.btnCancelar.setBackground(new Color(217, 30, 17));
        this.btnCancelar.setForeground(new Color(255, 255, 255));

        this.btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (accesoJuego == true){
                    Controlador.getMiControlador().cerrarVentanaRankingJuego();
                    accesoJuego = false;
                } else{
                    Controlador.getMiControlador().cerrarVentanaRankingMenu();
                }
            }
        });
        panelMenu.add(btnCancelar);


        accesoJuego = false;
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


    /**
     * Muestra una tabla con las mejores partidas del usuario
     */
    public void mostrarMisMejoresPartidas() {
        // Vector en el que se almacenará los datos y campos de la tabla
        Vector<Vector<String>> puntuaciones = new Vector<>();

        // Llamada al gestor que conecta con la base de datos
        JSONArray json = Controlador.getMiControlador().obtenerMisMejoresPartidas();

        // Guarda los datos del json en Vector<String> y a su vez se va añadinedo al Vector<Vector<String>>

        if (json.length() > 0) { // En caso de que haya datos en la base de datos
            for (int i = 0; i < json.length(); i++) {
                Vector<String> puntuacion = new Vector<>();
                JSONObject object = json.getJSONObject(i);
                int clave = object.getInt("Puntuacion");
                puntuacion.add(Integer.toString(clave));
                puntuaciones.add(puntuacion);
            }
        } else { // En caso de que no haya datos almacenados en la base de datos
            Vector<String> puntuacion = new Vector<>();
            puntuacion.add("No hay datos");
            puntuaciones.add(puntuacion);
        }


        // Se crean los campos de la tabla
        Vector<String> columnas = new Vector<>();
        columnas.add("Puntuacion");

        // Se crea la tabla
        crearTabla(puntuaciones, columnas);
    }

    /**
     * Muestra una tabla con la mejor puntuación del día
     */
    public void mostrarMejorPuntuacionDia() {
        // Vector en el que se almacenará los datos y campos de la tabla
        Vector<Vector<String>> puntuaciones = new Vector<>();

        // Llamada al gestor que conecta con la base de datos
        JSONArray json = Controlador.getMiControlador().obtenerMejorPuntuacionDia();

        // Guarda los datos del json en Vector<String> y a su vez se va añadinedo al Vector<Vector<String>>

        if (json.length() > 0) { // En caso de que haya datos en la base de datos
            for (int i = 0; i < json.length(); i++) {
                Vector<String> puntuacion = new Vector<>();
                JSONObject object = json.getJSONObject(i);
                String id = object.getString("IdUsuario");
                int punt = object.getInt("Puntuacion");
                puntuacion.add(id);
                puntuacion.add(Integer.toString(punt));
                puntuaciones.add(puntuacion);

            }
        } else { // En caso de que no haya datos almacenados en la base de datos
            Vector<String> puntuacion = new Vector<>();
            puntuacion.add("No hay datos");
            puntuaciones.add(puntuacion);
        }

        // Se crean los campos de la tabla
        Vector<String> columnas = new Vector<>();
        columnas.add("IdUsuario");
        columnas.add("Puntuacion");

        // Se crea la tabla
        crearTabla(puntuaciones, columnas);
    }

    /**
     * Muestra una tabla con las mejores partidas
     */
    public void mostrarMejoresPartidas() {
        // Vector en el que se almacenará los datos y campos de la tabla
        Vector<Vector<String>> puntuaciones = new Vector<>();

        // Llamada al gestor que conecta con la base de datos
        JSONArray json = Controlador.getMiControlador().obtenerMejoresPartidas();

        // Guarda los datos del json en Vector<String> y a su vez se va añadinedo al Vector<Vector<String>>

        if (json.length() > 0) { // En caso de que haya datos en la base de datos
            for (int i = 0; i < json.length(); i++) {
                Vector<String> puntuacion = new Vector<>();
                JSONObject object = json.getJSONObject(i);
                String id = object.getString("IdUsuario");
                int punt = object.getInt("Puntuacion");
                String fecha = object.getString("Fecha");
                puntuacion.add(id);
                puntuacion.add(Integer.toString(punt));
                puntuacion.add(fecha);
                puntuaciones.add(puntuacion);
            }

        } else { // En caso de que no haya datos almacenados en la base de datos
            Vector<String> puntuacion = new Vector<>();
            puntuacion.add("No hay datos");
            puntuaciones.add(puntuacion);
        }

        // Se crean los campos de la tabla
        Vector<String> columnas = new Vector<>();
        columnas.add("IdUsuario");
        columnas.add("Puntuacion");
        columnas.add("Fecha");

        // Se crea la tabla
        crearTabla(puntuaciones, columnas);

    }

    /**
     * Muestra una tabla con las mejores medias
     */
    public void mostrarMejorMedia() {
        // Vector en el que se almacenará los datos y campos de la tabla
        Vector<Vector<String>> puntuaciones = new Vector<>();

        // Llamada al gestor que conecta con la base de datos
        JSONArray json = Controlador.getMiControlador().obtenerMejorMedia();

        // Guarda los datos del json en Vector<String> y a su vez se va añadinedo al Vector<Vector<String>>

        if (json.length() > 0) { // En caso de que haya datos en la base de datos
            for (int i = 0; i < json.length(); i++) {
                Vector<String> puntuacion = new Vector<>();
                JSONObject object = json.getJSONObject(i);
                String id = object.getString("IdUsuario");
                String media = object.getString("Media");
                puntuacion.add(id);
                puntuacion.add(media);
                puntuaciones.add(puntuacion);
            }
        } else { // En caso de que no haya datos almacenados en la base de datos
            Vector<String> puntuacion = new Vector<>();
            puntuacion.add("No hay datos");
            puntuaciones.add(puntuacion);
        }

        // Se crean los campos de la tabla
        Vector<String> columnas = new Vector<>();
        columnas.add("IdUsuario");
        columnas.add("Media");

        // Se crea la tabla
        crearTabla(puntuaciones, columnas);
    }

    /**
     * Crea el JTable y su diseño
     */
    private void crearTabla(Vector<Vector<String>> pData, Vector<String> columnas) {
        //Se crea la JTable con los datos correspondientes
        table = new JTable(pData, columnas);
        //Diseño de la tabla
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

    public void accesoJuego(){
        accesoJuego = true;
    }


}

