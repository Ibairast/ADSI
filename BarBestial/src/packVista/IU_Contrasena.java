package packVista;


import packControlador.Controlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class IU_Contrasena extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JButton btnCambiarContraseña;

    private JLabel lblPass1;
    private JLabel lblPass2;
    private JTextField txtPass1;
    private JTextField txtPass2;


    /**
     * Create the frame.
     */
    public IU_Contrasena() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 280, 310);

        contentPane = new JPanel();
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


        lblPass1 = new JLabel("Nueva contraseña");

        lblPass2 = new JLabel("Confirmar contraseña");

        txtPass1 = new JTextField();
        txtPass2 = new JTextField();

        this.btnCambiarContraseña = new JButton("Aceptar");
        this.btnCambiarContraseña.setBackground(new Color(51, 204, 204));

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPass1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPass1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPass2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPass2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(btnCambiarContraseña, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPass1).addComponent(txtPass1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPass2).addComponent(txtPass2))
                .addComponent(btnCambiarContraseña));


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


    public void addCambiarContrasena(ActionListener listenForBtnMisMejoresPartidas) {
        btnCambiarContraseña.addActionListener(listenForBtnMisMejoresPartidas);
    }

    public void cambiarContrasena() {
        if (txtPass1.getText().equals(txtPass2.getText()) && !txtPass1.getText().equals("")) {
            Controlador.getMiControlador().cambiarContrasena(txtPass1.getText());
            JOptionPane.showConfirmDialog(null,
                    "Contraseña cambiada", "Éxito", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showConfirmDialog(null,
                    "Las contraseñas no coinciden", "Error", JOptionPane.DEFAULT_OPTION);
        }
    }


}