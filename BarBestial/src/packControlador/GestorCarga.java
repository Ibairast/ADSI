package packControlador;

import packModelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GestorCarga {
    private static GestorCarga mGestor=null;
    private String user=Usuario.getUsuario().getIdUsuario();
    private String mazoP;

    private GestorCarga() {
    }

    public static GestorCarga getGestor(){
        if (mGestor == null) {
            mGestor = new GestorCarga();
        }return mGestor;
    }

    /**
     * Método principal para cargar partida
     * @param NombreP
     */
    public void cargarPartida(String NombreP) {
        //Crear en partida la lista de jugadores
        Partida.getMiPartida().cargarPartida();
        //Get datos partida
        String sql = "SELECT * FROM Partida WHERE IdUsuario = '" + user + "' AND IdPartida = '"+NombreP+"'";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                this.mazoP=rs.getString("IdMazoP");
                Partida.getMiPartida().setNAyudas(rs.getInt("NAyudas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        GestorMazoPersonalizado.getMiGestorMazoPersonalizado().seleccionarPersonalizacion(this.mazoP,this.user);
        //Get Cartas

        ListaCartas cielo = new ListaCartas();
        ListaCartas manoJ = new ListaCartas();
        ListaCartas mazoJ = new ListaCartas();
        ListaCartas manoIA = new ListaCartas();
        ListaCartas mazoIA = new ListaCartas();
        ListaCartas cola = new ListaCartas();
        sql = "SELECT * FROM Cartas WHERE IdUsuario = '" + user + "' AND IdCartas = '"+NombreP+"'";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            EnumColor color;
            Carta c;
            //En base al grupo al que pertenecen, su color, y el animal, se crean los objetos Carta y se asignan a la lista correspondiente
            while (rs.next()) {
                switch (rs.getString("Grupo")) {
                    case "Cielo":
                        if (rs.getString("Color").equals("AZUL")){
                             color=EnumColor.AZUL;
                        }else{
                            color =EnumColor.VERDE;
                        }
                        c=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal(rs.getString("Animal")),color);
                        cielo.anadirCarta(c);
                        break;
                    case "ManoJ":
                        if (rs.getString("Color").equals("AZUL")){
                            color=EnumColor.AZUL;
                        }else{
                            color=EnumColor.VERDE;
                        }
                        c=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal(rs.getString("Animal")),color);
                        manoJ.anadirCarta(c);
                        break;
                    case "MazoJ":
                        if (rs.getString("Color").equals("AZUL")){
                            color=EnumColor.AZUL;
                        }else{
                            color=EnumColor.VERDE;
                        }
                        c=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal(rs.getString("Animal")),color);
                        mazoJ.anadirCarta(c);
                        break;
                    case "ManoIA":
                        if (rs.getString("Color").equals("AZUL")){
                            color=EnumColor.AZUL;
                        }else{
                            color=EnumColor.VERDE;
                        }
                        c=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal(rs.getString("Animal")),color);
                        manoIA.anadirCarta(c);
                        break;
                    case "MazoIA":
                        if (rs.getString("Color").equals("AZUL")){
                            color=EnumColor.AZUL;
                        }else{
                            color=EnumColor.VERDE;
                        }
                        c=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal(rs.getString("Animal")),color);
                        mazoIA.anadirCarta(c);
                        break;
                    case "Cola":
                        if (rs.getString("Color").equals("AZUL")){
                            color=EnumColor.AZUL;
                        }else{
                            color=EnumColor.VERDE;
                        }
                        c=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal(rs.getString("Animal")),color);
                        cola.anadirCarta(c);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Colocar las cartas
        Bar.getMiBar().setCielo(cielo);
        Tablero.getMiTablero().setCola(cola);
        ArrayList<Jugador> js=Partida.getMiPartida().obtenerJugadores();
        for (Jugador j:js) {
            if (j instanceof JugadorReal) {
                j.setMano(manoJ);
                j.setMazo(mazoJ);
            }else{
                j.setMazo(mazoIA);
                j.setMano(manoIA);
            }
        }

    }

    /**
     *Eliminar la partida dada
     * @param NombreP
     */
    public void eliminarPartida(String NombreP) {
        String sql="Delete From Partida where IdPartida = '"+NombreP+"' AND IdUsuario = '"+this.user+"'";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        String sql2="Delete From Cartas where IdCartas = '"+NombreP+"' AND IdUsuario = '"+this.user+"'";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             PreparedStatement pstmt = conn.prepareStatement(sql2)) {
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Select con los nombres de las partidas para la ventana de carga
     * @return
     */
    public ArrayList<String> getPartidas() {
        ArrayList<String> partidas = new ArrayList<>();
        String sql="Select IdPartida From Partida Where IdUsuario = '"+this.user+"'";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                partidas.add(rs.getString("IdPartida"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return partidas;
    }
}
