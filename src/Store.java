import java.util.Scanner;

public class Store {
    private static final Scanner scanner = new Scanner(System.in);

    public void createAnimal(Player player){
        boolean exit = false;
        while(!exit) {
            int animalChoice = 0;
            print("\n".repeat(10) + "-".repeat(56) +
                    "\n\t\t\tWELCOME TO FARMERS MARKETS\n" +
                    "-".repeat(56) +
                    "\n    ANIMALS        PRICE       MAX AGE" +
                    "\n[1]  HORSE \t|   50.000 SEK\t|   20 YRS" +
                    "\n[2]   COW  \t|   12.000 SEK\t|   10 YRS" +
                    "\n[3]   PIG  \t|    1.500 SEK\t|    8 YRS" +
                    "\n[4]  SHEEP \t|    1.000 SEK\t|   10 YRS" +
                    "\n[5]  LLAMA \t|   10.000 SEK\t|   12 YRS" +
                    "\n\n[6]\t EXIT STORE ");
            try {
                animalChoice = Integer.parseInt(prompt("-".repeat(56) +
                        "\nWhat animal would you like to buy today " + player.niceName() + "?\n" +
                        "-".repeat(56)));
            } catch (Exception ignore){}

            switch (animalChoice) {
                case 1 -> { player.addAnimal(new Horse(setName(), setGender()));
                print(" Congratulations, you bought a horse!");
                sleep(1000);}
                case 2 -> { player.addAnimal(new Cow(setName(),setGender()));
                print(" Congratulations, you bought a cow!");
                sleep(1000);}
                case 3 -> { player.addAnimal(new Pig(setName(), setGender()));
                print(" Congratulations, you bought a pig!");
                sleep(1000);}
                case 4 -> { player.addAnimal(new Sheep(setName(), setGender()));
                print(" Congratulations, you bought a sheep!");
                sleep(1000);}
                case 5 -> {player.addAnimal(new Llama(setName(),setGender()));
                print(" Congratulations, you bought a llama!");
                sleep(1000);}
                case 6 -> {print("-".repeat(56) +
                        "\n\t\tWELCOME BACK ANOTHER TIME, " + player.name + "!");
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
            print("\n".repeat(10) + "-".repeat(56) +
                    "\n\t\t\tFARMERS MARKETS FOOD COURT  \n" +
                    "-".repeat(56) +
                    "\n\t FOODS     PRICE           ANIMALS" +
                    "\n[1]\t HAY\t|  100 SEK/KG\t|  HORSE  LLAMA  SHEEP" +
                    "\n[2]\t GRASS\t|  150 SEK/KG\t|  HORSE  COW" +
                    "\n[3]\t GRAIN\t|  200 SEK/KG\t|  LLAMA  SHEEP  PIG" +
                    "\n\n[4]\tEXIT STORE");
        do {
            try {
                foodChoice = Integer.parseInt(prompt("-".repeat(56) +
                        "\nWhat food would you like to buy today " + player.niceName() + "?\n" +
                        "-".repeat(56)));
                if(foodChoice == 4){
                    break;
                }
            } catch (Exception e) {
                print("ERROR : You have to chose a food option between 1-3.");
                sleep(1000);
            }
            if (foodChoice >= 1 && foodChoice <= 3) {
                try {
                    kilos = Integer.parseInt(prompt("-".repeat(56) +
                            "\nHow many kilos would you like to get?\n" +
                            "-".repeat(56)));
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
                case 4 -> {System.out.println("-".repeat(56) +
                        "\n\t\tWELCOME BACK ANOTHER TIME!");
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
        return prompt("\t".repeat(3) + "[ GIVE YOUR ANIMAL A NAME ]");
    }
    public static String setGender(){   // Help method
        var input = "";
        while(true) {
            try {
                input = prompt("\t".repeat(3) + "[ CHOOSE GENDER ]\n[F]  Female\n[M]  Male");
                if(input.toUpperCase().equals("F") || input.toUpperCase().equals("M")){
                    break;
                }
            } catch (Exception ignore) {
            }
        }
        return (input.toUpperCase().equals("F") ? "FEMALE" : "MALE");
    }
}
