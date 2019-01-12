package packVista.sesion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Sesion extends Application {

    /**
     * Lanzador de Sesion
     */
    public static void main(String... arg) {
        launch(arg);
    }

    /**
     * Inicializador de las interfaces "IU_SignUp", "IU_Login" y "IU_RContra".
     * Carga todos los archivos fxml correspondiendes a las interfaces anteriormente mencionadas y se crea una escena con cada uno.
     * Obtiene el controlador de cada interfaz y se le pasa las interfaces que puede acceder.
     * Finalmente indica a "IU_Login" como interfaz inicial y la muestra.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlRegistro = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent panelRegistro = fxmlRegistro.load();
        Scene sceneRegistro = new Scene(panelRegistro);

        FXMLLoader fxmlIdentificacion = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent panelIdentificacion = fxmlIdentificacion.load();
        Scene sceneIdentificacion = new Scene(panelIdentificacion);

        FXMLLoader fxmlRContrasena = new FXMLLoader(getClass().getResource("RPass.fxml"));
        Parent panelRContrasena = fxmlRContrasena.load();
        Scene sceneRContrasena = new Scene(panelRContrasena);

        IU_SignUp registroController = fxmlRegistro.getController();
        IU_Login identificacionController = fxmlIdentificacion.getController();
        IU_RContra rPassController = fxmlRContrasena.getController();


        // "IU_SignUp" puede acceder a "IU_Login".
        registroController.setSceneIdentificacion(sceneIdentificacion);

        // "IU_Login" puede acceder a "IU_SignUp" y "IU_RContra".
        identificacionController.setSceneRegistro(sceneRegistro);
        identificacionController.setSceneRPassword(sceneRContrasena);

        // "IU_RContra" puede acceder a "IU_Login"
        rPassController.setSceneIdentificacion(sceneIdentificacion);

        stage.setTitle("Sesión");
        stage.setScene(sceneIdentificacion);
        stage.show();

    }
}

