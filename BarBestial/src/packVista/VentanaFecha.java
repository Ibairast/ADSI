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

    //Creacion de los elementos de la ventana
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
                //En este if comparamos la fecha introducida en el TextField por el usuario
                //y miramos que tenga esta estructura (aaaa-mm-dd)
                if (txtfecha.getText().matches(fechaPattern)) {
                    //Si la fecha tiene la estructura indicada llamamos al metodo del controlador pasandole la fecha
                    JSONArray json = Controlador.getMiControlador().obtenerUsuarios(txtfecha.getText());
                    //Ponemos la ventana a false para que no se vea y la cerramos
                    setVisible(false);
                    dispose();
                    //Limpiamos el valor del TextField para que cuando volvamos ha abrirla este limpio
                    txtfecha.setText("");
                    //Creamos la ventana de usuario
                    ventanaUsuario = new VentanaUsuario();
                    ventanaUsuario.setVisible(true);
                    //Le pasamos el json que hemos recogido anteriormente para pasarselo
                    ventanaUsuario.cargarUsuarios(json);
                } else {
                    //Si la fecha esta mal introducida saldra un mensaje de error
                    JOptionPane.showConfirmDialog(null,
                            "Error,el formato de la fecha no es correcto", "Fecha", JOptionPane.DEFAULT_OPTION);
                   //Limpiamos el TextField y hacemos focus en el.
                    txtfecha.setText("");
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