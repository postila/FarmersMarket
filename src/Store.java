import java.util.Scanner;

public class Store {
    public Animal animal;
    public Food food;
    Scanner scanner = new Scanner(System.in);

    public Animal createAnimal(){
        System.out.println("What kind of animal would you like to create?" +
                "\n1. Horse" +
                "\n2. Cow" +
                "\n3. Pig" +
                "\n4. Sheep" +
                "\n5. Llama");
        var animalChoice = scanner.nextInt();
        System.out.println("Choose a name:");
        var name = scanner.next();
        System.out.println("Choose a gender, (female/male): ");
        var gender = scanner.next();

        switch (animalChoice) {
            case 1 -> {
                return new Horse(name,gender);
            }
            case 2 -> {
                return new Cow(name,gender);
            }
            case 3 -> {
                return new Pig(name,gender);
            }
            case 4 -> {
                return new Sheep(name,gender);
            }
            case 5 -> {
                return new Llama(name,gender);
            }
        }
        return null;
    }
}
