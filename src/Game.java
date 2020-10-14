import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {
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
                    player.increaseAnimalAge();
                    boolean option = false;
                    int userChoice = 0;
                    while (!option) {
                        clear();
                        print("\t== ROUND " + i + " ==");
                        print( player.niceName() + ", it's your turn.\nCurrent Balance: " + player.money +
                                " SEK.\nCurrent Animals: " + player.animals.size() +
                                "\n\n[1] Buy Animal" + "\n[2] Buy Food" + "\n[3] Feed Animal" +
                                "\n[4] Mate Animal" + "\n[5] Sell Animal" + "\n[6] Show " + player.name + " Info");
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
                        case 6 -> player.showPlayerInfo();
                    }
                    sleep(1000);
                }
            checkIfGameOver();
            if(i == numberOfRounds){
                clear();
                print("GAME IS OVER!\n\n");
                checkForWinner();
                break;
            }
            if (players.isEmpty()){
                clear();
                print("== GAME OVER ==\n   NO WINNER");
                return;     // If players list is empty, end game.
            }
        }
    }

    public void buyAnimal(Player player) {
        store.createAnimal(player);
    }
    public void buyFood(Player player) {
        store.deliverFood(player);
    }
    public void feedAnimal(Player player) {
        int kilos = 0;
        Food userChoiceOfFood = null;
        int count = 0;
        for (var f : player.foods) {
            System.out.println("[" + ++count + "]  Food: " + f.getClass().getSimpleName().toUpperCase() +
                    "  \tAmount: " + f.getAmount());
        }
        if(player.foods.isEmpty()){
            print("You don't have any food!");
            return;
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
            if (userChoiceOfFood instanceof Hay) {
                if (a instanceof Horse || a instanceof Llama || a instanceof Sheep) {
                    for (var i = 0; i < kilos; i++) {
                        if (a.health < 100 && userChoiceOfFood.getAmount() > 0) {
                            a.increaseHealth(1);
                            player.reduceFood(userChoiceOfFood, 1);
                        }
                    }
                }
            }
            if (userChoiceOfFood instanceof Grass) {
                if (a instanceof Cow || a instanceof Horse) {
                    for (var i = 0; i < kilos; i++) {
                        if (a.health < 100 && userChoiceOfFood.getAmount() > 0) {
                            a.increaseHealth(1);
                            player.reduceFood(userChoiceOfFood, 1);
                        }
                    }
                }
            }
            if (userChoiceOfFood instanceof Grain) {
                if (a instanceof Pig || a instanceof Llama || a instanceof Sheep) {
                    for (var i = 0; i < kilos; i++) {
                        if (a.health < 100 && userChoiceOfFood.getAmount() > 0) {
                            a.increaseHealth(1);
                            player.reduceFood(userChoiceOfFood, 1);
                        }
                    }
                }
            }
        }
    }
    public void mateAnimal(Player player) {
        if(player.animals.size() < 2){
            print("Oh no.. Not a possible move! You must have at least two animals!");
            sleep(1000);
        }
        else {
            player.mateAnimal();
        }
    }
    public void sellAnimal(Player player) {
        boolean sell = true;
        do {
            var count = 0;
            Animal animalToSell;
            for (var a : player.animals) {
                print("[" + ++count + "] " + a.name + " the " + a.getClass().getSimpleName().toLowerCase() +
                        " current health value: " + a.health);
            }
            while (true) {
                try {
                    if (!player.animals.isEmpty()) {
                        var input = prompt("What animal from you list would you like to sell?\n" +
                                "[E] to exit.");
                        if(input.toUpperCase().equals("E")){
                            print("So, you changed your mind!");
                            sleep(1000);
                            return;
                        }
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
            var priceLossDueToAge = (animalToSell.age * 0.02); // Animal lose 2% in value due to age
            var newPrice = animalToSell.getPrice() - (animalToSell.getPrice() * priceLossDueToAge);
            var sellingPrice = (newPrice * (animalToSell.health / 100));
            print("Selling Price: " + (int) sellingPrice);
            player.money += sellingPrice;
            player.animals.remove(animalToSell);
            if (player.animals.isEmpty()) {
                print("You don't have any animals left.");
                sell = false;
            }
            var sellMore = prompt("\n\n[S] to sell another animal. \n[E] to exit.");
            if (sellMore.toUpperCase().equals("E")) {
                sell = false;
            }
        } while (sell);
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
                print("GAME OVER " + players.get(i).name.toUpperCase() + "\nYou're out of the game!");
                sleep(3000);
                players.remove(i);
            }
        }
    }
    public void checkForWinner() {
        print("\t\t[ S C O R E  B O A R D ]");
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
            print(++count + ". " + players.get(i).name.toUpperCase() + "\t\t Farmers total worth: " + players.get(i).money);
        }
    }
    public void sleep(int ms){
        try{
            Thread.sleep(ms);
        } catch (Exception ignore){}
    }
}


