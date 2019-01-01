package packVista;

import org.json.JSONArray;
import org.json.JSONObject;
import packControlador.Controlador;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Vector;

public class VentanaUsuario extends JFrame {
    private static final long serialVersionUID = 1L;

        private JButton btnenviar =new JButton("Enviar");;
        private JCheckBox check = new JCheckBox();

    public VentanaUsuario() {
        JFrame frame = new JFrame("Bar Bestial - Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(0, 1));

      /*  check = new JCheckBox("Garlic");
        panel.add(check);
        check = new JCheckBox("Onions");
        panel.add(check);
        check = new JCheckBox("Pepperoni");
        panel.add(check);
        check = new JCheckBox("Spinach");
        panel.add(check);
        Container contentPane = frame.getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(btnenviar, BorderLayout.SOUTH);
        frame.setSize(300, 200);
        frame.setVisible(true);
        setLocationRelativeTo(null);*/


    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaUsuario frame = new VentanaUsuario();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

  //  public void addobtenerJugadores(ActionListener listenForBtnEnviar) {
    //    btnenviar.addActionListener(listenForBtnEnviar);
    //}

    public void cargarUsuarios(JSONArray json){
        for(int i=0;i<json.length();i++){
            System.out.println(json.get(i));
            JSONObject objeto = json.getJSONObject(i);
            String id = objeto.getString("IdUsuario");
            check = new JCheckBox(id);
            System.out.println(id);
        }
    }


    }
