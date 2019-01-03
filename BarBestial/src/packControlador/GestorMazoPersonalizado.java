package packControlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;
import packModelo.Usuario;
import java.sql.*;

public class GestorMazoPersonalizado {
    private static GestorMazoPersonalizado miGestorMazoPersonalizado;
    private Connection c;
    private Statement s;


    private GestorMazoPersonalizado() {

    }

    public static GestorMazoPersonalizado getMiGestorMazoPersonalizado() {
        if (miGestorMazoPersonalizado == null) {
            miGestorMazoPersonalizado=new GestorMazoPersonalizado();
        }
        return miGestorMazoPersonalizado;
    }

    public JSONArray llenar_combo() {
        JSONArray json = new JSONArray();
        String iduser= Usuario.getUsuario().getIdUsuario();
        try{
            Class.forName("org.sqlite.JDBC");
            c= DriverManager.getConnection("jdbc:sqlite:barbes.db");
            c.setAutoCommit(false);
            String sql="SELECT IdMazoP FROM MazoP WHERE IdUsuario=iduser";
            s = c.createStatement();
            ResultSet rs= s.executeQuery(sql);
            while (rs.next()) {
                JSONObject js = new JSONObject();
                String nombreMazo = rs.getString("IdMazoP");
                js.put("IdMazoP", nombreMazo);
                json.put(js);
            }}
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return json;
    }
}