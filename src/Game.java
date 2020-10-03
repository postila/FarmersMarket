import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public int numberOfRounds;
    public ArrayList<Player> players;
    public Animal animal;

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
                if(userChoice != 3){    // If user don't pick 3, animals will lose life value
                    for(var a : player.animals){
                        a.decreaseHealth();
                        a.changeHealth();
                    }
                }
                switch (userChoice) {
                    case 1 -> buyAnimal(player);// Creates a new animal and add to players list of animals
                    case 2 -> buyFood(player);    // Buy food and add to players list
                    case 3 -> feedAnimal(player);   // Increase animals life value
                }
            }
        }
    }

    public void buyAnimal(Player player){
        Animal myNewAnimal = Store.createAnimal();
        player.addAnimal(myNewAnimal);
    }
    public void buyFood(Player player){
        Food myNewFood = Store.deliverFood();
        player.addFood(myNewFood);
    }
    public void feedAnimal(Player player){
        player.feedAnimal();
    }
}
