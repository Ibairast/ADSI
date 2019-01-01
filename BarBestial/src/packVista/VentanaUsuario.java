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

        private JButton btneliminar =new JButton("Eliminar");;
        private JCheckBox check = new JCheckBox();
        private JPanel panel=new JPanel(new GridLayout(0, 1));;

    public VentanaUsuario() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane =getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(btneliminar, BorderLayout.SOUTH);
        setSize(300, 200);
        setTitle("Hiola");
        setLocationRelativeTo(null);


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

    public void cargarUsuarios(JSONArray json){
        for(int i=0;i<json.length();i++){
            System.out.println(json.get(i));
            JSONObject objeto = json.getJSONObject(i);
            String id = objeto.getString("IdUsuario");
            check = new JCheckBox(id);
            System.out.println(id);
            panel.add(check);
        }
    }

      public void addEliminar(ActionListener listenForBtnEliminar) {
        btneliminar.addActionListener(listenForBtnEliminar);
    }
    public boolean algunoPulsado(){
      boolean pulsado=false;
      int i=0;
        while(i<panel.getComponentCount() && !pulsado){
            JCheckBox check = (JCheckBox) panel.getComponent(i);
            if(check.isSelected()){
                pulsado=true;
            }
            i++;
        }
        return pulsado;
    }
    public JSONArray eliminarUsuarios(){
        JSONArray json = new JSONArray();

        for(int i=0;i<panel.getComponentCount();i++){
            JCheckBox check = (JCheckBox) panel.getComponent(i);
            if(check.isSelected()){
                JSONObject js = new JSONObject();
                js.put("IdUsuario",  check.getText());
                json.put(js);
            }
        }
        return json;
    }

    }
