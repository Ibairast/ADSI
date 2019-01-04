package packControlador;

import packModelo.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GestorCarga {
    private static GestorCarga mGestor=null;
    private String user;

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
        //Get datos partida
        String sql = "SELECT * FROM Partida WHERE IdUsuario = '" + user + "' AND IdPartida = '"+NombreP+"'";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                Partida.getMiPartida().setUser(rs.getString("IdUsuario"));
                Partida.getMiPartida().setMazoP(rs.getString("MazoP"));
                Partida.getMiPartida().setNAyudas(rs.getInt("NAyudas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            while (rs.next()) {
                switch (rs.getString("Grupo")) {
                    case "Cielo":
                        if (rs.getString("Color")=="AZUL"){
                             color=EnumColor.AZUL;
                        }else{
                            color =EnumColor.VERDE;
                        }
                        c=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal(rs.getString("Animal")),color);
                        cielo.anadirCarta(c);
                        break;
                    case "manoJ":
                        if (rs.getString("Color")=="AZUL"){
                            color=EnumColor.AZUL;
                        }else{
                            color=EnumColor.VERDE;
                        }
                        c=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal(rs.getString("Animal")),color);
                        manoJ.anadirCarta(c);
                        break;
                    case "mazoJ":
                        if (rs.getString("Color")=="AZUL"){
                            color=EnumColor.AZUL;
                        }else{
                            color=EnumColor.VERDE;
                        }
                        c=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal(rs.getString("Animal")),color);
                        mazoJ.anadirCarta(c);
                        break;
                    case "manoIA":
                        if (rs.getString("Color")=="AZUL"){
                            color=EnumColor.AZUL;
                        }else{
                            color=EnumColor.VERDE;
                        }
                        c=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal(rs.getString("Animal")),color);
                        manoIA.anadirCarta(c);
                        break;
                    case "mazoIA":
                        if (rs.getString("Color")=="AZUL"){
                            color=EnumColor.AZUL;
                        }else{
                            color=EnumColor.VERDE;
                        }
                        c=new Carta(AnimalFactory.getMiAnimalFactory().crearAnimal(rs.getString("Animal")),color);
                        mazoIA.anadirCarta(c);
                        break;
                    case "cola":
                        if (rs.getString("Color")=="AZUL"){
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

    }

    /**
     *
     * @param NombreP
     */
    public void eliminarPartida(String NombreP) {
        // TODO - implement GestorCarga.eliminarPartida
        throw new UnsupportedOperationException();
    }

    public void getPartidas() {
        // TODO - implement GestorCarga.getPartidas
        throw new UnsupportedOperationException();
    }
}
