package packPrincipal;

import packControlador.Controlador;

import java.io.IOException;

public class BarBestial {
    public static void main(String[] args) {
        //Solo de momento y para pruebas
        String[] cmd = {"/bin/zsh","-c","java -jar exe.jar"};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controlador controlador = new Controlador();
        controlador.iniciarAplicacion();
    }
}
