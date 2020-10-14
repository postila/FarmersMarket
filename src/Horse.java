import java.io.Serializable;

public class Horse extends Animal implements Serializable {

    public Horse(String name, String gender){
        super(name, gender);
        price = 50000;
        age = 0;
        maxAge = 20;
    }
}
