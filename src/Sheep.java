import java.io.Serializable;

public class Sheep extends Animal implements Serializable {

    public Sheep(String name, String gender){
        super(name, gender);
        price = 1000;
        age = 0;
        maxAge = 10;
    }
}
