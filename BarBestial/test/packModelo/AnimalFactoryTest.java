package packModelo;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalFactoryTest {

    @Test
    public void getMiAnimalFactory() {
        assertNotNull(AnimalFactory.getMiAnimalFactory());
    }

    @Test
    public void crearAnimal() {
        assertEquals(AnimalFactory.getMiAnimalFactory().crearAnimal("Camaleon").getClass(), Camaleon.class);
        assertEquals(AnimalFactory.getMiAnimalFactory().crearAnimal("Hipopotamo").getClass(), Hipopotamo.class);
        assertEquals(AnimalFactory.getMiAnimalFactory().crearAnimal("Leon").getClass(), Leon.class);
        assertEquals(AnimalFactory.getMiAnimalFactory().crearAnimal("Serpiente").getClass(), Serpiente.class);
    }
}