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
                        "\n What would you like to do?");
                player.showPlayerInfo();
            }
        }
    }

    public void buyAnimal(Player player){
        player.addAnimal(store.createAnimal());
    }

}
