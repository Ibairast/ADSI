package packVista.sesion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packControlador.Controlador;

import javax.swing.*;

public class IU_Login {

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtPass;

    private Scene sceneRegistro;

    private Scene sceneRPassword;

    private Scene scene;

    protected void setSceneRegistro(Scene sceneRegistro) {
        this.sceneRegistro = sceneRegistro;
    }

    protected void setSceneRPassword(Scene sceneLogin) {
        this.sceneRPassword = sceneLogin;
    }


    protected void setScene(Scene sceneIdentificacion) {
        this.scene = sceneIdentificacion;
    }

    @FXML
    protected void eventOpenRegistro(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneRegistro);
    }


    public void eventIdentificar(MouseEvent mouseEvent) {
        if (!txtCorreo.getText().equals("") && !txtPass.getText().equals("")) {
            int resul = Controlador.getMiControlador().comprobarUsuario(txtCorreo.getText(), txtPass.getText());
            if (resul == -1) {//noadmin
                JOptionPane.showConfirmDialog(null,
                        "Bienvenido", "Login", JOptionPane.DEFAULT_OPTION);
                Controlador.getMiControlador().mostarVentanaInicio();
            } else if (resul == 1) {//admin
                JOptionPane.showConfirmDialog(null,
                        "ADMIN", "Login", JOptionPane.DEFAULT_OPTION);
                Controlador.getMiControlador().mostrarVentanaFecha();
            } else {
                JOptionPane.showConfirmDialog(null,
                        "Error en la Identificación", "Error", JOptionPane.DEFAULT_OPTION);
            }
            Stage stage = (Stage) this.scene.getWindow();
            stage.close();
        }
    }

    @FXML
    protected void eventOpenRPassword(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneRPassword);
    }


}

