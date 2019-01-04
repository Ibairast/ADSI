package packControlador;

import org.json.JSONArray;
import packModelo.*;
import packVista.*;
import packVista.sesion.Sesion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Controlador {
    private static Controlador miControlador;

    /* Modelo */
    private Partida partida;
    private Tablero tablero;
    private SGBD database;

    /* Vista */
    private VentanaInicio ventanaInicio;
    private VentanaJuego ventanaJuego;
    private VentanaAyuda ventanaAyuda;
    private I_Ranking iRanking;
    private VentanaFecha ventanaFecha;
    private VentanaUsuario ventanaUsuario;
    private IU_Carga ventanaCarga;
    private VentanaPersonalizar ventanaPersonalizar;
    
    private IU_Contrasena ventanaContrasena;

    public Controlador() {
        this.partida = Partida.getMiPartida();
        this.tablero = Tablero.getMiTablero();
        this.database = SGBD.getMiSGBD();

        this.ventanaInicio = new VentanaInicio();
        this.ventanaJuego = new VentanaJuego();
        this.ventanaAyuda = new VentanaAyuda();
        this.iRanking = new I_Ranking();
        this.ventanaFecha = new VentanaFecha();
        this.ventanaUsuario = new VentanaUsuario();
        this.ventanaCarga = new IU_Carga();
        this.ventanaPersonalizar = new VentanaPersonalizar();

        this.ventanaContrasena = new IU_Contrasena();




        /* Listeners VentanaInicio */
        this.ventanaInicio.addNuevaPartidaListener(new NuevaPartidaListener());
        this.ventanaInicio.addInstrucionesListener(new InstruccionesListener());
        this.ventanaInicio.addRankingListener(new RankingListener());
        this.ventanaInicio.addCargarPartidaListener(new CargarPartidaListener());
        this.ventanaInicio.addPersonalizarListener(new PersonalizarListener());
        this.ventanaInicio.addCambiarContraseniaListener(new OpenCambiarContraseniaListener());




        /* Listeners VentanaJuego */
        this.ventanaJuego.addJugarTurnoListener(new JugarTurnoListener());
        this.ventanaJuego.addElegirCarta1Listener(new ElegirCarta1Listener());
        this.ventanaJuego.addElegirCarta2Listener(new ElegirCarta2Listener());
        this.ventanaJuego.addElegirCarta3Listener(new ElegirCarta3Listener());
        this.ventanaJuego.addElegirCarta4Listener(new ElegirCarta4Listener());
        this.ventanaJuego.addSiguienteListener(new SiguienteListener());

        this.ventanaJuego.desactivarBotonJugarTurno();
        this.ventanaJuego.desactivarBotonSiguiente();


        /* Listeners VentanFecha */
        this.ventanaFecha.addobtenerJugadores(new MisJugadores());

        /* Listeners VentanaUsuario */
        this.ventanaUsuario.addEliminar(new EliminarJugadores());
        this.ventanaUsuario.addVolver(new VolverFecha());


        /* Listeners IU_CONTRASENA */
        this.ventanaContrasena.addCambiarContrasena(new CambiarContrasena());
    }


    public static Controlador getMiControlador() {
        if (miControlador == null) {
            miControlador = new Controlador();
        }
        return miControlador;
    }

    public void iniciarAplicacion() {
        Sesion.main();
    }

    private void mostrarIU_Contra() {
        this.ventanaContrasena.setVisible(true);

    }

    private void mostrarVentanaInicio() {
        this.ventanaInicio.setVisible(true);
    }

    private void mostrarVentanaJuego() {
        this.ventanaJuego.setVisible(true);
    }

    private void mostrarVentanaCarga() { this.ventanaCarga.setVisible(true); }

    private void mostrarVentanaAyuda() {
        this.ventanaAyuda.setVisible(true);
    }

    private void mostrarVentanaRanking() {
        this.iRanking.setVisible(true);
    }

    private void mostrarVentanaPersonalizar() {
        this.ventanaPersonalizar.setVisible(true);
    }
    
    private void mostrarVentanafecha() {
        this.ventanaFecha.setVisible(true);
        this.ventanaInicio.setVisible(false);

    }

    private void setUpObservers() {
        ArrayList<Jugador> jugadores = this.partida.obtenerJugadores();
        for (Jugador jug : jugadores) {
            jug.anadirObservador(ventanaJuego);
            /* Notificacion artificial para el inicio de la partida
             * los jugadores no eran observados mientras se repartian
             * las cartas. */
            jug.notificar(jug.obtenerInformacionCartas());
        }
        tablero.addObserver(ventanaJuego);
        partida.addObserver(ventanaJuego);
    }

    //private void mostrarVentanaCarga() {this.ventanaCarga.setVisible(true); }

    //Fecha
    public JSONArray obtenerUsuarios(String fecha) {

        return GestorUsuario.getGestorUsuario().obtenerUsuarios(fecha);
    }

    public void eliminarUsuarios(JSONArray json) {
        GestorUsuario.getGestorUsuario().eliminarUsuarios(json);
    }
    
    //PERSONALIZAR
        public JSONArray llenar_combo(){
        return GestorMazoPersonalizado.getMiGestorMazoPersonalizado().llenar_combo();
        }

    //Ranking
    public JSONArray obtenerMisMejoresPartidas() {
        return GestorRanking.getMiGestorRanking().obtenerMisMejoresPartidas();
    }

    public JSONArray obtenerMejorPuntuacionDia() {
        return GestorRanking.getMiGestorRanking().obtenerMejorPuntuacionDia();
    }

    public JSONArray obtenerMejoresPartidas() {
        return GestorRanking.getMiGestorRanking().obtenerMejoresPartidas();
    }

    public JSONArray obtenerMejorMedia() {
        return GestorRanking.getMiGestorRanking().obtenerMejorMedia();
    }

    //FUNCIONALIDAD 1
    public boolean registrarUsuario(String txtCorreo, String txtPass1) {
        return GestorUsuario.getGestorUsuario().registrarUsuario(txtCorreo, txtPass1);
    }

    public int comprobarUsuario(String correo, String pass) {
        return GestorUsuario.getGestorUsuario().comprobarUsuario(correo, pass);

    }

    public void mostarVentanaInicio() {
        this.mostrarVentanaInicio();
    }

    public void mostrarVentanaFecha() {
        this.mostrarVentanafecha();
    }

    public boolean recuperarContrasena(String correo) {

        return GestorUsuario.getGestorUsuario().recuperarContrasena(correo);
    }

    public void cambiarContrasena(String text) {
        GestorUsuario.getGestorUsuario().cambiarContrasena(Usuario.getUsuario().getIdUsuario(), text);
    }

    private class FechaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarVentanafecha();
        }
    }

    class NuevaPartidaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //ocultarVentanaInicio();
            mostrarVentanaJuego();
            partida.inicializarPartida("test"); //Aquí se debe acceder a la base de datos y pillar el nombre
            setUpObservers();
        }
    }

    private class CargarPartidaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO mostrar ventana carga
            mostrarVentanaCarga();
        }
    }

    class InstruccionesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarVentanaAyuda();
        }
    }

    class RankingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarVentanaRanking();
        }
    }

    private class OpenCambiarContraseniaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarIU_Contra();
        }
    }

    private class PersonalizarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mostrarVentanaPersonalizar();
        }
    }

    class ElegirCarta1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            partida.obtenerJugadorTurnoActual().elegirCartaMano(0);
            ventanaJuego.desactivarBotonesElegir();
            ventanaJuego.desactivarBotonSiguiente();
            ventanaJuego.activarBotonJugarTurno();
        }
    }

    class ElegirCarta2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            partida.obtenerJugadorTurnoActual().elegirCartaMano(1);
            ventanaJuego.desactivarBotonesElegir();
            ventanaJuego.desactivarBotonSiguiente();
            ventanaJuego.activarBotonJugarTurno();
        }
    }

    class ElegirCarta3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            partida.obtenerJugadorTurnoActual().elegirCartaMano(2);
            ventanaJuego.desactivarBotonesElegir();
            ventanaJuego.desactivarBotonSiguiente();
            ventanaJuego.activarBotonJugarTurno();
        }
    }

    class ElegirCarta4Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            partida.obtenerJugadorTurnoActual().elegirCartaMano(3);
            ventanaJuego.desactivarBotonesElegir();
            ventanaJuego.desactivarBotonSiguiente();
            ventanaJuego.activarBotonJugarTurno();
        }
    }


    class JugarTurnoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            partida.jugarTurno();

            ventanaJuego.desactivarBotonJugarTurno();
            ventanaJuego.activarBotonSiguiente();
        }
    }

    class SiguienteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            partida.obtenerJugadorTurnoActual().elegirCartaMano(0);
            partida.jugarTurno();

            ventanaJuego.activarBotonesElegir();
            ventanaJuego.desactivarBotonJugarTurno();
            ventanaJuego.desactivarBotonSiguiente();
        }
    }
    

    //Fecha
    class MisJugadores implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (ventanaFecha.comprobarFecha()) {
                JSONArray json = ventanaFecha.obtenerJugadores();
                ventanaUsuario.setVisible(true);
                ventanaFecha.setVisible(false);
                ventanaFecha.cerrarVentana();
                ventanaFecha.limpiarVentana();
                ventanaUsuario.cargarUsuarios(json);

            } else {
                JOptionPane.showConfirmDialog(null,
                        "Error,el formato de la fecha no es correcto", "Fecha", JOptionPane.DEFAULT_OPTION);
            }

        }
    }

    class EliminarJugadores implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (ventanaUsuario.algunoPulsado()) {
                JSONArray json = ventanaUsuario.eliminarUsuarios();
                ventanaUsuario.limpiarVentana();
                eliminarUsuarios(json);
                JOptionPane.showConfirmDialog(null,
                        "Usuario eliminado", "Usuario", JOptionPane.DEFAULT_OPTION);
                ventanaUsuario.cerrarVentana();
                ventanaFecha.setVisible(true);


            } else {
                JOptionPane.showConfirmDialog(null,
                        "Error,debes seleccionar al menos un usuario", "Usuario", JOptionPane.DEFAULT_OPTION);
            }
        }
    }
    class VolverFecha implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ventanaUsuario.limpiarVentana();
            ventanaUsuario.cerrarVentana();
            ventanaFecha.setVisible(true);
        }
    }


    class CambiarContrasena implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ventanaContrasena.cambiarContrasena();
        }
    }
}
