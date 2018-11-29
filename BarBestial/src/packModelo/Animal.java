package packModelo;

public abstract class Animal {
    protected int fuerza;
    protected String especie;

    protected abstract void hacerAnimalada();

    public int getFuerza(){
        return this.fuerza;
    }

    public String getEspecie(){
        return this.especie;
    }

    protected int obtenerMiPosicion() {    	
        Tablero tablero = Tablero.getMiTablero();
        return tablero.obtenerPosicionAnimal(this);
    }
}
