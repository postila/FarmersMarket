import java.io.Serializable;
import java.util.*;

public class PlayerScoreBoard implements Comparator<Player>, Serializable {

    public int compare(Player firstPlayer, Player secondPlayer){
        return Integer.compare(firstPlayer.money, secondPlayer.money);
    }
}
