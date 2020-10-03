import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public String name;
    public int money;
    public ArrayList<Animal> animals;
    public ArrayList<Food> foods;
    public Store store;

    public Player(String name){
        this.name = name;
        this.money = 0;
        this.animals = new ArrayList<>();
        this.foods = new ArrayList<>();
    }

    public void addAnimal(Animal animal){
        animals.add(animal);    // Add animal to players list
    }
    public void showAnimal(){
        for(var a : animals){
            System.out.println(a.getClass().getSimpleName());
        }
    }

    public void showPlayerInfo(){
        System.out.println(this.name + " information" +
                "\n-----------------------------\n" +
                "[Money]\t " + this.money + "SEK" +
                "\n-----------------------------\n" +
                "[Animal List]");
        for(var a : animals){
            System.out.println(a);
        }
    }
}
