package packVista;

import org.json.JSONArray;
import org.json.JSONObject;
import packControlador.Controlador;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

public class VentanaFecha extends JFrame {
    private static final long serialVersionUID = 1L;
    private VentanaUsuario ventanaUsuario;


    private JLabel lblfecha= new JLabel("Introduce una fecha(aaaa-mm-dd): ");

    private JTextField txtfecha= new JTextField();

    private JButton btnenviar = new JButton("Ver Jugadores");

    public VentanaFecha() {
        setSize(600, 285);
        setTitle("Bar Bestial - Ayuda");
        setLocationRelativeTo(null);
        setResizable(false);

        btnenviar.setBounds(420,110,120,25);
        btnenviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String fechaPattern = "\\d{4}-\\d{1,2}-\\d{1,2}";
                if (txtfecha.getText().matches(fechaPattern)) {
                    JSONArray json = Controlador.getMiControlador().obtenerUsuarios(txtfecha.getText());
                    setVisible(false);
                    dispose();
                    txtfecha.setText("");
                    ventanaUsuario = new VentanaUsuario();
                    ventanaUsuario.setVisible(true);
                    ventanaUsuario.cargarUsuarios(json);
                } else {
                    JOptionPane.showConfirmDialog(null,
                            "Error,el formato de la fecha no es correcto", "Fecha", JOptionPane.DEFAULT_OPTION);

                    txtfecha.requestFocus();
                }
            }
        });
        add(btnenviar);

        txtfecha.setBounds(200,110,200,25);
        add(txtfecha);

        lblfecha.setBounds(20,35,70,25);
        add(lblfecha);

    }

/**
 * Launch the application.
 */
public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
        try {
            VentanaFecha frame = new VentanaFecha();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    });

}

}