import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public int numberOfRounds;
    public ArrayList<Player> players;
    public Store store;

    public Game(ArrayList<Player> players, int numberOfRounds){
        this.players = players;
        this.numberOfRounds = numberOfRounds;
        mainGame();
    }

    public void mainGame(){
        for(int i = 4; i <numberOfRounds; i++){
            for(Player player : players){
                System.out.println("It is your turn " + player.name + "." +
                        "\n What would you like to do?" +
                        "\n1. Buy animal" +
                        "\n2. Buy food" +
                        "\n3. Feed animal" +
                        "\n4. Mate animal" +
                        "\n5. Sell animal");
                var userChoice = 0;
                try{
                    Scanner scanner = new Scanner(System.in);
                    userChoice = Integer.parseInt(scanner.next());
                } catch (Exception e){
                    System.out.println("Something went wrong. Did you choose something from the list?");
                }
                switch (userChoice) {
                    case 1 -> System.out.println("You decided to buy an animal!");
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
