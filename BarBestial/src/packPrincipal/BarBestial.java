package packPrincipal;

import packControlador.Controlador;

public class BarBestial {

    public static void main(String[] args)  {

        try{
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", "exe.jar");
            pb.start();
        }catch (Exception e){
            e.printStackTrace();
        }
        Controlador controlador = new Controlador();
        controlador.iniciarAplicacion();
    }
}
