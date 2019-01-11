package packVista.sesion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packControlador.Controlador;

import javax.swing.*;

public class IU_RPass {

    public Button btnRPass;
    @FXML
    private TextField txtCorreo;
    private Scene sceneIdentificacion;


    /**
     * @param sceneIdentificacion Escena perteneciente a la interfaz "IU_Login".
     */
    protected void setSceneIdentificacion(Scene sceneIdentificacion) {
        this.sceneIdentificacion = sceneIdentificacion;
    }

    /**
     * @param mouseEvent Evento ejecutado al pulsar sobre el botón identificado como "btnRPass"
     * @precondicion Ninguna.
     * @postcondicion Dependiendo si se ha podido enviar la contraseña o no, mostrará un pop up de Error o Éxito.
     * <p>Funcionamiento</p>
     * Ejecuta el método "recuperarContrasena" del Singleton "Controlador" y dependiendo que el resultado de éste
     * mostrará un determinado pop up.
     */
    public void eventEnviarContrasena(MouseEvent mouseEvent) {
        if (Controlador.getMiControlador().recuperarContrasena(txtCorreo.getText())) {
            JOptionPane.showConfirmDialog(null,
                    "Su contraseña ha sido enviada", "Éxito", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showConfirmDialog(null,
                    "Error en el envío de contraseña", "Error", JOptionPane.DEFAULT_OPTION);
        }
    }

    /**
     * @param mouseEvent Evento ejecutado al pulsar sobre el texto "Volver"
     * @precondicion Ninguna.
     * @postcondicion Cambiar de escena a la interfaz "IU_Login".
     * <p>Funcionamiento</p>
     * Obtiene la escena actual mediante el MouseEvent y se actualiza por la escena perteneciente a la
     * la interfaz "IU_Login".
     */
    public void eventOpenLogin(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneIdentificacion);
    }


}
