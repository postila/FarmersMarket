import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public int numberOfRounds;
    public ArrayList<Player> players;
    public Store store = new Store();

    public Game(ArrayList<Player> players, int numberOfRounds){
        this.players = players;
        this.numberOfRounds = numberOfRounds;
        mainGame();
    }

    public void mainGame(){
        for(int i = 4; i <numberOfRounds; i++){
            for(Player player : players){
                System.out.println("It is your turn " + player.name + ".\n" +
                        "\nWhat would you like to do?" +
                        "\n1. Buy animal" +
                        "\n2. Buy food" +
                        "\n3. Feed animal" +
                        "\n4. Mate animal" +
                        "\n5. Sell animal");
                int userChoice = 0;
                try{
                    Scanner scanner = new Scanner(System.in);
                    userChoice = Integer.parseInt(scanner.next());
                    if(userChoice < 1 || userChoice > 5){
                        System.out.println("You have to choose a number from the list.");
                        return;
                    }
                } catch (Exception e){
                    System.out.println("Something went wrong. Pick a number from the menu!");
                }
                switch (userChoice) {
                    case 1 -> {
                        System.out.println("You decided to buy an animal!");
                        buyAnimal(player);
                        player.showPlayerInfo();
                    }
                    case 2 -> System.out.println("Okay, so you want to buy some food.");
                    case 3 -> System.out.println("Dinner time!");
                }
            }
        }
    }

    public void buyAnimal(Player player){
        player.addAnimal(store.createAnimal());
    }

}
