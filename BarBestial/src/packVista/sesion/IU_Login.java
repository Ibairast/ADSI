package packVista.sesion;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import packControlador.Controlador;

import javax.swing.*;

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
        if (!txtPass.getText().equals("")) {
            int resul = Controlador.getMiControlador().identificarCorreo(txtCorreo.getText(), txtPass.getText());
            if (resul == -1) {//noadmin
                JOptionPane.showConfirmDialog(null,
                        "Bienvenido", "Identificación", JOptionPane.DEFAULT_OPTION);
                Controlador.getMiControlador().mostarVentanaInicio();
                Platform.exit();
            } else if (resul == 1) {//admin
                JOptionPane.showConfirmDialog(null,
                        "ADMIN", "Identificación", JOptionPane.DEFAULT_OPTION);
                Controlador.getMiControlador().mostrarVentanaFecha();
                Platform.exit();
            } else {
                JOptionPane.showConfirmDialog(null,
                        "Error en la Identificación", "Error", JOptionPane.DEFAULT_OPTION);
            }
        }
    }
    @FXML
    protected void eventOpenRPassword(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneRPassword);
    }

    public void eventOpenRRSS(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlIdentificacionRRSS = new FXMLLoader(getClass().getResource("LoginRRSS.fxml"));
            Parent panelIdentificacionRRSS = fxmlIdentificacionRRSS.load();
            Scene sceneIdentificacionRRSS = new Scene(panelIdentificacionRRSS);
            Stage stage = new Stage();
            stage.setTitle("Identificación Google");
            stage.setScene(sceneIdentificacionRRSS);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

