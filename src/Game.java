import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public int numberOfRounds;
    public ArrayList<Player> players;
    public Store store = new Store();

    ArrayList<Horse> horseList = new ArrayList<>();
    ArrayList<Cow> cowList = new ArrayList<>();
    ArrayList<Pig> pigList = new ArrayList<>();
    ArrayList<Sheep> sheepList = new ArrayList<>();
    ArrayList<Llama> llamaList = new ArrayList<>();

    public Game(ArrayList<Player> players, int numberOfRounds){
        this.players = players;
        this.numberOfRounds = numberOfRounds;
        mainGame();
    }

    public void mainGame(){
        for(int i = 4; i <numberOfRounds; i++){
            for(Player player : players){
                System.out.println("It is your turn " + player.name + ". What would you like to do?" +
                        "\n[1] Buy animal" + "\n[2] Buy food" + "\n[3] Feed animal" + "\n[4] Mate animal" + "\n[5] Sell animal");
                int userChoice = 0;
                    try {
                        Scanner scanner = new Scanner(System.in);
                        userChoice = Integer.parseInt(scanner.next());
                        if (userChoice < 1 || userChoice > 5) {
                            System.out.println("You have to choose a number from the list.");
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

    public void buyAnimal(Player player){
        Animal myNewAnimal = store.createAnimal();
        player.addAnimal(myNewAnimal);
    }
    public void buyFood(Player player){
        Food myNewFood = store.deliverFood();
        if(!player.foods.contains(myNewFood)) {
            player.addFood(myNewFood);
        }
        else {
            var index = player.foods.indexOf(myNewFood);    // To get the index of where the specific food is located
            player.foods.set(index, myNewFood);     // Replace old index with new amount
        }
    }
    public void feedAnimal(Player player) {
        System.out.println("[ENTER THE TYPE OF FOOD YOU LIKE TO FEED YOUR ANIMALS]");
        for (var f : player.foods) {
            System.out.println("[" + f.getClass().getSimpleName() + "]");
        }
            try {
                var userChoiceOfFood = scanner.next();
                for (var a : player.animals) {
                    if (userChoiceOfFood.toUpperCase().equals("HAY")) {
                        if (a instanceof Horse || a instanceof Llama || a instanceof Sheep) {
                            player.feedHay(a);
                        }
                    }
                    if (userChoiceOfFood.toUpperCase().equals("GRASS")) {
                        if (a instanceof Cow) {
                            player.feedGrass(a);
                        }
                    }
                    if (userChoiceOfFood.toUpperCase().equals("GRAIN")) {
                        if (a instanceof Pig || a instanceof Horse)
                            player.feedGrain(a);
                    }
                }
            } catch (Exception e) {
                System.out.println("You gave the wrong input!");
            }
    }
    public void mateAnimal(Player player){
        for(var a : player.animals){
                // Add animal to specific Arraylist of same class.
                // NOTE: Check if both male and female is contained in list.
                // If yes, try to mate animals.
            if(a instanceof Horse){
                horseList.add((Horse)a);
            }
            if(a instanceof Cow){
                cowList.add((Cow)a);
            }
            if(a instanceof Pig){
                pigList.add((Pig)a);
            }
            if(a instanceof Sheep){
                sheepList.add((Sheep)a);
            }
            if(a instanceof Llama){
                llamaList.add((Llama)a);
            }
        }
    }
}
