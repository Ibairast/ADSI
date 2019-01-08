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
         //Creacion de los elementos de la ventana
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
                    //Si algun chechBox ha sido seleccionado
                    int cont= contadorPulsado();
                    JSONArray json = eliminarUsuarios();
                    limpiarVentana();
                    //Llamamos al metodo del controlador pasandole el json obtenido antes
                    Controlador.getMiControlador().eliminarUsuarios(json);
                    if(cont>1){
                        //Si hemos seleccionado mas de un usuario
                        JOptionPane.showConfirmDialog(null,
                                "Usuarios eliminado", "Usuario", JOptionPane.DEFAULT_OPTION);
                    }else{
                        //Si solo hemos seleccionado un usuario
                        JOptionPane.showConfirmDialog(null,
                                "Usuario eliminado", "Usuario", JOptionPane.DEFAULT_OPTION);
                    }
                    //Ponemos el visible a false para que no se vea la ventana y la cerramos
                    setVisible(false);
                    dispose();
                    //Volvemos ala ventana fecha
                    ventanaFecha= new VentanaFecha();
                    ventanaFecha.setVisible(true);
                } else {
                    //Si ningun chechBox ha sido seleccionado
                    JOptionPane.showConfirmDialog(null,
                            "Error,debes seleccionar al menos un usuario", "Usuario", JOptionPane.DEFAULT_OPTION);
                }
            }
        });
        contentPane.add(btneliminar, BorderLayout.PAGE_END);
        btnvolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiarVentana();
                //Ponemos el visible a false para que no se vea la ventana y la cerramos
                setVisible(false);
                dispose();
                //Volvemos ala ventana fecha
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
    //Recibimos un json con todos los usuarios que no se han logeado desde una fecha
    //y los cargamos en la pantalla como checkBox
    public void cargarUsuarios(JSONArray json){
        for(int i=0;i<json.length();i++){
            JSONObject objeto = json.getJSONObject(i);
            String id = objeto.getString("IdUsuario");
            check = new JCheckBox(id);
            System.out.println(id);
            panel.add(check);
        }

    }

    //Miramos si algun checkBox ha sido seleccionado
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
    //Recorremos todos los chechBox que tenemos y vamos mirando si estan seleccionados.
    //Si lo esta cogemos su texto y lo añadimos al json
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
    //Limpiamos la ventana, para que cuando se vuelva a abrir este vacia
    public void limpiarVentana(){
       int i= panel.getComponentCount();
       int j=0;
       while(j<i){
          panel.remove(panel.getComponent(0));
          j++;
      }
    }
    //Miramos haber cuantos checkBox han sido seleccionados
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
