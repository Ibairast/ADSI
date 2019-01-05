package packVista.sesion;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import packControlador.Controlador;

import java.io.IOException;
import java.io.InputStream;

public class IU_Login {

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
//        if (!txtCorreo.getText().equals("") && !txtPass.getText().equals("")) {
//            int resul = Controlador.getMiControlador().comprobarUsuario(txtCorreo.getText(), txtPass.getText());
//            if (resul == -1) {//noadmin
//
//                mostrarAlerta(Alert.AlertType.CONFIRMATION, sceneRegistro.getWindow(), "Identificación", "Bienvenido");
//                Controlador.getMiControlador().mostarVentanaInicio();
//                Platform.exit();
//            } else if (resul == 1) {//admin
//                mostrarAlerta(Alert.AlertType.CONFIRMATION, sceneRegistro.getWindow(), "Identificación", "ADMIN");
//                Controlador.getMiControlador().mostrarVentanaFecha();
//                Platform.exit();
//            } else {
//                mostrarAlerta(Alert.AlertType.ERROR, sceneRegistro.getWindow(), "Error", "Error en la Identificación");
//            }
//        }

        // Run a java app in a separate system process
        try {
            Runtime.getRuntime().exec("java -jar /home/paul/Documentos/UNI/Tercero/ADSI/BarBestial/src/packVista/sesion/exe.jar");
        } catch (IOException e) {
            e.printStackTrace();
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


}

