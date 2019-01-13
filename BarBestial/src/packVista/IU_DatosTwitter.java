package packVista;

import packControlador.Controlador;
import twitter4j.TwitterException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class IU_DatosTwitter extends JFrame {

    private JPanel contentPane;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JPasswordField consumerKey;
    private JPasswordField consumerSecret;
    private JPasswordField accessToken;
    private JPasswordField accessTokenSecret;
    private JButton btnAceptar;

    private String resultado;

    /**
     * Create the frame.
     */
    public IU_DatosTwitter(String pResultado) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                .addComponent(getLabel1())
                                                .addContainerGap(44, Short.MAX_VALUE))
                                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                .addComponent(getLabel2(), GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(302, Short.MAX_VALUE))
                                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                .addComponent(getLabel4(), GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(343, Short.MAX_VALUE))
                                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                .addComponent(getLabel5(), GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(90, Short.MAX_VALUE))
                                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                .addComponent(getLabel3())
                                                .addContainerGap(302, Short.MAX_VALUE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(getAccessTokenSecret(), Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(getAccessToken(), Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(getConsumerSecret(), Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(getConsumerKey(), Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE))
                                                .addGap(44))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(getBtnAceptar())
                                                .addContainerGap(375, Short.MAX_VALUE))))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(getLabel1(), GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(getLabel2(), GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(getConsumerKey(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(getLabel3())
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(getConsumerSecret(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(getLabel4(), GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(getAccessToken(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(getLabel5(), GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(getAccessTokenSecret(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(getBtnAceptar())
                                .addContainerGap(5, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
        this.resultado = pResultado;
    }
    private JLabel getLabel1() {
        if (label1 == null) {
            label1 = new JLabel("Inserta los datos asociados a tu cuenta de desarrollador de Twitter");
            label1.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 10));
            label1.setForeground(Color.BLACK);
            label1.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return label1;
    }

    private JLabel getLabel2() {
        if (label2 == null) {
            label2 = new JLabel("Consumer Key:");
            label2.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 12));
        }
        return label2;
    }
    private JLabel getLabel3() {
        if (label3 == null) {
            label3 = new JLabel("Consumer Secret:");
            label3.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 12));
        }
        return label3;
    }
    private JLabel getLabel4() {
        if (label4 == null) {
            label4 = new JLabel("Access Token:");
            label4.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 12));
        }
        return label4;
    }

    private JLabel getLabel5() {
        if (label5 == null) {
            label5 = new JLabel("Access Token Secret:");
            label5.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 12));
        }
        return label5;
    }
    private JPasswordField getConsumerKey() {
        if (consumerKey == null) {
            consumerKey = new JPasswordField();
        }
        return consumerKey;
    }

    private JPasswordField getConsumerSecret() {
        if (consumerSecret == null) {
            consumerSecret = new JPasswordField();
        }
        return consumerSecret;
    }

    private JPasswordField getAccessToken() {
        if (accessToken == null) {
            accessToken = new JPasswordField();
        }
        return accessToken;
    }

    private JPasswordField getAccessTokenSecret() {
        if (accessTokenSecret == null) {
            accessTokenSecret = new JPasswordField();
        }
        return accessTokenSecret;
    }

    // El usuario introduce los datos y pulsa el boton Aceptar
    private JButton getBtnAceptar() {
        if (btnAceptar == null) {
            btnAceptar = new JButton("Aceptar");
            btnAceptar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // Se intenta iniciar sesion
                    try
                    {
                        setVisible(false);
                        Controlador.getMiControlador().iniciarSesionTwitter(new String(consumerKey.getPassword()), new String(consumerSecret.getPassword()), new String(accessToken.getPassword()), new String(accessTokenSecret.getPassword()));
                        // Si los datos introducidos son correctos se publica el resultado
                        int puntuacion = Controlador.getMiControlador().obtenerMiMejorPuntuacion();
                        resultado = resultado + puntuacion + " puntos de fuerza.";
                        Controlador.getMiControlador().twittearResultado(resultado);
                        // Se le muestra el mensaje enviado (Visible por pruebas)
                        JOptionPane.showMessageDialog(null, resultado, "Tweet Enviado", JOptionPane.INFORMATION_MESSAGE);
                    }
                    // Si los datos introducidos no son correctos se le muestra un mensaje de error y se le solicita volver a identificarse
                    catch (TwitterException e)
                    {
                        setVisible(false);
                        JOptionPane.showMessageDialog(null, "Los datos introducidos no son correctos", "Datos Erroneos", JOptionPane.ERROR_MESSAGE);
                        IU_DatosTwitter twitterIU = new IU_DatosTwitter(resultado);
                        twitterIU.setVisible(true);
                    }
                }
            });
        }
        return btnAceptar;
    }
}