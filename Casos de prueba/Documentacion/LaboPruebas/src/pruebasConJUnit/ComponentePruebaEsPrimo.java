package pruebasConJUnit;
import Pruebas.*;
import junit.framework.*;

public class ComponentePruebaEsPrimo extends TestCase 
{

  String[] num;
  boolean result;
  
  public ComponentePruebaEsPrimo(String nombre)
  { super(nombre);
  }

  protected void setUp() {
         System.out.println("Aqu� inicializo");
     }

  protected void tearDown() {
         System.out.println("Aqu� borro");
     }

  public void testPrimoSinPars() {
        num = new String[0];
        try {result= Esprimo.esPrimo(num);
             assertTrue(false);}
        catch (Exception e) {assertTrue(e instanceof ErrorFaltaParametro);}        
    }

  public void testPrimoXX_YY() {
        num = new String[2];
        num[0]="xx";
        num[1]="yy";
        try {result= Esprimo.esPrimo(num);
             assertTrue(false);}
        catch (Exception e) {assertTrue(e instanceof ErrorSolo1Parametro);}        
    }

  public void testPrimo_4() {
        num = new String[1];
        num[0]="-4";
        try {result= Esprimo.esPrimo(num);
             assertTrue(false);}
        catch (Exception e) {assertTrue(e instanceof ErrorNoNumeroPositivo);}    
    }

  public void testPrimo2() {
        num = new String[1];
        num[0]="2";
        try {result= Esprimo.esPrimo(num);
             assertTrue(result == true);}
        catch (Exception e) {assertTrue(false);}
   }

  public void testPrimo3() {
        num = new String[1];
        num[0]="3";
        try {result= Esprimo.esPrimo(num);
             assertTrue(result == true);}
        catch (Exception e) {assertTrue(false);}
   }
  
  public void testNoPrimo4() {
        num = new String[1];
        num[0]="4";
        try {result= Esprimo.esPrimo(num);
             assertTrue(result == false);}
        catch (Exception e) {assertTrue(false);}
   }

  public static Test suite() {
        return new TestSuite(ComponentePruebaEsPrimo.class);
    }

    
 public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}
   
