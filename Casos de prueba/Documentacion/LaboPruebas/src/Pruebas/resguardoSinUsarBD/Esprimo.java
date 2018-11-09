package Pruebas.resguardoSinUsarBD;
import Pruebas.*;

public class Esprimo 
{

  public static boolean esPrimo(String[] args) 
    throws  ErrorFaltaParametro, 
            ErrorSolo1Parametro, 
            ErrorNoNumeroPositivo{

  if (args.length==0) throw new ErrorFaltaParametro();
  if (args.length==2 && args[0].equals("xx") && args[1].equals("yy")) throw new ErrorSolo1Parametro();
  if (args.length==1 && args[0].equals("-4")) throw new ErrorNoNumeroPositivo();
  if (args.length==1 && args[0].equals("2")) return true;
  if (args.length==1 && args[0].equals("3")) return true;
  if (args.length==1 && args[0].equals("4")) return false;
  if (args.length==1 && args[0].equals("23")) return true;
  if (args.length==1 && args[0].equals("patata")) throw new ErrorNoNumeroPositivo();
  if (args.length==1 && args[0].equals("-1")) throw new ErrorNoNumeroPositivo();
  if (args.length==1 && args[0].equals("0")) throw new ErrorNoNumeroPositivo();
  if (args.length==1 && args[0].equals("1")) return true;
  if (args.length==1 && args[0].equals("-1234123412341234")) throw new ErrorNoNumeroPositivo();
  if (args.length==1 && args[0].equals("421342314321423142314324")) return false;
  if (args.length==1 && args[0].equals("0.67")) throw new ErrorNoNumeroPositivo();
  if (args.length==1 && args[0].equals("7.98")) return true;
  if (args.length==1 && args[0].equals("782.98234")) return false;
  if (args.length==1 && args[0].equals("4234dsgdsgs")) throw new ErrorNoNumeroPositivo();
  if (args.length==2 && args[0].equals("56") && args[1].equals("34")) throw new ErrorSolo1Parametro();
  if (args.length==1 && args[0].equals("78298234")) return false;

  return false;
}
}