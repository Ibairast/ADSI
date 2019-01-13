package func7;

import org.junit.Test;
import packModelo.TwitterJava;
import twitter4j.TwitterException;
import javax.swing.JOptionPane;

public class Funcionalidad7Test {
    @Test
    public void iniciarSesionYTwittear()
    {
        System.out.println("Primera prueba. Introduce tus datos correctamente. Resultado: Tweet publicado.");
        String consumerKey = JOptionPane.showInputDialog("Consumer Key");
        String consumerSecret = JOptionPane.showInputDialog("Consumer Secret");
        String accessToken = JOptionPane.showInputDialog("Access Token");
        String accessTokenSecret = JOptionPane.showInputDialog("Access Token Secret");
        String tweet = JOptionPane.showInputDialog("Tweet (No repetido)");
        try
        {
            TwitterJava.getTwitterJava().iniciarSesionTwitter(consumerKey, consumerSecret, accessToken, accessTokenSecret);
            TwitterJava.getTwitterJava().twittearResultado(tweet);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        System.out.println("Segunda prueba. Introduce tus datos incorrectamente. Resultado: Tweet no publicado.");
        consumerKey = JOptionPane.showInputDialog("Consumer Key");
        consumerSecret = JOptionPane.showInputDialog("Consumer Secret");
        accessToken = JOptionPane.showInputDialog("Access Token");
        accessTokenSecret = JOptionPane.showInputDialog("Access Token Secret");
        tweet = JOptionPane.showInputDialog("Tweet (No repetido)");
        try
        {
            TwitterJava.getTwitterJava().iniciarSesionTwitter(consumerKey, consumerSecret, accessToken, accessTokenSecret);
            TwitterJava.getTwitterJava().twittearResultado(tweet);
        } catch (TwitterException e) {
            System.out.println("ERROR");
        }
    }
}