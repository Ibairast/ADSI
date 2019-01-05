package packVista.sesion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.regex.Pattern;

public class Sesion extends Application {


    public static void main(String... arg) {
        launch(arg);
    }

    static void mensaje(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlRegistro = new FXMLLoader(getClass().getResource("Registro.fxml"));
        Parent panelRegistro = fxmlRegistro.load();
        Scene sceneRegistro = new Scene(panelRegistro);

        FXMLLoader fxmlIdentificacion = new FXMLLoader(getClass().getResource("Identificacion.fxml"));
        Parent panelIdentificacion = fxmlIdentificacion.load();
        Scene sceneIdentificacion = new Scene(panelIdentificacion);

        FXMLLoader fxmlRContrasena = new FXMLLoader(getClass().getResource("RPassword.fxml"));
        Parent panelRContrasena = fxmlRContrasena.load();
        Scene sceneRContrasena = new Scene(panelRContrasena);

        // cambio a identificacion
        IU_SignUp registroController = fxmlRegistro.getController();
        // cambio a registro
        IU_Login identificacionController = fxmlIdentificacion.getController();
        // cambio a rec contraseña
        IU_RPass rPassController = fxmlRContrasena.getController();


        registroController.setSceneIdentificacion(sceneIdentificacion);

        identificacionController.setSceneRegistro(sceneRegistro);
        identificacionController.setSceneRPassword(sceneRContrasena);

        rPassController.setSceneIdentificacion(sceneIdentificacion);


        stage.setTitle("Sesión");
        stage.setScene(sceneIdentificacion);
        stage.show();
    }
}

