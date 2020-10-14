import java.io.Serializable;

public class Cow extends Animal implements Serializable {

    public Cow(String name, String gender){
        super(name,gender);
        price = 12000;
        age = 0;
        maxAge = 10;
    }
}
