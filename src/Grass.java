import java.io.Serializable;

public class Grass extends Food implements Serializable {

    public Grass(int amount){
        price = 150;
        this.amount = amount;
    }


}
