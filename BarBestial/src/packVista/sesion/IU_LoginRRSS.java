package packVista.sesion;

import javafx.application.Platform;
import javafx.fxml.Initializable;
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
    public Button btnSgt;
    private WebEngine engine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = web.getEngine();
        engine.load("http://localhost:8080/barbes/index.jsp");
        JOptionPane.showConfirmDialog(null,
                "Después de identificarse y la pantalla esté sin datos, pulsar el botón 'Aceptar'", "Identificación", JOptionPane.DEFAULT_OPTION);

    }

    public void eventConfirmar(MouseEvent mouseEvent) {
        engine.load("http://localhost:8080/barbes/index.jsp");
        btnClose.setVisible(true);
        btnSgt.setVisible(false);
        JOptionPane.showConfirmDialog(null,
                "Enlace su cuenta", "Identificación", JOptionPane.DEFAULT_OPTION);

    }

    public void eventSalir(MouseEvent mouseEvent) {

        String[] url = engine.getLocation().split("=");
        if (Controlador.getMiControlador().identificarRRSS(url[1])) {
            JOptionPane.showConfirmDialog(null,
                    "Bienvenido", "Éxito", JOptionPane.DEFAULT_OPTION);
            Controlador.getMiControlador().mostarVentanaInicio();

            Platform.exit();
        }
    }

}
