package pruebasConCodigoJava;
import Pruebas.*;

public class ComponentePruebaSiguientePrimo 
{

 public static void main(String args[]) {
  String[] num;
  int result;
  
  // Caso de prueba 1
        num = new String[1];
        num[0] = "0";
        try {result= SiguientePrimo.siguientePrimo(num);
             if (result==1) System.out.println("Caso de prueba 1 correcto");
             else System.out.println("Caso de prueba 1 incorrecto");}
        catch (Exception e) {System.out.println("Caso de prueba 1 incorrecto");}        

  // Caso de prueba 2
        num = new String[1];
        num[0] = "1";
        try {result= SiguientePrimo.siguientePrimo(num);
             if (result==2) System.out.println("Caso de prueba 2 correcto");
             else System.out.println("Caso de prueba 2 incorrecto");}
        catch (Exception e) {System.out.println("Caso de prueba 2 incorrecto");}        

  // Caso de prueba 3
        num = new String[1];
        num[0] = "2";
        try {result= SiguientePrimo.siguientePrimo(num);
             if (result==3) System.out.println("Caso de prueba 3 correcto");
             else System.out.println("Caso de prueba 3 incorrecto");}
        catch (Exception e) {System.out.println("Caso de prueba 3 incorrecto");}        

  // Caso de prueba 4
        num = new String[1];
        num[0] = "4";
        try {result= SiguientePrimo.siguientePrimo(num);
             if (result==5) System.out.println("Caso de prueba 4 correcto");
             else System.out.println("Caso de prueba 4 incorrecto");}
        catch (Exception e) {System.out.println("Caso de prueba 4 incorrecto");}        
    }
}
   
