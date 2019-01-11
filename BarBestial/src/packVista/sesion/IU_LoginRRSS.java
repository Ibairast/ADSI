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

    /**
     * Inicia la escena con la URL especificada y muestra un pop up indicando un paso a seguir en el proceso de
     * identificación con RRSS.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = web.getEngine();
        engine.load("http://localhost:8080/barbes/index.jsp");
        JOptionPane.showConfirmDialog(null,
                "Después de identificarse y la pantalla esté sin datos, pulsar el botón 'Aceptar'", "Identificación", JOptionPane.DEFAULT_OPTION);

    }

    /**
     * @param mouseEvent Evento ejecutado al pulsar sobre el texto "Aceptar".
     * @precondicion Haberse identificado usando la API de Google.
     * @postcondicion Redirige a la URL especificada, hace que el botón identificado como "btnClose" sea visible,
     * hace que el botón identificado como "btnSgt" no sea visible y muestra un pop up indicando un paso a seguir
     * en el proceso de identificación con RRSS.
     */
    public void eventConfirmar(MouseEvent mouseEvent) {
        engine.load("http://localhost:8080/barbes/index.jsp");
        btnClose.setVisible(true);
        btnSgt.setVisible(false);
        JOptionPane.showConfirmDialog(null,
                "Enlace su cuenta", "Identificación", JOptionPane.DEFAULT_OPTION);

    }

    /**
     * @param mouseEvent Evento ejecutado al pusar sobre el texto "Finalizar".
     * @precondicion Seguir los pasos anteriores y haber enlazado su cuenta con el Bar Bestial.
     * @postcondicion Identifica al usuario, mostrando un pop up de Éxito.
     */
    public void eventFinalizar(MouseEvent mouseEvent) {
        String[] url = engine.getLocation().split("=");
        Controlador.getMiControlador().identificarRRSS(url[1]);
        JOptionPane.showConfirmDialog(null,
                "Bienvenido", "Éxito", JOptionPane.DEFAULT_OPTION);
        Controlador.getMiControlador().mostarVentanaInicio();
        Platform.exit();
    }
}
