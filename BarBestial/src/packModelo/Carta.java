package packModelo;

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
}
