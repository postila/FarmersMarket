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
        for (int i = 1; i < numberOfRounds; i++) {
            for (Player player : players) {
                boolean option = false;
                int userChoice = 0;
                while(!option) {
                    System.out.println("It is your turn " + player.name + ". Make a move!" +
                            "\n[1] Buy animal" + "\n[2] Buy food" + "\n[3] Feed animal" + "\n[4] Mate animal" + "\n[5] Sell animal");

                    try {
                        userChoice = Integer.parseInt(SCANNER.next());
                    } catch (Exception ignore){}
                    if (userChoice < 1 || userChoice > 6) {
                        System.out.println("You have to choose a number from the list.");
                    }
                    else {
                        option = true;
                    }
                }
                if (userChoice != 3) {    // If user don't pick 3, animals will lose life value
                    player.notFeedingAnimal();
                    player.removeDeadAnimal();
                }
                switch (userChoice) {
                    case 1 -> buyAnimal(player);  // Creates a new animal and add to players list of animals
                    case 2 -> buyFood(player);    // Buy food and add to players list
                    case 3 ->{ feedAnimal(player);
                    player.showPlayerInfo();}// Increase animals life value
                    case 4 -> mateAnimal(player);
                    case 5 -> sellAnimal(player);
                    case 6 -> player.showPlayerInfo();
                }
            }
        }
    }

    public void buyAnimal(Player player) {
        //  Menu should be out here, and when player choose animal it should
        //  first check if player has enough money or not.
        store.createAnimal(player);
    }
    public void buyFood(Player player) {
        //  Menu should be out here, and when player choose animal it should
        //  first check if player has enough money or not.
        store.deliverFood(player);
    }
    public void feedAnimal (Player player) {
        int kilos = 0;
        Food userChoiceOfFood = null;
        int count = 0;
        for (var f : player.foods) {
            System.out.println("[" + ++count + "]  Food: " + f.getClass().getSimpleName().toUpperCase() +
                    "  \tAmount: " + f.getAmount());
        }
        do {
            try {
                var input = prompt("Enter number attached to food: ");
                userChoiceOfFood = player.foods.get(Integer.parseInt(input) - 1);
            } catch (Exception ignore) {
            }
            try {
                kilos = Integer.parseInt(prompt("Amount of kilos to feed your animals:"));
            } catch (Exception ignore) {
            }
        } while (userChoiceOfFood == null || kilos == 0);

        for (var a : player.animals) {
            if(userChoiceOfFood instanceof Hay){
                if(a instanceof Horse || a instanceof Llama || a instanceof Sheep) {
                    for (var i = 0; i < kilos; i++) {
                        if (a.health < 100 && userChoiceOfFood.getAmount() > 0) {
                            a.increaseHealth(1);
                            player.reduceFood(userChoiceOfFood, 1);
                        }
                    }
                }
            }
            if(userChoiceOfFood instanceof Grass) {
                if (a instanceof Cow || a instanceof Horse) {
                    for (var i = 0; i < kilos; i++) {
                        if (a.health < 100 && userChoiceOfFood.getAmount() > 0) {
                            a.increaseHealth(1);
                            player.reduceFood(userChoiceOfFood, 1);
                        }
                    }
                }
            }
            if(userChoiceOfFood instanceof Grain){
                if(a instanceof Pig || a instanceof Llama || a instanceof Sheep){
                    for(var i = 0; i < kilos; i++) {
                        if (a.health < 100 && userChoiceOfFood.getAmount() > 0) {
                            a.increaseHealth(1);
                            player.reduceFood(userChoiceOfFood, 1);
                        }
                    }
                }
            }
        }
    }
    public void mateAnimal (Player player){
        if(!player.animals.isEmpty()) { // check if player has any animal
            player.mateAnimal();
        }
        else {
            System.out.println("Oh no.. Not a possible move.\nYou don't have any animals, " + player.name + ".");
        }
    }
    public void sellAnimal (Player player){
        boolean sell = true;
        do {
            var count = 0;
            Animal animalToSell;
            for (var a : player.animals) {
                print("[" + ++count + "] " + a.name + " the " + a.getClass().getSimpleName().toLowerCase() +
                        " current health value: " + a.health);
            }
            while (true){
                try {
                    if(!player.animals.isEmpty()) {
                        var input = prompt("What animal from you list would you like to sell?");
                        animalToSell = player.animals.get(Integer.parseInt(input) - 1);
                        if (animalToSell != null) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    print("Chose an animal from your list, 1-" + count);
                }
            }
            System.out.println(animalToSell.getClass().getSimpleName() + " animal you wish to sell.");
            var sellingPrice = (animalToSell.getPrice() * (animalToSell.health / 100));
            print("Selling Price: " + (int) sellingPrice);
            player.money += sellingPrice;
            player.animals.remove(animalToSell);
            if(player.animals.isEmpty()){
                print("You don't have any animals left.1");
                sell = false;
            }
            var sellMore = prompt("\n\n[S] to sell another animal. \n[E] to exit.");
            if (sellMore.toUpperCase().equals("E")){
                sell = false;
            }
        } while(sell);
    }
    public String prompt(String question){
        System.out.println(question);
        return SCANNER.next();
    }
    public void print(String text){
        System.out.println(text);
    }
}


