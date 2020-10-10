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
                animalChoice = Integer.parseInt(prompt("Buy animal or leave the store."));
            } catch (Exception ignore){}

            switch (animalChoice) {
                case 1 -> player.addAnimal(new Horse(setName(), setGender()));
                case 2 -> player.addAnimal(new Cow(setName(),setGender()));
                case 3 -> player.addAnimal(new Pig(setName(),setGender()));
                case 4 -> player.addAnimal(new Sheep(setName(),setGender()));
                case 5 -> player.addAnimal(new Llama(setName(),setGender()));
                case 6 -> {System.out.println("Come back soon, " + player.name + "!");
                exit = true;}
                default -> System.out.println("Choose an option between 1-6!");
            }
        }
    }
    public void deliverFood(Player player) {
        boolean exit = false;
        int foodChoice = 0;
        int kilos = 0;
        while (!exit){
            System.out.println("\t\t[ FOOD ] " +
                    "\n[1] HAY\t\t|  100 SEK/KG" +
                    "\n[2] GRASS\t|  150 SEK/KG" +
                    "\n[3] GRAIN\t|  200 SEK/KG" +
                    "\n[4] \tEXIT STORE");
        do {
            try {
                foodChoice = Integer.parseInt(prompt("What food would you like to buy today?"));
            } catch (Exception e) {
                print("ERROR : You have to chose a food option between 1-3.");
            }
            if (foodChoice >= 1 && foodChoice <= 3) {
                try {
                    kilos = Integer.parseInt(prompt("How many kilos would you like to get?"));
                } catch (Exception e) {
                    print("ERROR : Register amount of kilos with numbers.");
                }
            }
        } while (foodChoice == 0 || kilos == 0);
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
    private static void print(String text){
        System.out.println(text);
    }
    public static String setName(){     // Help method
        return prompt("[ ENTER A NAME ]");
    }
    public static String setGender(){   // Help method
        var input = "";
        while(true) {
            try {
                input = prompt("[ GENDER ]\n[F]  Female\n[M]  Male");
                if(input.toUpperCase().equals("F") || input.toUpperCase().equals("M")){
                    break;
                }
            } catch (Exception ignore) {
            }
        }
        return (input.toUpperCase().equals("F") ? "FEMALE" : "MALE");
    }
}
