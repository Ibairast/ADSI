package packModelo;

import packControlador.GestorCarga;
import packControlador.GestorRanking;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Partida extends Observable {
    private static Partida miPartida;
    private String user;
    private String mazoP;
    private int NAyudas;

    /* El turno se representa como un numero
     * que indica la posicion de la lista de jugadores
     * que jugara cada turno. Incrementara y volverá
     * a 0 cuando todos hayan jugado. */
    private int turnoActual;
    private ArrayList<Jugador> listaJugadores;

    private Partida() {
        this.listaJugadores = new ArrayList<Jugador>();
    }

    public static Partida getMiPartida() {
        if (miPartida == null) {
            miPartida = new Partida();
        }
        return miPartida;
    }

    public String getMazo(){
        return this.mazoP;
    }
    
    public void inicializarPartida() {
        this.user=Usuario.getUsuario().getIdUsuario();
        Tablero tablero = Tablero.getMiTablero();
        tablero.vaciar();

        Bar bar = Bar.getMiBar();
        bar.vaciar();

        EsLoQueHay elqh = EsLoQueHay.getMiEsLoQueHay();
        elqh.vaciar();

        this.listaJugadores.add(new JugadorReal(this.user, EnumColor.AZUL));
        this.listaJugadores.add(new Maquina("Maquina", EnumColor.VERDE));

        this.repartirCartas();
        this.turnoActual = 0;
    }
    
    public void jugarTurno() {
        Tablero tablero = Tablero.getMiTablero();
        Jugador jugador;
        jugador = this.obtenerJugadorTurnoActual();
        jugador.jugarTurno();
        tablero.hacerUltimaAnimalada();
        tablero.hacerAnimaladasRecurrentes();
        tablero.revisarCola();

        if (this.comprobarFinalizacion()) {
            this.finalizar();
        } else {
            this.avanzarTurno();
        }

    }

    public ArrayList<Jugador> obtenerJugadores() {
        return this.listaJugadores;
    }

    public Jugador obtenerJugadorTurnoActual() {
        return this.listaJugadores.get(turnoActual);
    }

    private void repartirCartas() {
        Iterator<Jugador> iterator = this.listaJugadores.iterator();
        Jugador jugador;

        while (iterator.hasNext()) {
            jugador = iterator.next();
            jugador.repartirCartas();
        }
    }

    private void avanzarTurno() {
        this.turnoActual++;

        if (this.turnoActual == this.listaJugadores.size()) {
            turnoActual = 0;
        }
    }

    private void finalizar() {
        String infoGanador = this.obtenerInformacionGanador();
        /* Guardar ganador en la base de datos */
        GestorRanking.getMiGestorRanking().insertarPuntuacion(obtenerInformacionJugador());

        /* Notificar a la interfaz */
        this.notificar("fin-" + infoGanador);
    }

    private boolean comprobarFinalizacion() {
        Iterator<Jugador> it = this.listaJugadores.iterator();
        Jugador j;
        boolean quedanCartas = false;

        while (it.hasNext() && !quedanCartas) {
            j = it.next();
            if (j.tieneCartas()) {
                quedanCartas = true;
            }
        }
        return !quedanCartas;
    }

    private String obtenerInformacionGanador() {
        Bar bar = Bar.getMiBar();
        EnumColor color = bar.calcularGanador();
        String infoGanador = null;
        if (color == null) {
            infoGanador = "Empate";
        } else {
            boolean ganadorEncontrado = false;
            Iterator<Jugador> it = this.listaJugadores.iterator();
            Jugador jugador = null;

            while (it.hasNext() && !ganadorEncontrado) {
                jugador = it.next();
                if (jugador.getColorJugador().equals(color)) {
                    ganadorEncontrado = true;
                    infoGanador = jugador.getNombre();
                }
            }
            String numeroDeCartasGanador = Integer.toString(this.obtenerNumeroDeCartasColorEnBar(color));
            String fuerzaGanador = Integer.toString(this.obtenerFuerzaColorEnBar(color));
            infoGanador = infoGanador + " " + numeroDeCartasGanador + " " + fuerzaGanador;
        }
        return infoGanador;
    }

    private String obtenerInformacionJugador(){
        String info = null;
        String fuerza = Integer.toString(this.obtenerFuerzaColorEnBar(EnumColor.AZUL));
        EnumColor color = Bar.getMiBar().calcularGanador();
        if (color == EnumColor.AZUL){
            info =  " " + fuerza + " " + 1;
        }else{
            info = " " + fuerza + " " + 0;

        }
        return info;
    }

    private int obtenerNumeroDeCartasColorEnBar(EnumColor pColor) {
        return Bar.getMiBar().obtenerNumeroDeCartasColor(pColor);
    }

    private int obtenerFuerzaColorEnBar(EnumColor pColor) {
        return Bar.getMiBar().obtenerFuerzaColor(pColor);
    }


    
    private void notificar(String pInformacion) {
        super.setChanged();
        super.notifyObservers(pInformacion);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setMazoP(String mazoP) {
        this.mazoP = mazoP;
    }

    public void setNAyudas(int NAyudas) {
        this.NAyudas = NAyudas;
    }

    /**
     * Función principal para guardar la partida
     * @param nombreP: String con el id de la partida de la bd
     */
    public void guardarPartida(String nombreP) {
        this.mazoP=Usuario.getUsuario().getIDMazo();
        this.NAyudas= Usuario.getUsuario().getAyuda();
        this.user=Usuario.getUsuario().getIdUsuario();

        //Guardar datos partida
        String sql= "INSERT INTO Partida VALUES('"+nombreP+"','"+ this.user +"','"+mazoP+"','"+NAyudas+"')";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.executeUpdate();
        } catch (Exception e) {
             e.printStackTrace();
             JOptionPane.showMessageDialog(null,"Error al guardar la partida","Error",JOptionPane.INFORMATION_MESSAGE);
        }

        //Guardar Listas
        Bar.getMiBar().guardarCielo(nombreP);
        Tablero.getMiTablero().guardarCola(nombreP);
        for (Jugador j:this.listaJugadores) {
            j.guardarMazos(nombreP);
        }
        JOptionPane.showMessageDialog(null, "Partida guardada correctamente", "Partida Guardada", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Crea los jugadores y asigna el turno
     */
    public void cargarPartida(){
        Tablero tablero = Tablero.getMiTablero();
        tablero.vaciar();

        Bar bar = Bar.getMiBar();
        bar.vaciar();

        EsLoQueHay elqh = EsLoQueHay.getMiEsLoQueHay();
        elqh.vaciar();
        this.user=Usuario.getUsuario().getIdUsuario();
        this.listaJugadores.add(new JugadorReal(this.user, EnumColor.AZUL));
        this.listaJugadores.add(new Maquina("Maquina", EnumColor.VERDE));

        this.turnoActual = 0;
    }
}
