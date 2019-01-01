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



    private JLabel lblfecha= new JLabel("Introduce una fecha(aaaa-mm-dd): ");

    private JTextField txtfecha= new JTextField();

    private JButton btnenviar = new JButton("Ver Jugadores");

    public VentanaFecha() {
        setSize(600, 285);
        setTitle("Bar Bestial - Ayuda");
        setLocationRelativeTo(null);
        setResizable(false);

        btnenviar.setBounds(420,110,120,25);
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
    public void addobtenerJugadores(ActionListener listenForBtnEnviar){
    btnenviar.addActionListener(listenForBtnEnviar);
    }
    public JSONArray obtenerJugadores() {
        String fecha = txtfecha.getText();
        JSONArray json = Controlador.getMiControlador().obtenerUsuarios(fecha);
        return json;


    }
    public boolean comprobarFecha(){
        String fecha = txtfecha.getText();
        String fechaPattern = "\\d{4}-\\d{1,2}-\\d{1,2}";

        if (fecha.matches(fechaPattern)) {
            return true;
        }else{
            return false;
        }
    }

}