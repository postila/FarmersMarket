import java.io.Serializable;

public class Grain extends Food implements Serializable {

    public Grain(int amount){
        price = 200;
        this.amount = amount;
    }

}
