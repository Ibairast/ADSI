package packControlador;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;
import packModelo.SGBD;
import packModelo.Usuario;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class GestorMazoPersonalizado {
    private static GestorMazoPersonalizado miGestorMazoPersonalizado;


    private GestorMazoPersonalizado() {

    }

    public static GestorMazoPersonalizado getMiGestorMazoPersonalizado() {
        if (miGestorMazoPersonalizado == null) {
            miGestorMazoPersonalizado=new GestorMazoPersonalizado();
        }
        return miGestorMazoPersonalizado;
    }

    public Vector<String> llenar_combo() {
        Vector<String> mazos = new Vector<>();
        String iduser= Usuario.getUsuario().getIdUsuario();
        String sql="SELECT IdMazoP FROM MAZOP WHERE IdUsuario='"+ iduser +"'";
        try{
            Connection conn = SGBD.getMiSGBD().conectarBD();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String mazo = rs.getString("IdMazoP");
                mazos.add(mazo);
            }}
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return mazos;
    }

    public void seleccionarPersonalizacion(String mazo){
        String iduser= Usuario.getUsuario().getIdUsuario();
        String sql="UPDATE USUARIO SET IDMazo='"+ mazo +"' WHERE IdUsuario='" + iduser +"'";
        try{
            Connection conn = SGBD.getMiSGBD().conectarBD();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showConfirmDialog(null,"¡Se ha seleccionado el mazo "+ mazo +"!","Exito", JOptionPane.DEFAULT_OPTION);
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void eliminarPersonalizacion(String mazo){
        String iduser= Usuario.getUsuario().getIdUsuario();
        String sql = "SELECT IDMazo FROM USUARIO WHERE IdUsuario='" + iduser +"'";
        String sql2 = "UPDATE USUARIO SET IDMazo = 'defecto' WHERE IdUsuario='" + iduser +"'";
        String sql3 = "DELETE FROM MAZOP WHERE IdMazoP='" + mazo + "'"; //borrarMazo
        try{
            Connection conn = SGBD.getMiSGBD().conectarBD();
            Statement stmt = conn.createStatement();
            if (mazo.equals("defecto")){ //Si quiere borrar el mazo por defecto
                JOptionPane.showConfirmDialog(null,"No puedes borrar el mazo por defecto","Error", JOptionPane.DEFAULT_OPTION);
            }
            else{
                ResultSet rs = stmt.executeQuery(sql);
                String mazoenuso = rs.getString("IDMazo");
                if (mazoenuso.equals(mazo)){ //Si quiere borrar el mazo que esta usando
                    stmt.executeUpdate(sql2);
                    JOptionPane.showConfirmDialog(null,"Quieres borrar tu mazo en uso. Ahora usaras el de por defecto.","Exito", JOptionPane.DEFAULT_OPTION);
                }
                stmt.executeUpdate(sql3);
                JOptionPane.showConfirmDialog(null,"Se ha eliminado el mazo "+ mazo +"","Exito", JOptionPane.DEFAULT_OPTION);
            }
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void anadirPersonalizacion(String nombre, String path){ //Parametros nombre mazo y path
        String iduser= Usuario.getUsuario().getIdUsuario();
        String sql="INSERT INTO MAZOP (IdMazoP,IdUsuario) VALUES ('"+ nombre + "','"+ iduser +"')";
        String [] nombrecartas = new String[] {"CamaleonAzul","CamaleonVerde","CanguroAzul","CanguroVerde","CebraAzul","CebraVerde", "CocodriloAzul", "CocodriloVerde", "FocaAzul", "FocaVerde", "HipoAzul", "HipoVerde", "JiraAzul", "JiraVerde", "LeonAzul", "LeonVerde", "LoroAzul", "LoroVerde", "MofetaAzul", "MofetaVerde", "MonoAzul", "MonoVerde", "SerpienteAzul", "SerpienteVerde", "Patada", "PuertaCielo", "Reverso", "Vacio"};
        if (nombre.isEmpty()==false && path.isEmpty()==false){
            try{
                if(nombreMazoEnBD(nombre, iduser) == null){ //Si quiere añadir un mazo con un nombre ya en uso
                    Connection conn = SGBD.getMiSGBD().conectarBD();
                    System.out.println("Entra"); // AQUI SE ROMPE MAZO=DEFECTO PATH=/IMAGES
                    PreparedStatement stmt2 = conn.prepareStatement(sql);
                    System.out.println("Antes del Statement");
                    stmt2.executeUpdate();
                    System.out.println("Antes del loop");

                    for (int i=0; i<nombrecartas.length; i++){//loop de cambiarImagen por todas las cartas posibles
                        System.out.println("Dentro del loop");
                        cambiarImagenBD(nombre,path,nombrecartas[i],iduser);}
                    JOptionPane.showConfirmDialog(null,"Mazo añadido","Exito", JOptionPane.DEFAULT_OPTION);}

                else{
                    JOptionPane.showConfirmDialog(null,"Ya tienes un mazo con ese nombre","Error", JOptionPane.DEFAULT_OPTION);
                }


            }
            catch (Exception e){
             System.err.println(e.getClass().getName() + ": " + e.getMessage());
             System.exit(0);
            }
        }
        else{
            JOptionPane.showConfirmDialog(null,"Introduce el campo nombre y/o path","Error", JOptionPane.DEFAULT_OPTION);
        }
    }

    public String nombreMazoEnBD(String nombre, String iduser){
        String mazorepetido=null;
        String sql2="SELECT IdMazoP FROM MAZOP WHERE IdMazoP='"+nombre+"' AND IdUsuario='"+iduser+"'";
        try {
            Connection conn = SGBD.getMiSGBD().conectarBD();
            PreparedStatement stmt = conn.prepareStatement(sql2);
            System.out.println("Antes del rs");
            ResultSet rs = stmt.executeQuery();
            System.out.println("Despues del rs");
            if (rs.next()){
                mazorepetido = rs.getString("IdMazoP");
            }

        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return mazorepetido;
    }

    public void cambiarImagenBD(String mazo, String path, String carta,String user){
        //Image foto = new ImageIcon(GestorMazoPersonalizado.class.getResource(path +"/"+ carta + ".jpg")).getImage();
        System.out.println("Despues del getImage");
        try{
            Connection conn = SGBD.getMiSGBD().conectarBD();
            switch (carta) {
                case "CamaleonAzul":
                    try { File f = new File (path +"/"+ carta + ".jpg"); //if (f==null)
                        //Carta personalizada
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET CamaleonAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(3,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                    //else{
                        //Cargar por defecto
                        File f = new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET CamaleonAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        System.out.println("Antes del Stream");
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(3,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "CamaleonVerde":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET CamaleonVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(4,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        File f = new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET CamaleonVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(4,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "CanguroAzul":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET CanguroAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(5,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        File f = new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET CanguroAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(5,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "CanguroVerde":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET CanguroVerde= ?WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(6,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Cargar por defecto
                        File f=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET CanguroVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(6,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "CebraAzul":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET CebraAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(7,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta personalizada
                        File f=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET CebraAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(7,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "CebraVerde":
                    try {
                        File f = new File (path +"/"+ carta + ".jpg");
                        //Carta personalizada
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET CebraVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(8,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Cargar por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql = "UPDATE MAZOP SET CebraVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(8,fis);
                        ps.executeUpdate();

                    }
                    break;
                case "CocodriloAzul":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET CocodriloAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(9,fis);
                        ps.executeUpdate();
                    }
                   catch (FileNotFoundException e){
                        //Cargar por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql = "UPDATE MAZOP SET CocodriloAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(9,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "CocodriloVerde":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET CocodriloVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(10,fis);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Cargar por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql = "UPDATE MAZOP SET CocodriloVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(10,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "FocaAzul":
                    try{
                        File f = new File (path +"/"+ carta + ".jpg");
                        //Carta personalizada
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET FocaAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(11,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET FocaAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(11,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "FocaVerde":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET FocaVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(12,fis);
                        ps.executeUpdate();

                    }
                    catch (FileNotFoundException e){
                        //Cargar por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql = "UPDATE MAZOP SET FocaVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(12,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "HipoAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET HipoAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(13,fis);
                        ps.executeUpdate();

                    }
                    catch (FileNotFoundException e){
                        //Cargar por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql = "UPDATE MAZOP SET HipoAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(13,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "HipoVerde":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET HipoVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(14,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Cargar por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql = "UPDATE MAZOP SET HipoVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(14,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "JiraAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET JiraAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(15,fis);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Cargar por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql = "UPDATE MAZOP SET JiraAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(15,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "JiraVerde":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql2= "UPDATE MAZOP SET JiraVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(16,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET JiraVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(16,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "LeonAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET LeonAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(17,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET LeonAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(17,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "LeonVerde":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET LeonVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(18,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET LeonVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(18,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "LoroAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET LoroAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(19,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET LoroAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(19,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "LoroVerde":
                     try {
                         //Carta personalizada
                         File f = new File (path +"/"+ carta + ".jpg");
                         FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET LoroVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(20,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET LoroVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(20,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "MofetaAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET MofetaAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(21,fis);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET MofetaAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(21,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "MofetaVerde":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET MofetaVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(22,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET MofetaVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(22,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "MonoAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET MonoAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(23,fis);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET MonoAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(23,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "MonoVerde":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET MonoVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(24,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET MonoVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(24,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "SerpienteAzul":
                     try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET SerpienteAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(25,fis);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET SerpienteAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(25,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "SerpienteVerde":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET SerpienteVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(26,fis);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET SerpienteVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(26,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "PuertaCielo":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET PuertaCielo= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(28,fis);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET PuertaCielo= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(28,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "Patada":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET Patada= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(27,fis);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET Patada= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(27,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "Reverso":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET Reverso= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(29,fis);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET Reverso= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(29,fis);
                        ps.executeUpdate();
                    }
                    break;
                case "Vacio":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f);
                        String sql = "UPDATE MAZOP SET Vacio= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBinaryStream(30,fis);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        File f2=new File("/images/" + carta + ".jpg");
                        FileInputStream fis = new FileInputStream(f2);
                        String sql2= "UPDATE MAZOP SET Vacio= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBinaryStream(30,fis);
                        ps.executeUpdate();                    }
                    break;
            }
        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }



    public ImageIcon seleccionarImagenCarta(String pInformacionCarta) {
        ImageIcon foto= null;
        String mazo=null;
        String iduser= Usuario.getUsuario().getIdUsuario();
        String sql="SELECT "+ pInformacionCarta + " FROM MAZOP WHERE IdUsuario='"+ iduser+"' AND IdMazoP='"+ mazo +"'";
        //Coger nombreMazo que se esta usando desde Partida y meterlo en el WHERE
        try{
            Connection conn = SGBD.getMiSGBD().conectarBD();
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql); //Como coger resultado rs y convertirlo en Imagen desde blob para "foto"
            Blob blob = rs.getBlob(pInformacionCarta);
            foto = new ImageIcon( blob.getBytes( 1L, (int) blob.length() ) ); //1 o 1L?
            foto = resizeImageIcon(foto);
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return foto;
    }

    public ImageIcon resizeImageIcon(ImageIcon imagen){
        Image ima = imagen.getImage();
        Image resize = ima.getScaledInstance(160,250, Image.SCALE_SMOOTH);
        ImageIcon resized = new ImageIcon(resize);

        return resized;
    }
}