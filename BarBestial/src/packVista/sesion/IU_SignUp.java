package packVista.sesion;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import packControlador.Controlador;

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
        if (Sesion.isValid(txtCorreo.getText())) {
            if (cbTerminos.isSelected()) {//Comprobar cb y que el usuario no esté vacío.

                if (comprobarContrasena()) {
                    if (Controlador.getMiControlador().registrarUsuario(txtCorreo.getText(), txtPass1.getText())) {
                        mostrarAlerta(Alert.AlertType.CONFIRMATION, btnRegistro.getScene().getWindow(), "Éxito", "Usuario registrado");
                        eventOpenLogin(mouseEvent);
                    } else {
                        mostrarAlerta(Alert.AlertType.ERROR, btnRegistro.getScene().getWindow(), "Error", "No se ha podido registrar");
                    }
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, btnRegistro.getScene().getWindow(), "Error", "Fallo en la introducción de datos");
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, btnRegistro.getScene().getWindow(), "Error", "Fallo en la introducción de datos");

            }
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, btnRegistro.getScene().getWindow(), "Error", "Fallo en la introducción de datos");
        }

    }

    private void mostrarAlerta(Alert.AlertType alertType, Window owner, String title, String message) {
        Sesion.mensaje(alertType, owner, title, message);
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

