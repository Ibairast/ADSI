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
    private VentanaFecha ventanaFecha;

        private JButton btneliminar =new JButton("Eliminar");;
        private JButton btnvolver =new JButton("Volver");;
        private JCheckBox check = new JCheckBox();
        private JPanel panel=new JPanel(new GridLayout(0, 1));

    public VentanaUsuario() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane =getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        btneliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (algunoPulsado()) {
                    int cont= contadorPulsado();
                    JSONArray json = eliminarUsuarios();
                    limpiarVentana();
                    Controlador.getMiControlador().eliminarUsuarios(json);
                    if(cont>1){
                        JOptionPane.showConfirmDialog(null,
                                "Usuarios eliminado", "Usuario", JOptionPane.DEFAULT_OPTION);
                    }else{
                        JOptionPane.showConfirmDialog(null,
                                "Usuario eliminado", "Usuario", JOptionPane.DEFAULT_OPTION);
                    }

                    setVisible(false);
                    dispose();
                    ventanaFecha= new VentanaFecha();
                    ventanaFecha.setVisible(true);
                } else {
                    JOptionPane.showConfirmDialog(null,
                            "Error,debes seleccionar al menos un usuario", "Usuario", JOptionPane.DEFAULT_OPTION);
                }
            }
        });
        contentPane.add(btneliminar, BorderLayout.PAGE_END);
        btnvolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiarVentana();
                setVisible(false);
                dispose();
                ventanaFecha= new VentanaFecha();
                ventanaFecha.setVisible(true);
            }
        });
        contentPane.add(btnvolver, BorderLayout.NORTH);

        setSize(300, 200);
        setTitle("Bar Bestial - Usuarios");
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
                js.put("IdUsuario",check.getText());
                json.put(js);
            }
        }
        return json;
    }

    public void limpiarVentana(){
       int i= panel.getComponentCount();
       int j=0;
       while(j<i){
          panel.remove(panel.getComponent(0));
          j++;
      }
    }
    public int contadorPulsado(){
        int cont=0;
        int i=0;
        while(i<panel.getComponentCount()){
            JCheckBox check = (JCheckBox) panel.getComponent(i);
            if(check.isSelected()){
                cont++;
            }
            i++;
        }
        return cont;
    }
    }
