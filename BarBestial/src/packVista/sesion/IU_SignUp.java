package packVista.sesion;

import javafx.fxml.FXML;
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
    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtPass1;

    @FXML
    private PasswordField txtPass2;

    @FXML
    private CheckBox cbTerminos;
    private Scene sceneIdentificacion;


    protected void setSceneIdentificacion(Scene sceneIdentificacion) {
        this.sceneIdentificacion = sceneIdentificacion;
    }

    public void eventRegistrar(MouseEvent mouseEvent) {
        if (cbTerminos.isSelected() && !txtCorreo.getText().equals("")) {//Comprobar cb y que el usuario no esté vacío.
            if (comprobarContrasena()) {
                if (Controlador.getMiControlador().registrarUsuario(txtCorreo.getText(), txtPass1.getText())) {
                    JOptionPane.showConfirmDialog(null,
                            "Usuario Registrado", "Éxito", JOptionPane.DEFAULT_OPTION);
                    eventOpenLogin(mouseEvent);
                } else {
                    JOptionPane.showConfirmDialog(null,
                            "Error en el Registro", "Error", JOptionPane.DEFAULT_OPTION);
                }
            } else {
                JOptionPane.showConfirmDialog(null,
                        "Debes ACEPTAR!!!!", "Error", JOptionPane.DEFAULT_OPTION);
            }
        } else {
            JOptionPane.showConfirmDialog(null,
                    "Error en el Registro", "Error", JOptionPane.DEFAULT_OPTION);
        }
    }

    @FXML
    protected void eventOpenLogin(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneIdentificacion);
    }

    private boolean comprobarContrasena() {
        if (txtPass1.getText().equals(txtPass2.getText())) {
            return !txtPass1.getText().equals("");
        }
        return false;
    }

}

