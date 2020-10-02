import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
    public int numberOfRounds;
    public ArrayList<Player> players;
    public Player player;

    public Game(ArrayList<Player> players){
        this.players = players;
        for (var player : players){
            player.showPlayerInfo();
        }
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

}
