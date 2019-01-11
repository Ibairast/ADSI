package packModelo;

import org.json.JSONObject;


public class Usuario {
    private static Usuario miUsuario = null;
    private String IdUsuario, Pass;
    private String LogFecha;
    private int Ayuda;

    private Usuario() {

    }

    public static Usuario getUsuario() {
        if (miUsuario == null) {
            miUsuario=new Usuario();
        }
        return miUsuario;
    }

    public void cargarUsuario(JSONObject js) {
        IdUsuario = js.getString("IdUsuario");
        Pass = js.getString("Pass");
        LogFecha = js.getString("LogFecha");
        Ayuda = js.getInt("Ayuda");
    }

    public String getIdUsuario() {
        return IdUsuario;
    }


    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getLogFecha() {
        return LogFecha;
    }

    public int getAyuda() {
        return Ayuda;
    }

    public void setAyuda(int ayuda) {
        Ayuda = ayuda;
    }

    public String getMazoP() {
        /**

         Alain Please ;)
         **/
        return null;
    }
}
