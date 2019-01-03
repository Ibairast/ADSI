package packVista;

import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packControlador.Controlador;
import packModelo.Partida;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

public class VentanaPersonalizar extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JComboBox combobox;

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
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblInstrucciones = new JLabel("<html><body><b><u>Instrucciones:</u></b><br>\nPara añadir un diseño de mazo nuevo guarda en una carpeta las cartas con el nombre del animal y color azul o verde (\"CanguroAzul\",\"SerpienteVerde\"...) y las cartas especiales \"reverso\",\"patada\",\"puertacielo\" en un total de 27 cartas.\n</html></body>");
        lblInstrucciones.setVerticalAlignment(SwingConstants.TOP);
        lblInstrucciones.setFont(new Font("Dialog", Font.PLAIN, 12));
        lblInstrucciones.setBounds(12, 12, 418, 89);
        contentPane.add(lblInstrucciones);

        JLabel lblNombreMazoNuevo = new JLabel("Nombre mazo nuevo:");
        lblNombreMazoNuevo.setFont(new Font("Dialog", Font.BOLD, 11));
        lblNombreMazoNuevo.setBounds(12, 107, 130, 15);
        contentPane.add(lblNombreMazoNuevo);

        textField = new JTextField();
        textField.setBounds(145, 104, 124, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblPathALa = new JLabel("Path a la carpeta:");
        lblPathALa.setFont(new Font("Dialog", Font.BOLD, 11));
        lblPathALa.setBounds(12, 128, 130, 15);
        contentPane.add(lblPathALa);

        combobox= new JComboBox();
        combobox.setBounds(12, 161, 130, 24);
        contentPane.add(combobox);
        //llenar_combo(); // Metodo aqui? ...Actualizarlo cuando añada/borre personalizacion?

        JButton btnAadirPersonalizacion = new JButton("<html><body>Añadir<br>personalizacion</html></body>");
        btnAadirPersonalizacion.setBounds(305, 100, 125, 42);
        contentPane.add(btnAadirPersonalizacion);

        JButton btnSeleccionarPersonalizacion = new JButton("<html><body>Seleccionar<br>personalizacion</html></body>");
        btnSeleccionarPersonalizacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnSeleccionarPersonalizacion.setBounds(166, 161, 130, 34);
        contentPane.add(btnSeleccionarPersonalizacion);

        JButton btnEliminarPersonalizacion = new JButton("<html><body>Eliminar<br>personalizacion</html></body>");
        btnEliminarPersonalizacion.setBounds(166, 218, 130, 37);
        contentPane.add(btnEliminarPersonalizacion);
    }
    public void llenar_combo() {
        JSONArray json= Controlador.getMiControlador().llenar_combo(); //import org.json.JSONArray org.json.JSONObject
        for (int i=0; i<json.length(); i++){
            combobox.addItem(json.getJSONObject(i));
        }
    }
}