package packControlador;

import org.json.JSONArray;
import org.json.JSONObject;
import packModelo.Jugador;
import packModelo.Partida;
import packModelo.SGBD;
import packModelo.Tablero;
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
    private VentanaRanking ventanaRanking;
    private VentanaFecha ventanaFecha;
    private VentanaUsuario ventanaUsuario;
    private IU_Carga ventanaCarga;

    public Controlador() {
        this.partida = Partida.getMiPartida();
        this.tablero = Tablero.getMiTablero();
        this.database = SGBD.getMiSGBD();

        this.ventanaInicio = new VentanaInicio();
        this.ventanaJuego = new VentanaJuego();
        this.ventanaAyuda = new VentanaAyuda();
        this.ventanaRanking = new VentanaRanking();
        this.ventanaFecha = new VentanaFecha();
        this.ventanaUsuario = new VentanaUsuario();
        this.ventanaCarga = new IU_Carga();




        /* Listeners VentanaInicio */
        this.ventanaInicio.addNuevaPartidaListener(new NuevaPartidaListener());
        this.ventanaInicio.addInstrucionesListener(new InstruccionesListener());
        this.ventanaInicio.addRankingListener(new RankingListener());
        this.ventanaInicio.addCargarPartidaListener(new CargarPartidaListener());
        this.ventanaInicio.addCambiarContraseniaListener(new CambiarContraseniaListener());
        this.ventanaInicio.addPersonalizarListener(new PersonalizarListener());
        this.ventanaInicio.addFechaListener(new FechaListener());




        /* Listeners VentanaJuego */
        this.ventanaJuego.addJugarTurnoListener(new JugarTurnoListener());
        this.ventanaJuego.addElegirCarta1Listener(new ElegirCarta1Listener());
        this.ventanaJuego.addElegirCarta2Listener(new ElegirCarta2Listener());
        this.ventanaJuego.addElegirCarta3Listener(new ElegirCarta3Listener());
        this.ventanaJuego.addElegirCarta4Listener(new ElegirCarta4Listener());
        this.ventanaJuego.addSiguienteListener(new SiguienteListener());

        this.ventanaJuego.desactivarBotonJugarTurno();
        this.ventanaJuego.desactivarBotonSiguiente();

        /* Listeners VentanaRanking */
        this.ventanaRanking.addMisMejoresPartidas(new MisMejoresPartidasListener());
        this.ventanaRanking.addMejorPuntuacionDia(new MejorPuntuacionDiaListener());
        this.ventanaRanking.addMejoresPartidas(new MejoresPartidasListener());
        this.ventanaRanking.addMejorMedia(new MejorMediaListener());

        /* Listeners VentanFecha */
        this.ventanaFecha.addobtenerJugadores(new MisJugadores());
        /* Listeners VentanaUsuario */
        this.ventanaUsuario.addEliminar(new EliminarJugadores());

    }


    public static Controlador getMiControlador() {
        if (miControlador == null) {
            miControlador = new Controlador();
        }
        return miControlador;
    }

    public void iniciarAplicacion() {
        Sesion.main();
        //this.mostrarVentanaInicio();
    }

    private void mostrarVentanaInicio() {
        this.ventanaInicio.setVisible(true);
    }

    private void mostrarVentanaJuego() {
        this.ventanaJuego.setVisible(true);
    }

    private void mostrarVentanaCarga() {
        this.ventanaCarga.setVisible(true);
    }

    private void mostrarVentanaAyuda() {
        this.ventanaAyuda.setVisible(true);
    }

    private void mostrarVentanaRanking() {
        this.ventanaRanking.setVisible(true);
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

    public void eliminarUsuarios(String id) {
        GestorUsuario.getGestorUsuario().eliminarUsuarios(id);
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
            setUpObservers();
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

    private class CambiarContraseniaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class PersonalizarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

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

    //RANKING
    class MisMejoresPartidasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ventanaRanking.mostrarMisMejoresPartidas();
        }
    }

    class MejorPuntuacionDiaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ventanaRanking.mostrarMejorPuntuacionDia();
        }
    }

    class MejoresPartidasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ventanaRanking.mostrarMejoresPartidas();
        }
    }

    class MejorMediaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ventanaRanking.mostrarMejorMedia();

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
                // System.out.println("Se ha pulsado uno");
                JSONArray json = ventanaUsuario.eliminarUsuarios();
                for (int i = 0; i < json.length(); i++) {
                    JSONObject objeto = json.getJSONObject(i);
                    String id = objeto.getString("IdUsuario");
                    eliminarUsuarios(id);
                }
                ventanaUsuario.cerrarVentana();


            } else {
                JOptionPane.showConfirmDialog(null,
                        "Error,debes seleccionar al menos un usuario", "Usuario", JOptionPane.DEFAULT_OPTION);
            }
        }
    }
}
