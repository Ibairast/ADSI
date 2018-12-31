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
    public void addobtenerJugadores(ActionListener listenForBtnEnviar){
    btnenviar.addActionListener(listenForBtnEnviar);
    }
    public void obtenerJugadores(){
     System.out.println("Hola");
     String fecha = txtfecha.getText();

        Vector<Vector<String>> usuarios = new Vector<>();

        JSONArray json = Controlador.getMiControlador().obtenerUsuarios(fecha);

        for (int i = 0; i < json.length(); i++) {
            Vector<String> usuario = new Vector<>();
            JSONObject object = json.getJSONObject(i);
            String clave = object.getString("IdUsuario");
            //System.out.println("Puntuacion: " + clave);
            usuario.add(clave);
            //System.out.println("Size: " + puntuacion.size());
            usuarios.add(usuario);

        }

        //System.out.println(puntuaciones.toString());

        Vector<String> columnas = new Vector<>();
        //System.out.println(columnas.toString());
        columnas.add("IdUsuario");


    }
}