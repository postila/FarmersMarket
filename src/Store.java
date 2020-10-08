import java.util.Scanner;

public class Store {
    private static final Scanner scanner = new Scanner(System.in);

    public void createAnimal(Player player){
        boolean exit = false;
        int animalChoice = 0;
        while(!exit) {
            System.out.println("\t[ ANIMALS FOR SALE ]" +
                    "\n[1] HORSE \t|  50.000 SEK" +
                    "\n[2]  COW \t|  12.000 SEK" +
                    "\n[3]  PIG \t|   1.500 SEK" +
                    "\n[4] SHEEP \t|   1.000 SEK" +
                    "\n[5] LLAMA \t|  10.000 SEK" +
                    "\n[6]\t\tEXIT STORE  ");
            try {
                animalChoice = Integer.parseInt(prompt("Chose an animal to buy or exit the store."));
            } catch (Exception ignore){}
            var name = "";
            var gender = "";
            if(animalChoice >= 1 && animalChoice <= 5){
                name = prompt("What would you like to name your animal?");
                gender = prompt("Chose gender, (female/male): ");
            }

            switch (animalChoice) {
                case 1 -> player.addAnimal(new Horse(name, gender));
                case 2 -> player.addAnimal(new Cow(name,gender));
                case 3 -> player.addAnimal(new Pig(name, gender));
                case 4 -> player.addAnimal(new Sheep(name, gender));
                case 5 -> player.addAnimal(new Llama(name, gender));
                case 6 -> {System.out.println("Come back soon, " + player.name + "!");
                exit = true;}
                default -> System.out.println("Choose an option between 1-6!");
            }
        }
    }
    public void deliverFood(Player player) {
        boolean exit = false;
        int foodChoice = 0;
        while (!exit){
            System.out.println("\t\t[ FOOD ] " +
                    "\n[1] HAY\t\t|  100 SEK/KG" +
                    "\n[2] GRASS\t|  150 SEK/KG" +
                    "\n[3] GRAIN\t|  200 SEK/KG" +
                    "\n[4] \tEXIT STORE");
        try {
            foodChoice = Integer.parseInt(prompt("What food would you like to buy today?"));
        } catch (Exception ignore) {}
        int kilos = 0;
        if (foodChoice >= 1 && foodChoice <= 3) {
            kilos = Integer.parseInt(prompt("How many kilos would you like to get?"));
        }
            switch (foodChoice) {
                case 1 -> player.addFood(new Hay(kilos));
                case 2 -> player.addFood(new Grass(kilos));
                case 3 -> player.addFood(new Grain(kilos));
                case 4 -> {System.out.println("Welcome back another time!");
                    exit = true;}
                default -> System.out.println("Choose an option between 1-4!");
            }
        }
    }

    private static String prompt(String question){
        System.out.println(question);
        return scanner.nextLine();
    }
}
