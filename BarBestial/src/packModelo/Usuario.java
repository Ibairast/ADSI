package packModelo;

import org.json.JSONObject;


public class Usuario {
    private static Usuario miUsuario = new Usuario();
    private String IdUsuario, Pass;
    private String LogFecha;
    private int Ayuda;

    private Usuario() {
    }

    public static Usuario getUsuario() {
        return miUsuario;
    }
    /* ************************************************* FUNCIONALIDAD 1 **********************************************/

    /**
     * @param usuario Datos del usuario
     * @precondicion Ninguna.
     * @postcondicion Carga los atributos del Singleton "Usuario"
     */
    public void cargarUsuario(JSONObject usuario) {
        IdUsuario = usuario.getString("IdUsuario");
        Pass = usuario.getString("Pass");
        LogFecha = usuario.getString("LogFecha");
        Ayuda = usuario.getInt("Ayuda");
    }

    /* ****************************************************************************************************************/


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
