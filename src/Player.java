import java.util.ArrayList;

public class Player {
    public String name;
    public int money;
    public ArrayList<Animal> animals;
    public ArrayList<Food> foods;

    public Player(String name){
        this.name = name;
        this.animals = new ArrayList<>();
        this.foods = new ArrayList<>();
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
    }
    public void showAnimal(){
        for(var a : animals){
            System.out.println(a.getClass().getSimpleName());
        }
    }
}
