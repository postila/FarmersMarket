import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public int numberOfRounds;
    public ArrayList<Player> players;
    public Store store = new Store();

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

                    userChoice = Integer.parseInt(scanner.next());
                    if (userChoice < 1 || userChoice > 5) {
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
                    case 3 -> {
                        feedAnimal(player);   // Increase animals life value
                        player.showPlayerInfo();
                    }
                    case 4 -> mateAnimal(player);
                    case 5 -> player.showPlayerInfo();
                }
            }
        }
    }

    public void buyAnimal(Player player) {
        store.createAnimal(player);
    }
    public void buyFood(Player player) {
        store.deliverFood(player);
    }
    public void feedAnimal (Player player){
        int kilos = 0;
        int userChoiceOfFood = 0;

            for (var f : player.foods) {
                System.out.println("[" + f.getClass().getSimpleName() + "]");
            }
            try {
                userChoiceOfFood = Integer.parseInt(prompt("Choose what type of food you would like to feed: "));
                kilos = Integer.parseInt(prompt("How many kilos?"));
            } catch (Exception e) {
                System.out.println("You gave the wrong input!");
            }
                for (var a : player.animals) {
                    if (userChoiceOfFood == 1) {
                        if (a instanceof Horse || a instanceof Llama || a instanceof Sheep) {
                            player.feedHay(a, kilos);
                        }
                    }
                    if (userChoiceOfFood == 2) {
                        if (a instanceof Cow) {
                            player.feedGrass(a, kilos);
                        }
                    }
                    if (userChoiceOfFood == 3) {
                        if (a instanceof Pig || a instanceof Horse)
                            player.feedGrain(a, kilos);
                    }
                }
        }
    public void mateAnimal (Player player){ }
    public String prompt(String question){
        System.out.println(question);
        return scanner.next();
    }
}


