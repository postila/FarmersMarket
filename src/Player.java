import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public String name;
    public int money;
    public ArrayList<Animal> animals;
    public ArrayList<Food> foods;

    public Player(String name){
        this.name = name;
        this.money = 500000;
        this.animals = new ArrayList<>();
        this.foods = new ArrayList<>();
    }

    public void addAnimal(Animal animal){
        animals.add(animal);    // Add animal to players list
            // Changes players money amount when player buy animal
        if(animal instanceof Horse){
            money = (money-animal.getPrice());
        }
        if(animal instanceof Cow){
            money = (money-animal.getPrice());
        }
        if(animal instanceof Pig){
            money = (money-animal.getPrice());
        }
        if(animal instanceof Sheep){
            money = (money-animal.getPrice());
        }
        if(animal instanceof Llama){
            money = (money-animal.getPrice());
        }
    }
    public void addFood(Food food){
        foods.add(food);    // Add food to players list
        if(food instanceof Hay){
            money = (money - food.getFoodPrice());
        }
        if(food instanceof Grass){
            money = (money - food.getFoodPrice());
        }
        if(food instanceof Grain){
            money = (money - food.getFoodPrice());
        }
    }
    public void notFeedingAnimal(){
        for(var a : animals){
            a.decreaseHealth();
            a.changeHealth();
        }
    }
    public void feedAnimal(){
        for (var a : animals){
            if(a.health <= 100){    // If animals health is 100 or more, no increase will happen
                a.increaseHealth();
                if(a.health > 100){
                    a.health = 100; // An animals can only be 100
                    continue;
                }
            }
            System.out.println("New Health: " + (int)a.health);
        }
    }
    public void mateAnimal(){
    }

    public void showPlayerInfo(){
        System.out.println(this.name + " information" +
                "\n-----------------------------\n" +
                "[Money]\t " + this.money + "SEK" +
                "\n-----------------------------\n" +
                "[Animal List]");
        for(var a : animals){
            System.out.println(a.getClass().getSimpleName() + " " + a.name + " " + (int)a.health);
            a.living();
        }
        System.out.println("--------------------------");
        for(var f : foods){
            System.out.println(f.getClass().getSimpleName());
        }
    }
}
