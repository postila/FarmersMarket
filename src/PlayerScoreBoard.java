import java.util.*;

public class PlayerScoreBoard implements Comparator<Player>{

    public int compare(Player firstPlayer, Player secondPlayer){
        return Integer.compare(firstPlayer.money, secondPlayer.money);
    }
}
