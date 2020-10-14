import java.io.Serializable;
import java.util.Scanner;

public class Store implements Serializable {
    private static final Scanner scanner = new Scanner(System.in);

    public void createAnimal(Player player){
        boolean exit = false;
        while(!exit) {
            clear();
            int animalChoice = 0;
            System.out.println("\t[ ANIMALS FOR SALE ]" +
                    "\n[1] HORSE \t|  50.000 SEK" +
                    "\n[2]  COW \t|  12.000 SEK" +
                    "\n[3]  PIG \t|   1.500 SEK" +
                    "\n[4] SHEEP \t|   1.000 SEK" +
                    "\n[5] LLAMA \t|  10.000 SEK" +
                    "\n[6]\t\tEXIT STORE  ");
            try {
                animalChoice = Integer.parseInt(prompt("\nBuy animal or enter [6] to leave the store, " + player.name + "."));
            } catch (Exception ignore){}

            switch (animalChoice) {
                case 1 -> { player.addAnimal(new Horse(setName(), setGender()));
                print("Congratulations, you just bought a horse!");
                sleep(1000);}
                case 2 -> { player.addAnimal(new Cow(setName(),setGender()));
                print("Congratulations, you just bought a new cow!");
                sleep(1000);}
                case 3 -> { player.addAnimal(new Pig(setName(), setGender()));
                print("Congratulations, you just bought a new pig!");
                sleep(1000);}
                case 4 -> { player.addAnimal(new Sheep(setName(), setGender()));
                print("Congratulations, you just bought a new sheep!");
                sleep(1000);}
                case 5 -> {player.addAnimal(new Llama(setName(),setGender()));
                print("Congratulations, you just bought a new llama!");
                sleep(1000);}
                case 6 -> {System.out.println("Come back soon, " + player.name + "!");
                exit = true;}
                default -> {System.out.println("ERROR : Choose an option between 1-6!");
                sleep(1000);}
            }
        }
    }
    public void deliverFood(Player player) {
        boolean exit = false;
        int foodChoice = 0;
        int kilos = 0;
        while (!exit){
            clear();
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
                sleep(1000);
            }
            if (foodChoice >= 1 && foodChoice <= 3) {
                try {
                    kilos = Integer.parseInt(prompt("How many kilos would you like to get?"));
                } catch (Exception e) {
                    print("ERROR : Register amount of kilos with numbers.");
                    sleep(1000);
                }
            }
        } while (foodChoice == 0 || kilos == 0);
            switch (foodChoice) {
                case 1 -> player.addFood(new Hay(kilos));
                case 2 -> player.addFood(new Grass(kilos));
                case 3 -> player.addFood(new Grain(kilos));
                case 4 -> {System.out.println("Welcome back another time!");
                    exit = true;}
                default -> {System.out.println("Choose an option between 1-4!");
                sleep(1000);}
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
    public void clear(){
        System.out.println("\n".repeat(50));
    }
    public void sleep(int ms){
        try{
            Thread.sleep(ms);
        } catch (Exception ignore){}
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
