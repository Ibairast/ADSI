package pruebasUsandoBD;
import java.sql.*;
import java.util.*;

import Pruebas.*;

public class ComponentePruebaEsprimo {

  public ComponentePruebaEsprimo() {
  }

  public final static int MAXPARAMS = 10;

  public static void main (String args[]) {

  try {
        //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());

	    Connection conexion=DriverManager.getConnection("jdbc:ucanaccess://D:/XXXXXX/Pruebas.mdb");
        Statement sentencia=conexion.createStatement();
        conexion.setAutoCommit(false);

        ResultSet rs = sentencia.executeQuery("Select CodPr, NUM, SalidaEsper"+
			                          " from PRU_UNIDAD_ESPRIMO");
        Statement sentencia2=conexion.createStatement();

        while (rs.next()) {
          int codPr = rs.getInt("CodPr");
          System.out.println("Le�do CodPr="+codPr);
          String num = rs.getString("NUM");
          System.out.println("Le�do num="+num);
          String[] tabStr;
          
          if (num==null) tabStr = new String[0];
          else
            {
            StringTokenizer st = new StringTokenizer(num," ");
            tabStr = new String[st.countTokens()];

            int i=0;
            while (st.hasMoreTokens())
              tabStr[i++]= st.nextToken();
            }

          String SalidaEsper = rs.getString("SalidaEsper");
          String resReal;
          String prueba;

          try { boolean res = Esprimo.esPrimo(tabStr);
                resReal = res ? "primo" : "no primo";
          }
          catch (ErrorNoNumeroPositivo e) {resReal = "no positivo";}
          catch (ErrorSolo1Parametro e) {resReal = "s�lo 1 param";}
          catch (ErrorFaltaParametro e) {resReal = "falta par�metro";}

          if (SalidaEsper.equals(resReal))
            prueba = "OK";
          else prueba = "ERROR";

          sentencia2.executeUpdate("Update PRU_UNIDAD_ESPRIMO"+
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
      
      
      System.out.println("FIN de la EJECUCI�N del COMPONENTE DE PRUEBA de ESPRIMO");
  }
}
