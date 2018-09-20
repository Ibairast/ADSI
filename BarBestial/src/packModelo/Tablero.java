package packModelo;

import java.util.Observable;

public class Tablero extends Observable {
    private static Tablero miTablero;
    private ListaCartas cola;

    private Tablero() {
        cola = new ListaCartas();
    }

    public static Tablero getMiTablero() {
        if (miTablero == null) {
            miTablero = new Tablero();
        }
        return miTablero;
    }

    public void anadirALaCola(Carta pCarta) {
        this.cola.anadirCarta(pCarta);
        this.notificar(this.obtenerInformacionCartas());
    }

    public void moverAlPrincipio(Carta pCarta) {
        this.cola.moverAlPrincipio(pCarta);
        this.notificar(this.obtenerInformacionCartas());
    }

    public void moverCartaAPosicion(int pPos, Carta pCarta) {
        this.cola.moverCartaAPosicion(pPos, pCarta);
        this.notificar(this.obtenerInformacionCartas());
    }

    public void sacarDeLaCola(Carta pCarta) {
        this.cola.sacarCarta(pCarta);
        
        EsLoQueHay e = EsLoQueHay.getMiEsLoQueHay();
        e.anadirCarta(pCarta);
        
        this.notificar(this.obtenerInformacionCartas());
    }

    public Carta obtenerCartaEnPosicion(int pPos) {
        return this.cola.obtenerCartaEnPosicion(pPos);
    }

    public Carta obtenerUltimaCarta() {
        return this.cola.obtenerUltimaCarta();
    }

    public Carta obtenerCartaAleatoria() {
        return this.cola.obtenerCartaAleatoria();
    }

    public void revisarCola() {
        if (this.hayCincoCartas()) {
            this.abrirPuertaCielo();
            this.provocarPatada();
            this.notificar(this.obtenerInformacionCartas());
        }
    }

    public void provocarPatada() {
        Carta c;
        EsLoQueHay e = EsLoQueHay.getMiEsLoQueHay();

        c = this.obtenerUltimaCarta();
        this.sacarDeLaCola(c);
        e.anadirCarta(c);
    }

    public void vaciar() {
        this.cola.vaciar();
    }

    public void hacerUltimaAnimalada() {
        Carta carta = this.obtenerUltimaCarta();
        carta.hacerAnimalada();
    }

    public int obtenerNumeroDeAnimalesDeEspecie(String pEspecie) {
        return this.cola.obtenerNumeroDeAnimalesDeEspecie(pEspecie);
    }

    public void sustituirCartaEnPosicion(int pPos, Carta pCarta) {
        this.cola.sustituirCartaEnPosicion(pPos, pCarta);
        this.notificar(this.obtenerInformacionCartas());
    }

    public void ordenarPorFuerza() {
        this.cola.ordenarPorFuerza();
        this.notificar(this.obtenerInformacionCartas());
    }

    public void intercambiarCartas(int pPos1, int pPos2) {
        this.cola.intercambiarCartas(pPos1, pPos2);
        this.notificar(this.obtenerInformacionCartas());
    }

    public void sacarTodasLasCartasDeEspecie(String pEspecie) {
        this.cola.sacarTodasLasCartasDeEspecie(pEspecie);
        this.notificar(this.obtenerInformacionCartas());
    }

    public void invertirCola() {
        this.cola.invertir();
        this.notificar(this.obtenerInformacionCartas());
    }

    public String obtenerEspecieMasFuerteDeLaCola() {
        return this.cola.obtenerEspecieMasFuerte();
    }

    public String obtenerEspeciesDeAnimalesEnLaCola() {
        return this.cola.obtenerEspeciesDeAnimales();
    }

    public void hacerAnimaladasRecurrentes() {
        this.cola.hacerAnimaladasRecurrentes();
    }

    public int obtenerNumeroDeCartas() {
        return this.cola.obtenerNumeroDeCartas();
    }

    public int obtenerPosicionAnimal(Animal pAnimal) {
        return this.cola.obtenerPosicionAnimal(pAnimal);
    }

    public void sustituirCartaEnUltimaPosicion(Carta pCarta) {
        this.cola.sustituirCartaEnUltimaPosicion(pCarta);
        this.notificar(this.obtenerInformacionCartas());
    }

    private void notificar(String pInformacion) {
        super.setChanged();
        super.notifyObservers(pInformacion);
    }

    private String obtenerInformacionCartas() {
        return "tablero-" + this.cola.obtenerInformacionCartas();
    }

    private boolean hayCincoCartas() {
        return this.cola.hayCincoCartas();
    }

    private void abrirPuertaCielo() {
        Carta c;
        Bar b = Bar.getMiBar();

        for (int i = 0; i < 2; i++) {
            c = this.obtenerCartaEnPosicion(0);
            this.sacarDeLaCola(c);
            b.anadirCarta(c);
        }
    }
}
