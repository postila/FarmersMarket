import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public int numberOfRounds;
    public ArrayList<Player> players;
    public Store store = new Store();
    public Animal animal;

    public Game(ArrayList<Player> players, int numberOfRounds){
        this.players = players;
        this.numberOfRounds = numberOfRounds;
        mainGame();
    }

    public void mainGame(){
        for(int i = 4; i <numberOfRounds; i++){
            for(Player player : players){
                System.out.println("It is your turn " + player.name + ". What would you like to do?" +
                        "\n[1] Buy animal" +
                        "\n[2] Buy food" +
                        "\n[3] Feed animal" +
                        "\n[4] Mate animal" +
                        "\n[5] Sell animal");
                int userChoice = 0;
                    try {
                        Scanner scanner = new Scanner(System.in);
                        userChoice = Integer.parseInt(scanner.next());
                        if (userChoice < 1 || userChoice > 5) {
                            System.out.println("You have to choose a number from the list.");
                            return;
                        }
                    } catch (Exception e) {
                        System.out.println("Something went wrong. Pick a number from the menu!");
                    }
                if(userChoice != 3){    // If user don't pick 3, animals will lose life value
                    player.notFeedingAnimal();
                    player.removeDeadAnimal();
                }
                switch (userChoice) {
                    case 1 -> {
                        while(true) {
                            buyAnimal(player);  // Creates a new animal and add to players list of animals
                            System.out.println("Buy another animal? (YES/NO)");
                            String yesNo = scanner.next();
                            if(yesNo.toUpperCase().equals("NO")){
                                break;
                            }
                        }
                    }
                    case 2 -> {
                        while (true) {
                            buyFood(player);    // Buy food and add to players list
                            System.out.println("Buy more food? (YES/NO)");
                            String yesNo = scanner.next();
                            if (yesNo.toUpperCase().equals("NO")) {
                                break;
                            }
                        }
                    }
                    case 3 ->{
                        feedAnimal(player);   // Increase animals life value
                        player.showPlayerInfo();
                    }
                    case 4 -> player.showPlayerInfo();
                }
            }
        }
    }

    public void buyAnimal(Player player){
        Animal myNewAnimal = store.createAnimal();
        player.addAnimal(myNewAnimal);
    }
    public void buyFood(Player player){
        Food myNewFood = store.deliverFood();
        player.addFood(myNewFood);
    }
    public void feedAnimal(Player player){
        System.out.println("[ENTER THE TYPE OF FOOD YOU LIKE TO FEED YOUR ANIMALS]");
        for(var f : player.foods){
            System.out.println("[" + f.getClass().getSimpleName() + "]");
        }
        try{
        var userChoiceOfFood = scanner.next();
        if(userChoiceOfFood.toUpperCase().equals("HAY")){
            for(var a : player.animals){
                if(a instanceof Horse || a instanceof Llama || a instanceof Sheep){
                    player.feedHay();
                }
            }
        }
        if(userChoiceOfFood.toUpperCase().equals("GRASS")){
            for(var a : player.animals){
                if(a instanceof Cow){
                    player.feedGrass();
                }
            }
        }
        if(userChoiceOfFood.toUpperCase().equals("GRAIN")){
            for(var a : player.animals){
                if(a instanceof Pig || a instanceof Horse || a instanceof Llama){
                    player.feedGrain();
                }
            }
        }
    } catch (Exception e){
            System.out.println("You gave the wrong input!");
        }
    }
    public void mateAnimal(Player player){
        player.mateAnimal();
    }
}
