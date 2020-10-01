import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public int numberOfRounds;
    public Player player;
    public ArrayList<Player> players;

    public Game(ArrayList<Player> players){
        this.players = players;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

}
