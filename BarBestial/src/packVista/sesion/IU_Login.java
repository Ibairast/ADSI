
package packVista.sesion;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class IU_Login {

    public TextField txtCorreo;
    public PasswordField txtPass;
    private Scene firstScene;

    public void setRegistroScene(Scene scene) {
        firstScene = scene;
    }


    public void eventOpenRegistro(MouseEvent mouseEvent) {
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(firstScene);
    }

    public void eventIdentificar(MouseEvent mouseEvent) {

        //IDENTIFICAR
    }
}

