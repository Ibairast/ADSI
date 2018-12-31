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



    JLabel lblfecha= new JLabel("Introduce una fecha(aaaa-mm-dd): ");

    JTextField txtfecha= new JTextField();

    JButton btnenviar = new JButton("Ver Jugadores");

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
    public void obtenerJugadores(){

     String fecha = txtfecha.getText();
     String fechaPattern = "\\d{4}-\\d{1,2}-\\d{1,2}";
     Vector<Vector<String>> usuarios = new Vector<>();
        if(fecha.matches(fechaPattern)){
            JSONArray json = Controlador.getMiControlador().obtenerUsuarios(fecha);
            for (int i = 0; i < json.length(); i++) {
                 System.out.println( json.get(i));
                    JSONObject objeto= json.getJSONObject(i);
                    String id = objeto.getString("IdUsuario");
                System.out.println(id);
                }

        }else{
            System.out.println("La pantallita de error");
        }





    }
}