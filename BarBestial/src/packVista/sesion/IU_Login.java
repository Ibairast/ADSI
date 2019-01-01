package packVista.sesion;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packControlador.Controlador;

import javax.swing.*;

public class IU_Login {

    public TextField txtCorreo;
    public PasswordField txtPass;
    private Scene firstScene;

    public void setRegistroScene(Scene scene) {
        firstScene = scene;
    }


    public void eventOpenRegistro(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(firstScene);
    }

    public void eventIdentificar(MouseEvent mouseEvent) {
        if (!txtCorreo.getText().equals("") && !txtPass.getText().equals("")) {
            int resul = Controlador.getMiControlador().comprobarUsuario(txtCorreo.getText(), txtPass.getText());
            if (resul == -1) {//noadmin
                JOptionPane.showConfirmDialog(null,
                        "Bienvenido", "Login", JOptionPane.DEFAULT_OPTION);
            } else if (resul == 1) {//admin
                JOptionPane.showConfirmDialog(null,
                        "ADMIN", "Login", JOptionPane.DEFAULT_OPTION);
            } else {
                JOptionPane.showConfirmDialog(null,
                        "Error en la Identificación", "Error", JOptionPane.DEFAULT_OPTION);
            }
        }

    }


}

