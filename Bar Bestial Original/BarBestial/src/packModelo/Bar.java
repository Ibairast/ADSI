package packModelo;

public class Bar {
    private static Bar miBar;
    private ListaCartas lista;

    private Bar() {
        lista = new ListaCartas();
    }

    public static Bar getMiBar() {
        if (miBar == null) {
            miBar = new Bar();
        }
        return miBar;
    }

    public EnumColor calcularGanador() {
        EnumColor colorGanador;
        colorGanador = this.lista.obtenerColorMasNumeroso();
        if (colorGanador != null) {
            return colorGanador;
        } else { /* Empate en numero de cartas */
            colorGanador = this.lista.obtenerColorMenosFuerza();

            if (colorGanador != null) { /* Empate en fuerza de cartas */
                return colorGanador;
            } else {
                return null;
            }
        }
    }

    public int obtenerNumeroDeCartasColor(EnumColor pColor) {
        return this.lista.obtenerNumeroDeCartasColor(pColor);
    }

    public int obtenerFuerzaColor(EnumColor pColor) {
        return this.lista.obtenerFuerzaDeCartasColor(pColor);
    }

    public void vaciar() {
        this.lista.vaciar();
    }

    public void anadirCarta(Carta pCarta) {
        this.lista.anadirCarta(pCarta);
    }
}
