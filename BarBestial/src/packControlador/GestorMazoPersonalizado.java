package packControlador;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import packModelo.Partida;
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

    /** Metodo para llenar JComboBox
     *
     * @return Vector<Sting> Con los nombres de los mazos de dicho Usuario
     */
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

    /** Metodo para seleccionarPersonalizacion
     *
     * @param mazo  Nombre del mazo a seleccionar
     * @param iduser correo del usuario
     *
     * Se actualizara la BD de manera que el IDMazo de USUARIO sera el nombre del mazo seleccionado.
     * Atributo que en la BD representa el mazo que el usuario jugara a partir de ahora.
     */
    public void seleccionarPersonalizacion(String mazo, String iduser){
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

    /** Metodo para eliminarPersonalizacion
     *
     * @param mazo Nombre del mazo a eliminar
     * @param iduser correo del usuario
     * @return Devolvera True si se elimina y False en caso negativo.
     *
     * Si quiere borrar el mazo por defecto, no le dejaremos.
     * Si quiere borrar el mazo que ha estado usando hasta ahora, se borrara pero le pondremos que use el de por defecto
     * Si borra un mazo que no esta usando, simplemente se borrara.
     */
    public boolean eliminarPersonalizacion(String mazo, String iduser){
        Boolean bol = false;
        String sql = "SELECT IDMazo FROM USUARIO WHERE IdUsuario='" + iduser +"'"; // Coger el nombre del mazo que esta usando hasta ahora
        String sql2 = "UPDATE USUARIO SET IDMazo = 'defecto' WHERE IdUsuario='" + iduser +"'"; //Cambia el mazo en uso del usuario
        String sql3 = "DELETE FROM MAZOP WHERE IdMazoP='" + mazo + "'"; //borra el mazo
        try{
            Connection conn = SGBD.getMiSGBD().conectarBD();
            Statement stmt = conn.createStatement();
            //Si ha seleccionado borrar el mazo por defecto, no le dejaremos.
            if (mazo.equals("defecto")){
                JOptionPane.showConfirmDialog(null,"No puedes borrar el mazo por defecto","Error", JOptionPane.DEFAULT_OPTION);
            }
            //Si decide borrar uno de sus mazos.
            else{
                ResultSet rs = stmt.executeQuery(sql);
                String mazoenuso = rs.getString("IDMazo");
                //Si quiere borrar el mazo que estaba usando hasta ahora, se borrara y usara el de por defecto.
                if (mazoenuso.equals(mazo)){
                    stmt.executeUpdate(sql2);
                    JOptionPane.showConfirmDialog(null,"Quieres borrar tu mazo en uso. Ahora usaras el de por defecto.","Exito", JOptionPane.DEFAULT_OPTION);
                }
                //Eliminar el mazo
                stmt.executeUpdate(sql3);
                JOptionPane.showConfirmDialog(null,"Se ha eliminado el mazo "+ mazo +"","Exito", JOptionPane.DEFAULT_OPTION);
                bol=true;
            }
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return bol;
    }

    /** Metodo añadirPersonalizacion
     *
     * @param nombre nombre del mazo
     * @param path path a la carpeta
     * @param iduser correo del usuario
     * @return Devolvera True si se añade y False si no
     */
    public boolean anadirPersonalizacion(String nombre, String path, String iduser){
        Boolean bol = false;
        String sql="INSERT INTO MAZOP (IdMazoP,IdUsuario) VALUES ('"+ nombre + "','"+ iduser +"')"; //Añade el nombre y usuario del nuevo mazo
        String [] nombrecartas = new String[] {"CamaleonAzul","CamaleonVerde","CanguroAzul","CanguroVerde","CebraAzul","CebraVerde", "CocodriloAzul", "CocodriloVerde", "FocaAzul", "FocaVerde", "HipoAzul", "HipoVerde", "JiraAzul", "JiraVerde", "LeonAzul", "LeonVerde", "LoroAzul", "LoroVerde", "MofetaAzul", "MofetaVerde", "MonoAzul", "MonoVerde", "SerpienteAzul", "SerpienteVerde", "Patada", "PuertaCielo", "Reverso", "Vacio"};
        if ((nombre!=null && path!=null) && (nombre.isEmpty()==false && path.isEmpty()==false) ){ //Si ha rellenado las casillas nombre y path
            try{
                if(nombreMazoEnBD(nombre, iduser) == null){ //Si quiere añadir un mazo con un nombre nuevo
                    Connection conn = SGBD.getMiSGBD().conectarBD();
                    PreparedStatement stmt2 = conn.prepareStatement(sql);
                    stmt2.executeUpdate();//Añade el nombre y usuario del nuevo mazo

                    for (int i=0; i<nombrecartas.length; i++){//loop de cambiarImagen por todas las cartas posibles
                        cambiarImagenBD(nombre,path,nombrecartas[i],iduser);}
                    //El mazo se habra añadido con exito
                    JOptionPane.showConfirmDialog(null,"Mazo añadido","Exito", JOptionPane.DEFAULT_OPTION);
                    bol =true;}

                else{
                    //Si ya tiene un mazo con ese nombre
                    JOptionPane.showConfirmDialog(null,"Ya tienes un mazo con ese nombre","Error", JOptionPane.DEFAULT_OPTION);
                }


            }
            catch (Exception e){
             System.err.println(e.getClass().getName() + ": " + e.getMessage());
             System.exit(0);
            }
        }
        else{
            //Si no ha rellenado la casilla nombre y/o path
            JOptionPane.showConfirmDialog(null,"Introduce el campo nombre y/o path","Error", JOptionPane.DEFAULT_OPTION);
        }
        return bol;
    }

    /** Metodo nombreMazoEnBD
     * Comprueba si el nombre del mazo ya existe en la BD para ese usuario
     * @param nombre nombre del mazo
     * @param iduser correo usuario
     * @return Devolvera null si no existe y el nombre si existe.
     */
    public String nombreMazoEnBD(String nombre, String iduser){
        String mazorepetido=null;
        String sql2="SELECT IdMazoP FROM MAZOP WHERE IdMazoP='"+nombre+"' AND IdUsuario='"+iduser+"'"; //Coge el nombre del mazo si existe
        try {
            Connection conn = SGBD.getMiSGBD().conectarBD();
            PreparedStatement stmt = conn.prepareStatement(sql2);
            ResultSet rs = stmt.executeQuery();//Coge el nombre del mazo si existe
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

    /** Metodo para cambiar la Imagen en la BD de un mazo
     *
     * @param mazo nombre del mazo
     * @param path Path a la carpeta
     * @param carta Info de la carta a cambiar (Ej: CamaleonAzul)
     * @param user Correo del usuario
     *
     * Se deberian de hacer UPDATE de todas las fotos en el mazo a crear.
     *
     * Si el path existe y tiene las fotos las coge de ahi
     * Si el path no tiene fotos, los nombres estan mal o no existe alguna de las fotos, cogera las fotos de los resources del proyecto.
     *
     * Error en .setBytes:
     * Con setBytes me da un java.lang.ArrayIndexOutOfBoundsException: Index 2 out of bounds for length 1
     * He probado a imprimir la length del Byte[], es de 12000, pero no logro arreglarlo y meter las fotos como Blob a la BD
     *
     */
    public void cambiarImagenBD(String mazo, String path, String carta,String user){
        try{
            Connection conn = SGBD.getMiSGBD().conectarBD();
            switch (carta) {
                case "CamaleonAzul":
                    try {
                        // Añadir Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        //System.out.println(byt.length); length de 12000.
                        String sql2= "UPDATE MAZOP SET CamaleonAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(3,byt); // Columna 3 de la tabla, set Byte[]. ERROR java.lang.ArrayIndexOutOfBoundsException: Index 2 out of bounds for length 1
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){ //Si no encuentra la foto en dicho path
                        //Cargar por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET CamaleonAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(3,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "CamaleonVerde": //Lo mismo por cada carta el mazo
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET CamaleonVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(4,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET CamaleonVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(4,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "CanguroAzul":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET CanguroAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(5,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET CanguroAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(5,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "CanguroVerde":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET CanguroVerde= ?WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(6,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Cargar por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET CanguroVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(6,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "CebraAzul":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET CebraAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(7,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta personalizada
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET CebraAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(7,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "CebraVerde":
                    try {
                        File f = new File (path +"/"+ carta + ".jpg");
                        //Carta personalizada
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET CebraVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(8,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Cargar por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET CebraVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(8,byt);
                        ps.executeUpdate();

                    }
                    break;
                case "CocodriloAzul":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET CocodriloAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(9,byt);
                        ps.executeUpdate();
                    }
                   catch (FileNotFoundException e){
                        //Cargar por defecto
                       BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                       byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET CocodriloAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(9,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "CocodriloVerde":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET CocodriloVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(10,byt);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Cargar por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET CocodriloVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(10,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "FocaAzul":
                    try{
                        File f = new File (path +"/"+ carta + ".jpg");
                        //Carta personalizada
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET FocaAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(11,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET FocaAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(11,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "FocaVerde":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET FocaVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(12,byt);
                        ps.executeUpdate();

                    }
                    catch (FileNotFoundException e){
                        //Cargar por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET FocaVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(12,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "HipoAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET HipoAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(13,byt);
                        ps.executeUpdate();

                    }
                    catch (FileNotFoundException e){
                        //Cargar por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET HipoAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(13,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "HipoVerde":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET HipoVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(14,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Cargar por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET HipoVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(14,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "JiraAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET JiraAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(15,byt);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Cargar por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET JiraAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(15,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "JiraVerde":
                    try {
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET JiraVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(16,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET JiraVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(16,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "LeonAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET LeonAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(17,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET LeonAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(17,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "LeonVerde":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET LeonVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(18,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET LeonVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(18,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "LoroAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET LoroAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(19,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET LoroAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(19,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "LoroVerde":
                     try {
                         //Carta personalizada
                         File f = new File (path +"/"+ carta + ".jpg");
                         BufferedImage imagen = ImageIO.read(f);
                         byte[] byt = imageIconABytes(imagen);
                         String sql = "UPDATE MAZOP SET LoroVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(20,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET LoroVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(20,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "MofetaAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET MofetaAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(21,byt);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET MofetaAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(21,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "MofetaVerde":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET MofetaVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(22,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET MofetaVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(22,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "MonoAzul":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET MonoAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(23,byt);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET MonoAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(23,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "MonoVerde":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET MonoVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(24,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET MonoVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(24,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "SerpienteAzul":
                     try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                         BufferedImage imagen = ImageIO.read(f);
                         byte[] byt = imageIconABytes(imagen);
                         String sql = "UPDATE MAZOP SET SerpienteAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(25,byt);
                        ps.executeUpdate();
                    }
                    catch (FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET SerpienteAzul= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(25,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "SerpienteVerde":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET SerpienteVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(26,byt);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET SerpienteVerde= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(26,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "PuertaCielo":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET PuertaCielo= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(28,byt);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET PuertaCielo= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(28,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "Patada":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET Patada= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(27,byt);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET Patada= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(27,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "Reverso":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET Reverso= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(29,byt);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET Reverso= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(29,byt);
                        ps.executeUpdate();
                    }
                    break;
                case "Vacio":
                    try{
                        //Carta personalizada
                        File f = new File (path +"/"+ carta + ".jpg");
                        BufferedImage imagen = ImageIO.read(f);
                        byte[] byt = imageIconABytes(imagen);
                        String sql = "UPDATE MAZOP SET Vacio= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setBytes(30,byt);
                        ps.executeUpdate();
                    }
                    catch(FileNotFoundException e){
                        //Carta por defecto
                        BufferedImage imagen = ImageIO.read(getClass().getResource("/images/"+carta+".jpg"));
                        byte[] byt = imageIconABytes(imagen);
                        String sql2= "UPDATE MAZOP SET Vacio= ? WHERE IdUsuario='"+ user+"' AND IdMazoP='"+mazo+"'"; //Meter una Image en BLOB ¿Problemas?
                        PreparedStatement ps = conn.prepareStatement(sql2);
                        ps.setBytes(30,byt);
                        ps.executeUpdate();                    }
                    break;
            }
        }
        catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    /**Metodo para seleccionar una imagen de la BD
     *
     * @param pInformacionCarta info de la carta (EJ. CamaleonAzul)
     * @param iduser Correo del usuario
     * @return Devuelve la imagen a mostrar, ya redimensionada
     */
    public ImageIcon seleccionarImagenCarta(String pInformacionCarta, String iduser) {
        ImageIcon foto= null;
        String mazo= Partida.getMiPartida().getMazo();
        String sql="SELECT "+ pInformacionCarta + " FROM MAZOP WHERE IdUsuario='"+ iduser+"' AND IdMazoP='"+ mazo +"'";
        try{
            Connection conn = SGBD.getMiSGBD().conectarBD();
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql); //Selecciona el blob de la carta que queremos mostrar
            Blob blob = rs.getBlob(pInformacionCarta); //Cogemos el Blob de la BD
            foto = new ImageIcon( blob.getBytes( 1, (int) blob.length() ) ); //Transforma de Blob a ImageIcon
            foto = resizeImageIcon(foto); //Redimensiona la foto a 160,250
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return foto;
    }

    /**Metodo para convertir una BufferedImage a un array de bytes
     *
     * @param image La imagen a convertir
     * @return Devuelve el array de bytes de dicha imagen
     * @throws IOException
     */
    public byte[] imageIconABytes (BufferedImage image) throws IOException{
        WritableRaster raster = image.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
        return (data.getData());
    }

    /** Metodo para redimensionar una imagen
     *
     * @param imagen Imagen a redimensionar
     * @return Devuelve la imagen redimensionada a 160 , 250.
     */
    public ImageIcon resizeImageIcon(ImageIcon imagen){
        Image ima = imagen.getImage();
        Image resize = ima.getScaledInstance(160,250, Image.SCALE_SMOOTH);
        ImageIcon resized = new ImageIcon(resize);

        return resized;
    }
}