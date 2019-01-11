package packVista;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import packControlador.Controlador;
import packModelo.Partida;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.util.Vector;

public class VentanaPersonalizar extends JFrame {

    private JPanel contentPane;
    private JTextField nombremazo;
    private JTextField path;
    private JComboBox<String> combobox;
    private JButton btnSeleccionarPersonalizacion;
    private JButton btnEliminarPersonalizacion;
    private JButton btnAnadirPersonalizacion;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPersonalizar frame = new VentanaPersonalizar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VentanaPersonalizar() {
        setTitle("Personalizar");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setResizable(false);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        this.contentPane.setLayout(null);

        JLabel lblInstrucciones = new JLabel("<html><body><b><u>Instrucciones:</u></b><br>\nPara añadir un diseño de mazo nuevo guarda en una carpeta las cartas con el nombre del animal y color azul o verde (\"CanguroAzul\",\"SerpienteVerde\"...) y las cartas especiales \"reverso\",\"patada\",\"puertacielo\" en un total de 27 cartas.\n</html></body>");
        lblInstrucciones.setVerticalAlignment(SwingConstants.TOP);
        lblInstrucciones.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblInstrucciones.setBounds(12, 12, 418, 89);
        contentPane.add(lblInstrucciones);

        JLabel lblNombreMazoNuevo = new JLabel("Nombre mazo nuevo:");
        lblNombreMazoNuevo.setFont(new Font("Dialog", Font.BOLD, 11));
        lblNombreMazoNuevo.setBounds(12, 107, 130, 15);
        contentPane.add(lblNombreMazoNuevo);

        this.nombremazo = new JTextField();
        this.nombremazo.setBounds(145, 104, 148, 19);
        contentPane.add(nombremazo);
        this.nombremazo.setColumns(10);

        JLabel lblPathALa = new JLabel("Path a la carpeta:");
        lblPathALa.setFont(new Font("Dialog", Font.BOLD, 11));
        lblPathALa.setBounds(12, 128, 130, 15);
        contentPane.add(lblPathALa);

        this.path = new JTextField();
        this.path.setBounds(125, 125, 168, 19);
        contentPane.add(path);
        this.path.setColumns(10);

        Vector<String> mazos = this.llenar_combo();
        this.combobox= new JComboBox<>(mazos);
        //Vector v = new Vector();
        //v.add("mazo1");
        //v.add("mazoPrueba2");
        //v.add("defecto");
        //this.combobox= new JComboBox<>(v);
        this.combobox.setBounds(12, 161, 130, 24);
        contentPane.add(combobox);

        this.btnAnadirPersonalizacion = new JButton("<html><body>Añadir<br>personalizacion</html></body>");
        this.btnAnadirPersonalizacion.setBounds(305, 100, 125, 42);
        this.btnAnadirPersonalizacion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                anadirPersonalizacion();
            }
        });
        contentPane.add(btnAnadirPersonalizacion);

        this.btnSeleccionarPersonalizacion = new JButton("<html><body>Seleccionar<br>personalizacion</html></body>");
        this.btnSeleccionarPersonalizacion.setBounds(166, 161, 130, 34);
        this.btnSeleccionarPersonalizacion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                seleccionarPersonalizacion();
            }
        });
        contentPane.add(btnSeleccionarPersonalizacion);

        this.btnEliminarPersonalizacion = new JButton("<html><body>Eliminar<br>personalizacion</html></body>");
        this.btnEliminarPersonalizacion.setBounds(166, 218, 130, 37);
        this.btnEliminarPersonalizacion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                eliminarPersonalizacion();
            }
        });
        contentPane.add(btnEliminarPersonalizacion);

    }
    public Vector<String> llenar_combo() {
        Vector<String> mazos= Controlador.getMiControlador().llenar_combo();
        return mazos;
    }

    public void seleccionarPersonalizacion(){
        Object eleccion = this.combobox.getSelectedItem();
        String mazo = String.valueOf(eleccion);
        Controlador.getMiControlador().seleccionarPersonalizacion(mazo);
    }

    public void eliminarPersonalizacion(){
        Object eleccion = this.combobox.getSelectedItem();
        String mazo = String.valueOf(eleccion);
        Controlador.getMiControlador().eliminarPersonalizacion(mazo);
    } //Cerrar y abrir ventana para refrescar

    public void anadirPersonalizacion(){//Cerrar y abrir ventana para refrescar
        String nombre = this.nombremazo.getText();
        String path=this.path.getText();
        Controlador.getMiControlador().anadirPersonalizacion(nombre, path);
    }

}



//TODO : REFRESCAR JCOMBOBOX
// TODO : AÑADIR PERSONALIZACION: ERROR EN UPDATE SET ....