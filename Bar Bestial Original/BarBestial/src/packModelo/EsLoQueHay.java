package packModelo;

public class EsLoQueHay {
    private static EsLoQueHay miEsLoQueHay;
    private ListaCartas lista;

    private EsLoQueHay() {
        lista = new ListaCartas();
    }

    public static EsLoQueHay getMiEsLoQueHay() {
        if (miEsLoQueHay == null) {
            miEsLoQueHay = new EsLoQueHay();
        }
        return miEsLoQueHay;
    }

    public void anadirCarta(Carta pCarta) {
        this.lista.anadirCarta(pCarta);
    }
    
    public void vaciar() {
        this.lista.vaciar();
    }
}
