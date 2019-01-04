package packVista.sesion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import packControlador.Controlador;

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
        if (Controlador.getMiControlador().recuperarContrasena(txtCorreo.getText())) {
            mostrarAlerta(Alert.AlertType.CONFIRMATION, sceneIdentificacion.getWindow(), "Éxito", "Su contraseña ha sido enviada");
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, sceneIdentificacion.getWindow(), "Error", "Error en el envío de contraseña");
        }
    }

    private void mostrarAlerta(Alert.AlertType alertType, Window owner, String title, String message) {
        Sesion.mensaje(alertType, owner, title, message);
    }


}
