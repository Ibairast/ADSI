package packVista.sesion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packControlador.Controlador;

import javax.swing.*;

public class IU_RPass {

    @FXML
    private TextField txtCorreo;
    private Scene sceneIdentificacion;


    public void eventOpenLogin(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneIdentificacion);
    }

    protected void setSceneIdentificacion(Scene sceneIdentificacion) {
        this.sceneIdentificacion = sceneIdentificacion;
    }

    public void eventEnviarContrasena(MouseEvent mouseEvent) {
       if (Controlador.getMiControlador().recuperarContrasena(txtCorreo.getText())){
           JOptionPane.showConfirmDialog(null,
                   "Su contraseña ha sido enviada", "Éxito", JOptionPane.DEFAULT_OPTION);
       }else {
           JOptionPane.showConfirmDialog(null,
                   "Error en el envío de contraseña", "Error", JOptionPane.DEFAULT_OPTION);
       }
    }


}
