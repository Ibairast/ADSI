package packVista.sesion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    /**
     * Evento ejecutado al presionar el botón "Registrar".
     * <p>
     * Se comprueba si el checkbox, identificado como "cbTerminos", está seleccionado.
     * Si es así, se llama al método "registrarUsuario" del Singleton "Controlador", comprobando si esté nos devuelve TRUE ó FALSE.
     * En caso contrario se muestra un pop up de error.
     * Si "registrarUsuario" es TRUE se muestra un pop up de éxito, de no ser así se muestra un pop up de error.
     */
    public void eventRegistrar(MouseEvent mouseEvent) {
        if (cbTerminos.isSelected()) {
            if (Controlador.getMiControlador().registrarUsuario(txtCorreo.getText(), txtPass1.getText(), txtPass2.getText())) {
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

