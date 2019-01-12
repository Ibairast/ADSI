package packModelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Carta implements Comparable<Carta> {
    private Animal animal;
    private EnumColor color;

    public Carta(Animal animal, EnumColor color) {
        this.animal = animal;
        this.color = color;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public EnumColor getColor() {
        return this.color;
    }

    public String getEspecie() {
        return this.animal.getEspecie();
    }

    public int getFuerza() {
        return this.animal.getFuerza();
    }

    public void hacerAnimalada() {
        this.animal.hacerAnimalada();
    }

    @Override
    public int compareTo(Carta o) {
        if (o.getAnimal().getFuerza() <= this.animal.getFuerza()) {
            return -1;
        } else {
            if (o.getAnimal().getFuerza() > this.animal.getFuerza()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public void guardarCarta(String nombreP,String grupo) {

        String sql = "INSERT INTO Cartas VALUES('"+nombreP+"','"+Usuario.getUsuario().getIdUsuario()+"','"+grupo+"','"+this.color.toString()+"','"+this.animal.toString()+"')";
        try (Connection conn = SGBD.getMiSGBD().conectarBD();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
