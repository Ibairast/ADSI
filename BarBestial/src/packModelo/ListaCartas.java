package packModelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class ListaCartas {
    private ArrayList<Carta> lista;

    public ListaCartas() {
        this.lista = new ArrayList<Carta>();
    }

    public void anadirCarta(Carta pCarta) {
        this.lista.add(pCarta);
    }

    public void moverCartaAPosicion(int pPos, Carta pCarta) {
        this.lista.remove(pCarta);
        this.lista.add(pPos, pCarta);
    }

    public void moverAlPrincipio(Carta pCarta) {
        if (this.lista.contains(pCarta)) {
            this.lista.remove(pCarta);
            this.lista.add(0, pCarta);
        }
    }

    public void sustituirCartaEnPosicion(int pPos, Carta pCarta) {
        this.lista.set(pPos, pCarta);
    }

    public void sustituirCartaEnUltimaPosicion(Carta pCarta) {
        this.lista.set(this.lista.size() - 1, pCarta);
    }

    public void sacarCarta(Carta pCarta) {
        this.lista.remove(pCarta);
    }

    public void vaciar() {
        this.lista.clear();
    }

    public boolean vacia() {
        return this.lista.isEmpty();
    }

    public Carta obtenerCartaEnPosicion(int pPos) {
        return this.lista.get(pPos);
    }

    public Carta obtenerUltimaCarta() {
        return this.lista.get(this.lista.size() - 1);
    }

    public void hacerAnimaladasRecurrentes() {
        int i = 0;
        ArrayList<Carta> cartasRecurridas = new ArrayList<>();
        Carta c;

        while (i < this.lista.size()) {
            c = this.lista.get(i);
            if (c.getAnimal() instanceof IRecurrente) {
                if (!cartasRecurridas.contains(c)) {
                    c.hacerAnimalada();
                    cartasRecurridas.add(c);
                    i = 0;
                    /* i vuelve a valer 0 para comenzar a buscar cartas
                     * recurrentes desde el principio, guardando la carta
                     * que ya ha hecho su animalada recurrente
                     * para que no la vuelva a repetir. */
                }
                else {
                    i++;
                }
            }
            else {
            	i++;
            }
        }
    }
    
    public int obtenerNumeroDeAnimalesDeEspecie(String pEspecie) {
        Iterator<Carta> iterator = this.lista.iterator();
        Carta carta;
        int numAnimales = 0;
        Animal animal = AnimalFactory.getMiAnimalFactory().crearAnimal(pEspecie);

        while (iterator.hasNext()) {
            carta = iterator.next();
            if (carta.getAnimal().getClass().equals(animal.getClass())) {
                numAnimales++;
            }
        }
        return numAnimales;
    }

    public String obtenerEspecieMasFuerte() {
        int maxFuerza = 0;
        Iterator<Carta> iterator = this.lista.iterator();
        Carta carta;
        String especie = null;

        while (iterator.hasNext()) {
            carta = iterator.next();
            if (carta.getAnimal().getFuerza() > maxFuerza) {
                maxFuerza = carta.getAnimal().getFuerza();
                especie = carta.getEspecie();
            }
        }
        return especie;
    }

    public String obtenerEspeciesDeAnimales() {
        Iterator<Carta> iterator = this.lista.iterator();
        String especies = "";
        Carta carta;

        while (iterator.hasNext()) {
            carta = iterator.next();
            especies = especies + carta.getEspecie();
            especies = especies + " ";
        }
        return especies;
    }

    public String obtenerInformacionCartas() {
        Iterator<Carta> iterator = this.lista.iterator();
        Carta carta;
        String infoCartas = "";
        String infoCartaActual;

        while (iterator.hasNext()) {
            carta = iterator.next();
            infoCartaActual = carta.getEspecie();

            switch (carta.getColor()) {
                case AZUL:
                    infoCartaActual = infoCartaActual + "Azul";
                    break;
                case VERDE:
                    infoCartaActual = infoCartaActual + "Verde";
            }

            infoCartas = infoCartas + infoCartaActual;
            if (iterator.hasNext()) {
                infoCartas = infoCartas + " ";
            }
        }
        return infoCartas;
    }

    public int obtenerNumeroDeCartas() {
        return this.lista.size();
    }

    public int obtenerNumeroDeCartasColor(EnumColor pColor) {
        int count = 0;
        for (Carta c : this.lista) {
            if (c.getColor().equals(pColor)) {
                count++;
            }
        }
        return count;
    }

    public int obtenerFuerzaDeCartasColor(EnumColor pColor) {
        int fuerza = 0;
        for (Carta c : this.lista) {
            if (c.getColor().equals(pColor)) {
                fuerza = fuerza + c.getFuerza();
            }
        }
        return fuerza;
    }

    public void sacarTodasLasCartasDeEspecie(String pEspecie) {
        Carta carta;
        Animal animal = AnimalFactory.getMiAnimalFactory().crearAnimal(pEspecie);
        int i = 0;

        while (i < this.lista.size()) {
            carta = this.lista.get(i);
            if (carta.getAnimal().getClass().equals(animal.getClass())) {
                this.lista.remove(carta);
                i--;
            }
            i++;
        }
    }

    public void ordenarPorFuerza() {
        int out, in;

        for (out = this.lista.size() - 1; out > 0; out--) {

            for (in = 0; in < out; in++) {

                if (this.lista.get(in).compareTo(this.lista.get(in + 1)) > 0) {
                    this.intercambiarCartas(in, in + 1);
                }
            }
        }
    }

    public void intercambiarCartas(int pPos1, int pPos2) {
        Carta c = this.lista.get(pPos1);
        this.lista.set(pPos1, this.lista.get(pPos2));
        this.lista.set(pPos2, c);
    }

    public boolean hayCincoCartas() {
        return this.lista.size() == 5;
    }

    public void invertir() {
        Collections.reverse(this.lista);
    }

    public EnumColor obtenerColorMasNumeroso() {
        int cartasVerdes = 0;
        int cartasAzules = 0;
        EnumColor colorActual;
        for (Carta c : this.lista) {
            colorActual = c.getColor();
            switch (colorActual) {
                case VERDE:
                    cartasVerdes++;
                    break;
                case AZUL:
                    cartasAzules++;
            }
        }
        if (cartasAzules > cartasVerdes) {
            return EnumColor.AZUL;
        } else if (cartasAzules < cartasVerdes) {
            return EnumColor.VERDE;
        } else {
            return null;
        }
    }

    public EnumColor obtenerColorMenosFuerza() {
        int fuerzaVerdes = 0;
        int fuerzaAzules = 0;
        int fuerzaActual;
        EnumColor colorActual;
        for (Carta c : this.lista) {
            colorActual = c.getColor();
            fuerzaActual = c.getFuerza();
            switch (colorActual) {
                case VERDE:
                    fuerzaVerdes = fuerzaVerdes + fuerzaActual;
                    break;
                case AZUL:
                    fuerzaAzules = fuerzaAzules + fuerzaActual;
            }
        }
        if (fuerzaAzules > fuerzaVerdes) {
            return EnumColor.VERDE;
        } else if (fuerzaAzules < fuerzaVerdes) {
            return EnumColor.AZUL;
        } else {
            return null;
        }
    }

    public Carta obtenerCartaAleatoria() {
        Random r = new Random();
        int max = this.lista.size();
        if (max > 0) {
            return this.lista.get(r.nextInt(max));
        } else {
            return null;
        }
    }

    public int obtenerPosicionAnimal(Animal pAnimal) {
        int i = this.lista.size() - 1;
        boolean encontrado = false;

        while (i >= 0 && !encontrado) {
            if (this.lista.get(i).getAnimal().equals(pAnimal)) {
                encontrado = true;
            } else {
                i--;
            }
        }
        return i;
    }
}
