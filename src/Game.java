import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public int numberOfRounds;
    public ArrayList<Player> players = new ArrayList<>();

    public Game(){
        // CONSTRUCTOR
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }
}
