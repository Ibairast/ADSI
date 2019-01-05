package packVista.sesion;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class IU_LoginRRSS implements Initializable {

    public WebView web;
    public Button btnClose;
    private WebEngine engine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = web.getEngine();
        engine.load("http://localhost:8080/barbes/index.jsp");


    }

    public void eventConfirmar(MouseEvent mouseEvent) {
        engine.load("http://localhost:8080/barbes/index.jsp");
    }

    public void eventSalir(MouseEvent mouseEvent) {

        System.out.println(engine.getLocation());

        // get a handle to the stage
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
