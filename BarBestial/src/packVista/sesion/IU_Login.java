package packVista.sesion;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import packControlador.Controlador;

public class IU_Login {

    public Button btnLogin;
    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtPass;

    private Scene sceneRegistro;

    private Scene sceneRPassword;

    protected void setSceneRegistro(Scene sceneRegistro) {
        this.sceneRegistro = sceneRegistro;
    }

    protected void setSceneRPassword(Scene sceneLogin) {
        this.sceneRPassword = sceneLogin;
    }


    @FXML
    protected void eventOpenRegistro(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneRegistro);
    }


    public void eventIdentificar(MouseEvent mouseEvent) {
        if (Sesion.validarFormatoEmail(txtCorreo.getText())) {
            if (!txtPass.getText().equals("")) {
                int resul = Controlador.getMiControlador().comprobarUsuario(txtCorreo.getText(), txtPass.getText());
                if (resul == -1) {//noadmin
                    mostrarAlerta(Alert.AlertType.CONFIRMATION, btnLogin.getScene().getWindow(), "Identificación", "Bienvenido");
                    Controlador.getMiControlador().mostarVentanaInicio();
                    Platform.exit();
                } else if (resul == 1) {//admin
                    mostrarAlerta(Alert.AlertType.CONFIRMATION, btnLogin.getScene().getWindow(), "Identificación", "ADMIN");
                    Controlador.getMiControlador().mostrarVentanaFecha();
                    Platform.exit();
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, btnLogin.getScene().getWindow(), "Error", "Error en la Identificación");
                }
            }

        } else {
            mostrarAlerta(Alert.AlertType.ERROR, sceneRegistro.getWindow(), "Error", "Error en la Identificación");
        }


    }

    @FXML
    protected void eventOpenRPassword(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneRPassword);
    }

    private void mostrarAlerta(Alert.AlertType alertType, Window owner, String title, String message) {
        Sesion.mensaje(alertType, owner, title, message);
    }


    public void eventOpenRRSS(MouseEvent mouseEvent) {

        try {
            FXMLLoader fxmlIdentificacionRRSS = new FXMLLoader(getClass().getResource("IdentificacionRRSS.fxml"));
            Parent panelIdentificacionRRSS = fxmlIdentificacionRRSS.load();
            Scene sceneIdentificacionRRSS = new Scene(panelIdentificacionRRSS);
            Stage stage = new Stage();
            stage.setTitle("dfd");
            stage.setScene(sceneIdentificacionRRSS);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

