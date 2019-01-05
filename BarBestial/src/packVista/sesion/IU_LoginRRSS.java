package packVista.sesion;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import packControlador.Controlador;

import javax.swing.*;
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

        String[] url = engine.getLocation().split("=");
        System.out.println(url[1]);
       if (Controlador.getMiControlador().identificarRRSS(url[1])){
           JOptionPane.showConfirmDialog(null,
                   "Bienvenido", "Éxito", JOptionPane.DEFAULT_OPTION);
           Controlador.getMiControlador().mostarVentanaInicio();
       }else {

           JOptionPane.showConfirmDialog(null,
                   "Por favor, cambie su contraseña", "Éxito", JOptionPane.DEFAULT_OPTION);
           Controlador.getMiControlador().mostarVentanaInicio();
       }
        Platform.exit();
    }
}
