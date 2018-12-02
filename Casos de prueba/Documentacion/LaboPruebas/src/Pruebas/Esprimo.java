package Pruebas;

public class Esprimo {

  public static boolean esPrimo (String args[])
      throws  ErrorFaltaParametro,
              ErrorSolo1Parametro,
              ErrorNoNumeroPositivo {
      if (args.length == 0) throw new ErrorFaltaParametro();
      else if (args.length > 1) throw new ErrorSolo1Parametro();
      else { 
        try {
      		float numF = Float.parseFloat(args[0]);
          int num = (int)numF;
          if (num<=0) throw new ErrorNoNumeroPositivo();
          else {
		        for (int i=2;i<num;i++)
		          if (num%i==0) {return false;}
		        return true;
          }
        }
    	  catch (NumberFormatException e) { throw new ErrorNoNumeroPositivo();  }
    	}
  }

  public static void main (String args[]) {
    try {System.out.println("Resultado: "+Esprimo.esPrimo(args));}
    catch (Exception e) {System.out.println("Error: "+e.toString());}
  }
}

