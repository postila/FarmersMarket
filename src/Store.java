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
        try {
            int foodChoice = Integer.parseInt(scanner.next());
                switch (foodChoice) {
                    case 1 -> {
                        System.out.println("How many kilos if hay do you want to buy?");
                        var kilos = Integer.parseInt(scanner.next());

                        return new Hay(kilos);
                    }
                    case 2 -> {
                        System.out.println("How many kilos if grass do you want to buy?");
                        var kilos = Integer.parseInt(scanner.next());
                        return new Grass(kilos);
                    }
                    case 3 -> {
                        System.out.println("How many kilos if grain do you want to buy?");
                        var kilos = Integer.parseInt(scanner.next());
                        return new Grain(kilos);
                    }
                }
            } catch (Exception e) {
                System.out.println("You did not choose one of the following food alternatives.");
            }
        return null;
    }
}
