import java.util.*;

public class Game {
    private static final Scanner SCANNER = new Scanner(System.in);
    public int numberOfRounds;
    public ArrayList<Player> players;
    private final Store store = new Store();

    public Game(ArrayList<Player> players, int numberOfRounds) {
        this.players = players;
        this.numberOfRounds = numberOfRounds;
        mainGame();
    }

    public void mainGame() {
        for (int i = 1; i <= numberOfRounds; i++) {
                for (Player player : players) {
                    player.increaseAnimalAge(); // Make players animal older each round
                    player.animalsGetSick();    // 20 % chance that an animal gets sick
                    boolean option = false;
                    int userChoice = 0;
                    while (!option) {
                        clear();
                        print(" ==================== ROUND " + i + " ====================");
                        print("\n\t\t\t" + player.name.toUpperCase() + " IT IS YOUR TURN!");
                        player.showPlayerInfo();
                        print("\n ================= FARMERS  MENU =================" +
                                "\n\t [1] BUY ANIMAL  \t  [4] MATE ANIMAL" +
                                "\n\t [2] BUY FOOD    \t  [5] SELL ANIMAL" +
                                "\n\t [3] FEED ANIMAL \t  [6] VISIT THE VET" +
                                "\n =================================================");
                        try {
                            userChoice = Integer.parseInt(SCANNER.next());
                        } catch (Exception ignore) {
                        }
                        if (userChoice < 1 || userChoice > 7) {
                            System.out.println("You have to choose a number from the list.");
                        } else {
                            option = true;
                        }
                    }
                    if (userChoice != 6 && !player.sickAnimals.isEmpty()){
                        player.removeSickAnimals();
                    }
                    if (userChoice != 3) {    // If user don't pick 3, animals will lose life value
                        player.notFeedingAnimal();
                        player.removeDeadAnimal();
                    }
                    switch (userChoice) {
                        case 1 -> buyAnimal(player);  // Creates a new animal and add to players list of animals
                        case 2 -> buyFood(player);    // Buy food and add to players list
                        case 3 -> feedAnimal(player);   // Increase animals life value
                        case 4 -> mateAnimal(player);
                        case 5 -> sellAnimal(player);
                        case 6 -> healAnimal(player);
                    }
                    sleep(1000);
                }
            checkIfGameOver();
            if(i == numberOfRounds){
                clear();
                print(" =================  G A M E   O V E R  =================\n");
                checkForWinner();
                break;
            }
            if (players.isEmpty()){
                clear();
                print("\n =================  G A M E  O V E R  ================= " +
                        "\n    NO WINNER THIS TIME - BETTER LUCK NEXT TIME");
                return;     // If list of players is empty, end game.
            }
        }
    }

    public void buyAnimal(Player player) {
        clear();
        store.createAnimal(player);
    }
    public void buyFood(Player player) {
        clear();
        store.deliverFood(player);
    }
    public void feedAnimal(Player player) {
        while (true) {
            clear();
            int kilos = 0;
            Food userChoiceOfFood = null;
            int count = 0;
            print(" ==================  TIME TO FEED  ================== ");
            for (var f : player.foods) {
                System.out.println("[" + ++count + "]  Food: " + f.getClass().getSimpleName().toUpperCase() +
                        "  \tAmount: " + f.getAmount());
            }
            if (player.foods.isEmpty()) {
                print("\n ======================  NOTICE  ====================== " +
                        "\n NO FOOD REGISTERED ");
                sleep(1000);
                return;
            }
            do {
                try {
                    var input = prompt("-".repeat(56) +
                            "\nWhat food would you like to feed." +
                            "\n[E] to EXIT.");
                    if(input.toUpperCase().equals("E")){
                        print("\n NO MORE FEEDING");
                        return;
                    }
                    userChoiceOfFood = player.foods.get(Integer.parseInt(input) - 1);
                } catch (Exception ignore) {
                }
                try {
                    kilos = Integer.parseInt(prompt("-".repeat(56) +
                            "\nAmount of kilos to feed your animals:"));
                } catch (Exception ignore) {
                }
            } while (userChoiceOfFood == null || kilos == 0);

            if (userChoiceOfFood instanceof Hay) {
                print(".".repeat(56) +
                        "\n\t HORSE \t|\t LLAMA \t|\t SHEEP");
                for (var a : player.animals) {
                    if (a instanceof Horse || a instanceof Llama || a instanceof Sheep) {
                        for (var i = 0; i < kilos; i++) {
                            if (a.health < 100 && userChoiceOfFood.getAmount() > 0) {
                                a.increaseHealth(1);
                                player.reduceFood(userChoiceOfFood, 1);
                            }
                        }
                    } else {
                        a.decreaseHealth();
                        a.animalDied();
                    }
                }
            }
                if (userChoiceOfFood instanceof Grass) {
                    print("-".repeat(56) +
                            "\n\t HORSE \t|\t COW ");
                    for (var a : player.animals) {
                        if (a instanceof Cow || a instanceof Horse) {
                            for (var i = 0; i < kilos; i++) {
                                if (a.health < 100 && userChoiceOfFood.getAmount() > 0) {
                                    a.increaseHealth(1);
                                    player.reduceFood(userChoiceOfFood, 1);
                                }
                            }
                        } else {
                            a.decreaseHealth();
                            a.animalDied();
                        }
                    }
                }
                if (userChoiceOfFood instanceof Grain) {
                    print("-".repeat(56) +
                            "\nANIMALS ABLE TO FEED:\t LLAMA \t|\t SHEEP \t|\t PIG");
                    for (var a : player.animals) {
                        if (a instanceof Pig || a instanceof Llama || a instanceof Sheep) {
                            for (var i = 0; i < kilos; i++) {
                                if (a.health < 100 && userChoiceOfFood.getAmount() > 0) {
                                    a.increaseHealth(1);
                                    player.reduceFood(userChoiceOfFood, 1);
                                }
                            }
                        }
                        else {
                            a.decreaseHealth();
                            a.animalDied();
                    }
                }
            }
        }
    }
    public void mateAnimal(Player player) {
        clear();
        if(player.animals.size() < 2){
            print("\n" + " ======================  NOTICE  ====================== " +
                    "\n MINIMUM ANIMALS REQUIRED: 2");
            sleep(1000);
        }
        else {
            player.mateAnimal();
        }
    }
    public void sellAnimal(Player player) {
        clear();
        boolean sell = true;
        do {
            var count = 0;
            Animal animalToSell;
            double sellingPrice = 0.0;
            print(" ================== SELL YOUR ANIMAL ================== " +
                    "\n  NOTICE!  Price is based on animals health percentage." +
                    "\n  And each animal year will cause price reduction by 2%\n");
            for (var a : player.animals) {
                var priceLossDueToAge = (a.age * 0.02); // Animal lose 2% in value due to age
                var newPrice = a.getPrice() - (a.getPrice() * priceLossDueToAge);
                sellingPrice = (newPrice * (a.health / 100));
                print(++count + ".\t" + a.name.toUpperCase() + "\n\tFinal Price: " + (int)sellingPrice + " SEK");
            }
            while (true) {
                try {
                    if (!player.animals.isEmpty()) {
                        var input = prompt("\n" + "-".repeat(56) +
                                "\nWhat animal from you list would you like to sell?\n" +
                                "[E] to exit.\n" +
                                "-".repeat(56));
                        if(input.toUpperCase().equals("E")){
                            print("-".repeat(56) +
                                    "\n\t WELCOME BACK ANOTHER TIME ");
                            sleep(1000);
                            return;
                        }
                        animalToSell = player.animals.get(Integer.parseInt(input) - 1);
                        if (animalToSell != null) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    print("-".repeat(56)+
                            "\nChoose an animal from your list, 1-" + count);
                }
            }
            player.money += sellingPrice;
            player.animals.remove(animalToSell);
            if (player.animals.isEmpty()) {
                print(" ================= NO MORE ANIMALS ================= ");
                sell = false;
            }
        } while (sell);
    }
    public void healAnimal(Player player){
        clear();
        boolean heal = true;
        do {
            int count = 0;
            Animal animalToHeal;
            var priceToHeal = 0.0;
            print(" ============ WELCOME TO THE VETERINARIAN'S ============ \n");
            for (var a : player.sickAnimals) {
                priceToHeal = (a.getPrice() * 0.2);
                print("[" + ++count + "]\t" + a.animalName() + " the " + a.getClass().getSimpleName() +
                        "\n\tHealth: " + a.health + "\tVeterinarian Costs: " + (int) priceToHeal);
            }
            while(true) {
                try {
                    var input = prompt("\n"+ "-".repeat(56) +
                            "\nWhich animal would you like to give a treatment?" +
                            "\n[E]  to EXIT" +
                            "\n"+ "-".repeat(56) );
                    if (input.toUpperCase().equals("E")) {
                        print("-".repeat(56)  +
                                "\nWe respect your decision!\nNo treatment will be done.");
                        player.removeSickAnimals();
                        sleep(1000);
                        return;
                    }
                    animalToHeal = player.sickAnimals.get(Integer.parseInt(input) - 1);
                    if (animalToHeal != null) {
                        break;
                    }
                } catch (Exception e) {
                    print("-".repeat(56) +
                            "\nChoose an animal 1-" + count + " to heal.");
                }
            }
            if(player.money >= priceToHeal){
                player.money -= priceToHeal;
            } else {
                print("=======================  NOTICE  ======================" +
                        "\n    YOU DON'T HAVE ENOUGH MONEY FOR A TREATMENT");
                player.sickAnimals.remove(animalToHeal);
                player.animals.remove(animalToHeal);
                sleep(1000);
                break;
            }

            animalToHeal.healAnimal();
            if(animalToHeal.sick) {
                print("-".repeat(56)  +
                        "\n I'm sorry, your " + animalToHeal.getClass().getSimpleName().toLowerCase() + " did not make it.");
                player.sickAnimals.remove(animalToHeal);
                player.animals.remove(animalToHeal);
            }
            else{
                player.sickAnimals.remove(animalToHeal);
            }
            player.money -= priceToHeal;
            if(player.sickAnimals.isEmpty()){
                heal = false;
            }
        }while (heal);
    }
    public String prompt(String question) {
        System.out.println(question);
        return SCANNER.next();
    }
    public void print(String text) {
        System.out.println(text);
    }
    public void clear(){
        System.out.println("\n".repeat(50));
    }
    public void checkIfGameOver() {
        clear();
        for(var i = players.size() -1; i >= 0; i--){
            if (players.get(i).money <= 0 && players.get(i).animals.isEmpty()) {
                print(" ================== GAME OVER " + players.get(i).name.toUpperCase() + " ================== ");
                sleep(3000);
                players.remove(i);
            }
        }
    }
    public void checkForWinner() {
        print(" ===================== SCORE BOARD ===================== ");
        if (!players.isEmpty()) {
            for (var player : players) {
                for (var animal : player.animals) {
                    var worthLeft = animal.price * (animal.health / 100);   // Calculate the worth of players animals
                    player.money += worthLeft;  // Adds animals worth to players money
                }
            }
        }
        int count = 0;
        players.sort(new PlayerScoreBoard());   // Sorts players list of money from low to high
        for(var i = players.size()-1; i >= 0; i--){     // Loop backwards to get the player with most money first
            print(++count + ". " + players.get(i).name.toUpperCase() + "\t\t FINAL FARM WORTH: " + players.get(i).money);
        }
    }
    public void sleep(int ms){
        try{
            Thread.sleep(ms);
        } catch (Exception ignore){}
    }
}


