import java.io.Serializable;

public class Pig extends Animal implements Serializable {

    public Pig(String name, String gender){
        super(name, gender);
        price = 1500;
        age = 0;
        maxAge = 8;
    }
}
