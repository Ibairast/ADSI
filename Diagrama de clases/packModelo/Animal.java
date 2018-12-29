package packModelo;

public abstract class Animal {

	protected int fuerza;
	protected String nombreAnimal;

	protected abstract void hacerAnimalada();

	public int getFuerza() {
		return this.fuerza;
	}

	public String getNombre() {
		// TODO - implement Animal.getNombre
		throw new UnsupportedOperationException();
	}

	public int posicion() {
		// TODO - implement Animal.posicion
		throw new UnsupportedOperationException();
	}

}