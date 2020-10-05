import java.util.Scanner;

public class Store {

    public Animal createAnimal(){
        Scanner scanner = new Scanner(System.in);
            System.out.println("\t[CHOOSE AN ANIMAL]" +
                    "\n[1] HORSE \t|  50.000 SEK" +
                    "\n[2]  COW \t|  12.000 SEK" +
                    "\n[3]  PIG \t|   1.500 SEK" +
                    "\n[4] SHEEP \t|   1.000 SEK" +
                    "\n[5] LLAMA \t|  10.000 SEK" );
            var animalChoice = scanner.nextInt();
            System.out.println("Choose a name:");
            var name = scanner.nextLine();
            scanner.nextLine(); // Added so scanner do not bug
            System.out.println("Choose a gender, (female/male): ");
            var gender = scanner.next();

            switch (animalChoice) {
                case 1 -> {
                    return new Horse(name, gender);
                }
                case 2 -> {
                    return new Cow(name, gender);
                }
                case 3 -> {
                    return new Pig(name, gender);
                }
                case 4 -> {
                    return new Sheep(name, gender);
                }
                case 5 -> {
                    return new Llama(name, gender);
                }
                default -> System.out.println("You did not chose an animal alternative.");
            }
        return null;
    }
    public Food deliverFood(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What food would you like to buy? \n1. Hay\n2. Grass\n3. Grain");
        int foodChoice = Integer.parseInt(scanner.next());
            try {
                switch (foodChoice) {
                    case 1 -> {
                        return new Hay();
                    }
                    case 2 -> {
                        return new Grass();
                    }
                    case 3 -> {
                        return new Grain();
                    }
                }
            } catch (Exception e) {
                System.out.println("You did not choose one of the following food alternatives.");
            }
        return null;
    }
}
