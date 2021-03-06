package packModelo;

import org.json.JSONObject;


public class Usuario {
    private static Usuario miUsuario = null;
    private String IdUsuario, Pass;
    private String LogFecha;
    private int Ayuda;
    private String IDMazo;

    private Usuario() {

    }

    public static Usuario getUsuario() {
        if (miUsuario == null) {
            miUsuario=new Usuario();
        }
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
        IDMazo = usuario.getString("IDMazo");
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

    public String getIDMazo() {
        return IDMazo;
    }

    public void setIDMazo(String IDMazo) {
        this.IDMazo = IDMazo;
    }
}
