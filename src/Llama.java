import java.io.Serializable;

public class Llama extends Animal implements Serializable {


    public Llama(String name, String gender){
        super(name, gender);
        price = 10000;
        age = 0;
        maxAge = 12;
    }
}
