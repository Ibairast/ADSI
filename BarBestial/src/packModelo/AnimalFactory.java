package packModelo;

public class AnimalFactory {
    private static AnimalFactory miAnimalFactory = null;

    private AnimalFactory(){}

    public static AnimalFactory getMiAnimalFactory() {
        if(miAnimalFactory == null){
            miAnimalFactory = new AnimalFactory();
        }
        return miAnimalFactory;
    }

    public Animal crearAnimal(String pAnimal) {
        Animal miAnimal = null;

        switch (pAnimal) {
            case "Camaleon":
                miAnimal = new Camaleon();
                break;
            case "Canguro":
                miAnimal = new Canguro();
                break;
            case "Cebra":
                miAnimal = new Cebra();
                break;
            case "Cocodrilo":
                miAnimal = new Cocodrilo();
                break;
            case "Foca":
                miAnimal = new Foca();
                break;
            case "Hipopotamo":
                miAnimal = new Hipopotamo();
                break;
            case "Jirafa":
                miAnimal = new Jirafa();
                break;
            case "Leon":
                miAnimal = new Leon();
                break;
            case "Loro":
                miAnimal = new Loro();
                break;
            case "Mofeta":
                miAnimal = new Mofeta();
                break;
            case "Mono":
                miAnimal = new Mono();
                break;
            case "Serpiente":
                miAnimal = new Serpiente();
                break;
        }
        return miAnimal;
    }
}
