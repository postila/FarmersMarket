import java.io.Serializable;

public class Hay extends Food implements Serializable {
    public Hay(int amount){
        price = 100;
        this.amount = amount;
    }

}
