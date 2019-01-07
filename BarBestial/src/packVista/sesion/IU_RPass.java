package packVista.sesion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import packControlador.Controlador;

public class IU_RPass {

    public Button btnRPass;
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
        if (Sesion.validarFormatoEmail(txtCorreo.getText())) {
            if (Controlador.getMiControlador().recuperarContrasena(txtCorreo.getText())) {
                mostrarAlerta(Alert.AlertType.CONFIRMATION, btnRPass.getScene().getWindow(), "Éxito", "Su contraseña ha sido enviada");
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, btnRPass.getScene().getWindow(), "Error", "Error en el envío de contraseña");
            }
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, btnRPass.getScene().getWindow(), "Error", "Error en el envío de contraseña");
        }


    }

    private void mostrarAlerta(Alert.AlertType alertType, Window owner, String title, String message) {
        Sesion.mensaje(alertType, owner, title, message);
    }


}
