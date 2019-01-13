package packModelo;

import twitter4j.*;

import twitter4j.conf.ConfigurationBuilder;

public class TwitterJava {

    private static TwitterJava miTwitterJava = new TwitterJava();
    private Twitter twitter;

    private TwitterJava() {}

    public static TwitterJava getTwitterJava()
    {
        return miTwitterJava;
    }

    // Metodo que dados los datos de identificacion inicia la sesion
    public void iniciarSesionTwitter(String pConsumerKey, String pConsumerSecret, String pAccessToken, String pAccessTokenSecret) throws TwitterException
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(pConsumerKey)
                .setOAuthConsumerSecret(pConsumerSecret)
                .setOAuthAccessToken(pAccessToken)
                .setOAuthAccessTokenSecret(pAccessTokenSecret);
        twitter = new TwitterFactory(cb.build()).getInstance();

        // Aqui simplemente se comprueba que los datos introducidos son correctos.
        // Si son correctos se muestra el nombre de usuario por consola (Se puede ignorar).
        // Si son erroneos la excepcion viajara hasta la clase IU_DatosTwitter donde sera tratada.
        User usuario = twitter.showUser(twitter.getScreenName());
        System.out.println(usuario.getScreenName());
    }

    // Metodo que publica el resultado de la partida habiendo iniciado previamente sesion.
    // Por tanto, ya esta comprobado que los datos son correctos.
    public void twittearResultado(String pResultado)
    {
        try
        {
            twitter.updateStatus(pResultado);
        }
        catch (TwitterException e)
        {
            e.printStackTrace();
        }
    }
}
