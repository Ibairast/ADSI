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

    /**
     * @param sceneRegistro Escena perteneciente a la interfaz "IU_SignUp".
     */
    protected void setSceneRegistro(Scene sceneRegistro) {
        this.sceneRegistro = sceneRegistro;
    }

    /**
     * @param sceneLoginRRSS Escena perteneciente a la interfaz "IU_LoginRRSS".
     */
    protected void setSceneRPassword(Scene sceneLoginRRSS) {
        this.sceneRPassword = sceneLoginRRSS;
    }

    /**
     * @param mouseEvent Evento generado al pulsar sobre el botón identificado como "btnLogin".
     * @precondicion Ninguna.
     * @postcondicion Identifica al usuario, mostrando un pop up indicando si es Administrador o dando la Bienvenida si
     * no lo es. En caso que no se pueda identificar, se le mostrará un pop up de Error.
     * <p>Funcionamiento</p>
     * Ejecuta  el método "identificarUsuario" del Singleton "Controlador" y dependiendo del resultado de éste
     * mostraremos un determinado pop up.
     */
    public void eventIdentificar(MouseEvent mouseEvent) {
        int resul = Controlador.getMiControlador().identificarUsuario(txtCorreo.getText(), txtPass.getText());
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

    /**
     * @param mouseEvent Evento generado al pulsar sobre el texto "Recuperar Contraseña".
     * @precondicion Ninguna.
     * @postcondicion Cambiar de escena a la interfaz "IU_RPass".
     * <p>Funcionamiento</p>
     * Obtiene la escena actual mediante el MouseEvent y se actualiza por la escena perteneciente a la
     * la interfaz "IU_RPass".
     */
    @FXML
    protected void eventOpenRPassword(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneRPassword);
    }

    /**
     * @param mouseEvent Evento generado al pulsar sobre el texto "Registrase".
     * @precondicion Ninguna.
     * @postcondicion Cambiar de escena a la interfaz "IU_SignUp".
     * <p>Funcionamiento</p>
     * Obtiene la escena actual mediante el MouseEvent y se actualiza por la escena perteneciente a la
     * la interfaz "IU_SignUp".
     */
    @FXML
    protected void eventOpenRegistro(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneRegistro);
    }

    /**
     * @param mouseEvent Evento generado al pulsar sobre el texto "Google".
     * @precondicion Ninguna.
     * @postcondicion Cambiar de escena a la interfaz "IU_LoginRRSS".
     * <p>Funcionamiento</p>
     * Carga el archivo "LoginRRSS.fxml", crea una escena a partir de éste y muestra la interfaz.
     */
    public void eventOpenRRSS(MouseEvent mouseEvent) {
        if (Controlador.getMiControlador().comprobarInternet()){
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
        }else {
            JOptionPane.showConfirmDialog(null,
                    "No tienes acceso a Internet", "Error", JOptionPane.DEFAULT_OPTION);
        }

    }
}

