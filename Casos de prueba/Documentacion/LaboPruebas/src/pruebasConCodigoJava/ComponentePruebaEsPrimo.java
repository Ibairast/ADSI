package pruebasConCodigoJava;
import Pruebas.*;

public class ComponentePruebaEsPrimo 
{

 public static void main(String args[]) {
  String[] num;
  boolean result;
  
  // Caso de prueba 1: sin pasar par�metros
        num = new String[0];
        try {result= Esprimo.esPrimo(num);
             System.out.println("Caso de prueba 1 incorrecto");}
        catch (Exception e) {System.out.println("Caso de prueba 1 correcto");}        

  // Caso de prueba 2: pasando dos par�metros
        num = new String[2];
        num[0]="xx";
        num[1]="yy";
        try {result= Esprimo.esPrimo(num);
             System.out.println("Caso de prueba 2 incorrecto");}
        catch (Exception e) {System.out.println("Caso de prueba 2 correcto");}        

  // Caso de prueba 3: no positivo
        num = new String[1];
        num[0]="-4";
        try {result= Esprimo.esPrimo(num);
             System.out.println("Caso de prueba 3 incorrecto");}
        catch (Exception e) {System.out.println("Caso de prueba 3 correcto");}    

  // Caso de prueba 4
        num = new String[1];
        num[0]="2";
        try {result= Esprimo.esPrimo(num);
             if (result) System.out.println("Caso de prueba 4 correcto");
             else System.out.println("Caso de prueba 4 incorrecto");}
        catch (Exception e) {System.out.println("Caso de prueba 4 incorrecto");}  

  // Caso de prueba 5
        num = new String[1];
        num[0]="3";
        try {result= Esprimo.esPrimo(num);
             if (result) System.out.println("Caso de prueba 5 correcto");
             else System.out.println("Caso de prueba 5 incorrecto");}
        catch (Exception e) {System.out.println("Caso de prueba 5 incorrecto");}  
  
  // Caso de prueba 6
        num = new String[1];
        num[0]="4";
        try {result= Esprimo.esPrimo(num);
             if (result) System.out.println("Caso de prueba 6 incorrecto");
             else System.out.println("Caso de prueba 6 correcto");}
        catch (Exception e) {System.out.println("Caso de prueba 6 incorrecto");} 
        
        
    }
}
   
