package Pruebas;
import Pruebas.Esprimo;
 //import pruebas.resguardoUsandoBD.Esprimo;
 //import pruebas.resguardoSinUsarBD.Esprimo;

public class SiguientePrimo {

public static int siguientePrimo (String args[])
      throws  ErrorNoNumero, 
              ErrorFaltaParametro,
              ErrorSolo1Parametro {
      if (args.length == 0) throw new ErrorFaltaParametro();
      else if (args.length > 1) throw new ErrorSolo1Parametro();
      else { 
        try {
          float numF = Float.parseFloat(args[0]);
          int num = (int)numF;
          if (num<=0) return 1;
          else {
		        int sig = num;
            boolean primo;
            do {
                sig = sig + 1;
                String[] sigTabString = new String[1];
                sigTabString[0] = String.valueOf(sig);
                try {
                  primo=Esprimo.esPrimo(sigTabString);
                }
                catch (ErrorNoNumeroPositivo e) {primo=false;}
                catch (ErrorSolo1Parametro e) {primo=false;}
                catch (ErrorFaltaParametro e) {primo=false;}
            }
            while (!primo);
            return sig;
          }
      }
      catch (NumberFormatException e) { throw new ErrorNoNumero();  }
    }
  }

  public static void main (String args[]) {
    try {System.out.println("Resultado: "+SiguientePrimo.siguientePrimo(args));}
    catch (Exception e) {System.out.println("Error: "+e.toString());}
  }
}

