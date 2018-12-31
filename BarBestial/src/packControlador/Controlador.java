package packControlador;

import org.json.JSONArray;
import org.json.JSONObject;
import packModelo.Jugador;
import packModelo.Partida;
import packModelo.SGBD;
import packModelo.Tablero;
import packVista.*;

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
    //private IU_Carga ventanaCarga;

    public Controlador() {
        this.partida = Partida.getMiPartida();
        this.tablero = Tablero.getMiTablero();
        this.database = SGBD.getMiSGBD();

        this.ventanaInicio = new VentanaInicio();
        this.ventanaJuego = new VentanaJuego();
        this.ventanaAyuda = new VentanaAyuda();
        this.ventanaRanking = new VentanaRanking();
        this.ventanaFecha = new VentanaFecha();


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

    }

    public static Controlador getMiControlador() {
        if (miControlador == null) {
            miControlador = new Controlador();
        }
        return miControlador;
    }

    public void iniciarAplicacion() {
        this.mostrarVentanaInicio();
    }

    private void mostrarVentanaInicio() {
        this.ventanaInicio.setVisible(true);
    }

    private void mostrarVentanaJuego() {
        this.ventanaJuego.setVisible(true);
    }

    private void mostrarVentanaAyuda() {
        this.ventanaAyuda.setVisible(true);
    }

    private void mostrarVentanaRanking() {
        this.ventanaRanking.setVisible(true);
    }

    private void mostrarFecha(){
        this.ventanaFecha.setVisible(true);
    }

    //private void mostrarVentanaCarga() {this.ventanaCarga.setVisible(true); }

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

        }
    }

    class InstruccionesListener  implements ActionListener {
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
    private class FechaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            mostrarFecha();
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


    public JSONArray obtenerMisMejoresPartidas() {
        return GestorRanking.getMiGestorRanking().obtenerMisMejoresPartidas();
    }


    public JSONArray obtenerMejorPuntuacionDia() {
        return GestorRanking.getMiGestorRanking().obtenerMejorPuntuacionDia();
    }

    private void obtenerMejoresPartidas() {
        this.ventanaRanking.obtenerMejoresPartidas();
    }

    private void obtenerMejorMedia() {
        this.ventanaRanking.obtenerMejorMedia();
    }



    class MisMejoresPartidasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            ventanaRanking.mostrarMisMejoresPartidas();
        }
    }


    class MejorPuntuacionDiaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            ventanaRanking.mostrarMejorPuntuacionDia();
        }
    }


    class MejoresPartidasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            //obtenerMejoresPartidas();
        }
    }


    class MejorMediaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            //obtenerMejorMedia();

        }
    }


}
