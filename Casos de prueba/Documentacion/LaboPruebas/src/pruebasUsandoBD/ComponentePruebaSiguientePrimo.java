package pruebasUsandoBD;
import java.sql.*;
import java.util.*;

import Pruebas.*;


public class ComponentePruebaSiguientePrimo {

  public ComponentePruebaSiguientePrimo() {
  }

  public final static int MAXPARAMS = 10;

  public static void main (String args[]) {

  try {
        //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	  	Connection conexion=DriverManager.getConnection("jdbc:ucanaccess://D:/XXXXXX/Pruebas.mdb");
        Statement sentencia=conexion.createStatement();
        conexion.setAutoCommit(false);

        ResultSet rs = sentencia.executeQuery("Select CodPr, NUM, SalidaEsper"+
			                          " from PRU_UNIDAD_SIGUIENTEPRIMO");
        Statement sentencia2=conexion.createStatement();

        while (rs.next()) {
          int codPr = rs.getInt("CodPr");
          String num = rs.getString("NUM");


          StringTokenizer st = new StringTokenizer(num," ");
          String[] tabStr = new String[st.countTokens()];

          int i=0;
          while (st.hasMoreTokens())
            tabStr[i++]= st.nextToken();


          String SalidaEsper = rs.getString("SalidaEsper");
          String resReal;
          String prueba;

          try { int sigPrimo = SiguientePrimo.siguientePrimo(tabStr);
                resReal = String.valueOf(sigPrimo);
          }
          catch (ErrorNoNumero e) {resReal = "no n�mero";}

          if (SalidaEsper.equals(resReal))
            prueba = "OK";
          else prueba = "ERROR";

          sentencia2.executeUpdate("Update PRU_UNIDAD_SIGUIENTEPRIMO"+
                                  " Set 	ResPrueba ='"+ prueba+
				                          "', SalReal ='"+ resReal+
			                            "' where CodPr="+ codPr);

        }
        conexion.commit();
        conexion.close();
      }
      catch (Exception e)
        {System.out.println("Error: "+e.getMessage());
         e.printStackTrace();}
      System.out.println("FIN de la EJECUCI�N del COMPONENTE DE PRUEBA de SIGUIENTE PRIMO");
  }
}
