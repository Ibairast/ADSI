package packVista.sesion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packControlador.Controlador;

import javax.swing.*;

public class IU_SignUp {
    public Button btnRegistro;
    @FXML
    private TextField txtUsuario;
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
        if (cbTerminos.isSelected()) {//Comprobar cb y que el usuario no esté vacío.
            if (Controlador.getMiControlador().registrarUsuario(txtCorreo.getText(), txtPass1.getText(),txtPass2.getText())) {
                JOptionPane.showConfirmDialog(null,
                        "Usuario registrado", "Éxito", JOptionPane.DEFAULT_OPTION);
                eventOpenLogin(mouseEvent);
            } else {
                JOptionPane.showConfirmDialog(null,
                        "No se ha podido registrar", "Error", JOptionPane.DEFAULT_OPTION);
            }
        } else {
            JOptionPane.showConfirmDialog(null,
                    "Fallo en la introducción de datos", "Error", JOptionPane.DEFAULT_OPTION);
        }
    }


    @FXML
    protected void eventOpenLogin(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneIdentificacion);
    }


}

