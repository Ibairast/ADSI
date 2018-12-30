package packVista.sesion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Sesion extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlRegistro = new FXMLLoader(getClass().getResource("guiSesionFxml/Registro.fxml"));
        Parent panelRegistro = fxmlRegistro.load();
        Scene sceneRegistro = new Scene(panelRegistro);


        FXMLLoader fxmlIdentificacion = new FXMLLoader(getClass().getResource("guiSesionFxml/Identificacion.fxml"));
        Parent panelIdentificacion = fxmlIdentificacion.load();
        Scene sceneIdentificacion = new Scene(panelIdentificacion);

        // cambio de registro a identificacion
        IU_SignUp registroController =  fxmlRegistro.getController();
        registroController.setIdentificacionScene(sceneIdentificacion);

        // cambio de identificacion a registro
        IU_Login identificacionController = fxmlIdentificacion.getController();
        identificacionController.setRegistroScene(sceneRegistro);

        stage.setTitle("Sesión");
        stage.setResizable(false);
        stage.setScene(sceneIdentificacion);
        stage.show();
    }
}