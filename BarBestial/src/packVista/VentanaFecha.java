package packVista;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class VentanaFecha extends JFrame {
    private static final long serialVersionUID = 1L;



    JLabel lblfecha= new JLabel("Introduce una fecha: ");

    JTextField txtfecha= new JTextField();

    JButton btnenviar = new JButton("Ver Jugadores");

    public VentanaFecha() {
        setSize(600, 285);
        setTitle("Bar Bestial - Ayuda");
        setLocationRelativeTo(null);
        setResizable(false);

        btnenviar.setBounds(350,110,120,25);
        add(btnenviar);

        txtfecha.setBounds(120,110,200,25);
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