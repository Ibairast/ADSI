package packVista;


import packControlador.Controlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class IU_Contrasena extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField txtPass1;
    private JTextField txtPass2;


    /**
     * Create the frame.
     */
    public IU_Contrasena() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 350, 118);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        contentPane.setBackground(new Color(21, 26, 35));

        setContentPane(contentPane);

        /* Centrar */
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize(); //Tamaño del frame actual (ancho x alto)
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height - 300;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width - 200;
        }
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);


        JLabel lblPass1 = new JLabel("Nueva contraseña");
        lblPass1.setForeground(Color.WHITE);

        JLabel lblTittle = new JLabel("Cambiar Contraseña");
        lblTittle.setForeground(Color.WHITE);
        lblTittle.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblPass2 = new JLabel("Confirmar contraseña");
        lblPass2.setForeground(Color.WHITE);

        txtPass1 = new JPasswordField();
        txtPass2 = new JPasswordField();

        JButton btnCambiarContrasena = new JButton("Aceptar");
        btnCambiarContrasena.setBackground(new Color(51, 204, 204));
        btnCambiarContrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cambiarContrasena();
            }
        });

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).
                addComponent(lblTittle)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPass1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPass1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPass2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPass2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(btnCambiarContrasena, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(lblTittle)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPass1).addComponent(txtPass1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPass2).addComponent(txtPass2))
                .addComponent(btnCambiarContrasena));


    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                IU_Contrasena frame = new IU_Contrasena();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void cambiarContrasena() {
        if (txtPass1.getText().equals(txtPass2.getText()) && !txtPass1.getText().equals("")) {
            Controlador.getMiControlador().cambiarContrasena(txtPass1.getText());
            JOptionPane.showConfirmDialog(null,
                    "Contraseña cambiada", "Éxito", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showConfirmDialog(null,
                    "Contraseña no cambiada", "Error", JOptionPane.DEFAULT_OPTION);
        }

    }




}
