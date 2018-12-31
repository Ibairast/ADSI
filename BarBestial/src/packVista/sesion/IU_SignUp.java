
package packVista.sesion;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packControlador.Controlador;

import javax.swing.*;

public class IU_SignUp {

    public TextField txtCorreo;
    public PasswordField txtPass1;
    public PasswordField txtPass2;
    public CheckBox cbTerminos;
    private Scene secondScene;

    public void setIdentificacionScene(Scene scene) {
        secondScene = scene;
    }


    public void eventRegistrar(MouseEvent mouseEvent) {
        if (cbTerminos.isSelected() && !txtCorreo.getText().equals("")){//Comprobar cb y que el usuario no esté vacío.
            if (comprobarContrasena()){
                if (Controlador.getMiControlador().registrarUsuario(txtCorreo.getText(),txtPass1.getText())){
                    JOptionPane.showConfirmDialog(null,
                            "Usuario Registrado", "Éxito", JOptionPane.DEFAULT_OPTION);
                    eventOpenLogin(mouseEvent);
                }else {
                    JOptionPane.showConfirmDialog(null,
                            "Error en el Registro", "Error", JOptionPane.DEFAULT_OPTION);
                }
            }else {
                JOptionPane.showConfirmDialog(null,
                        "Debes ACEPTAR!!!!", "Error", JOptionPane.DEFAULT_OPTION);
            }
        }else {
            JOptionPane.showConfirmDialog(null,
                    "Error en el Registro", "Error", JOptionPane.DEFAULT_OPTION);
        }
    }

    public void eventOpenLogin(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
    }

    private boolean comprobarContrasena(){
        if (txtPass1.getText().equals(txtPass2.getText())){
            return !txtPass1.getText().equals("");
        }
        return false;
    }
}

