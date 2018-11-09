package Pruebas.resguardoUsandoBD;
import java.sql.*;

import Pruebas.*;

public class Esprimo 
{

  public static boolean esPrimo(String[] args) 
    throws  ErrorFaltaParametro, 
            ErrorSolo1Parametro, 
            ErrorNoNumeroPositivo{

        try {
          //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
          String argsString = "";
          for (int i=0;i<args.length;i++)
            argsString=argsString+args[i]+" ";
        
          Connection conexion=DriverManager.getConnection("jdbc:ucanaccess://D:/XXXXXX/Pruebas.mdb");Statement sentencia=conexion.createStatement();
          ResultSet rs = sentencia.executeQuery("Select SalidaEsper"+
                                  " from PRU_UNIDAD_ESPRIMO where NUM='"+argsString+"'");
          if (!rs.next()) return false;
          else {  String res = rs.getString(1);
                  if (res.equals("primo")) return true;
                  else if (res.equals("no primo")) return false;
                  else throw new ErrorNoNumeroPositivo(); }
        } catch (Exception e) {System.out.println("Error: "+e.getMessage());
                               throw new ErrorNoNumeroPositivo();}
  }
}