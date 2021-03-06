package packVista;

import packControlador.Controlador;
import packControlador.GestorCarga;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class IU_Carga extends JFrame{

	private JFrame frame;
	String[] partidas;
	JList<String> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IU_Carga window = new IU_Carga();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IU_Carga() {
		cargar();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(600, 400);
		
		JLabel lblCargarPartida = new JLabel("Cargar Partida");
		lblCargarPartida.setBounds(12, 0, 111, 35);
		frame.getContentPane().add(lblCargarPartida);

		list.setBounds(22, 38, 290, 208);
		frame.getContentPane().add(list);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Listenner que carga la partida seleccionada
				//Muestra la ventana de juego y se cierra
				GestorCarga.getGestor().cargarPartida(list.getSelectedValue());
				Controlador.getMiControlador().mostrarVentanaJuego();
				Controlador.getMiControlador().setUpObservers();
				frame.dispose();
			}
		});
		btnCargar.setBounds(326, 103, 94, 25);
		frame.getContentPane().add(btnCargar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Listenner que coje el valor seleccionado de la lista y lo elimina
				//Se refresca la ventana
				GestorCarga.getGestor().eliminarPartida(list.getSelectedValue());
				new IU_Carga().setVisible(true);
				frame.dispose();
			}
		});
		btnEliminar.setBounds(326, 162, 94, 25);
		frame.getContentPane().add(btnEliminar);
	}

	/**
	 * Llena la lista con el array de partidas guardadas de la bd
	 *Hay que pasar de ArrayList a Array y crear la lista con eso.
	 */
	public void cargar(){
		ArrayList<String> temp =GestorCarga.getGestor().getPartidas();
		this.partidas = new String[temp.size()];
		this.partidas= (String[]) temp.toArray(partidas);
		this.list = new JList<String>(partidas);
	}

	/**
	 * Hay que hacer el set visible del frame, sino se veia una ventana en blanco
	 * @param b
	 */
	@Override
	public void setVisible(boolean b) {
		this.frame.setVisible(b);
	}
}
