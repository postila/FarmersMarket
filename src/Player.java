import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public String name;
    public int money;
    public ArrayList<Animal> animals;
    public ArrayList<Food> foods;

    public Player(String name){
        this.name = name;
        this.money = 0;
        this.animals = new ArrayList<>();
        this.foods = new ArrayList<>();
    }

    public void addAnimal(Animal animal){
        animals.add(animal);    // Add animal to players list
    }
    public void addFood(Food food){
        foods.add(food);    // Add food to players list
    }
    public void notFeedingAnimal(){
        for(var a : animals){
            a.decreaseHealth();
        }
    }
    public void feedAnimal(){
        for (var a : animals){
            System.out.println("Old Health: " + (int)a.health);
            if(a.health <= 100){    // If animals health is 100 or more, no increase will happen
                a.increaseHealth();
            }
            System.out.println("New Health: " + (int)a.health);
        }
    }

    public void showPlayerInfo(){
        System.out.println(this.name + " information" +
                "\n-----------------------------\n" +
                "[Money]\t " + this.money + "SEK" +
                "\n-----------------------------\n" +
                "[Animal List]");
        for(var a : animals){
            System.out.println(a.getClass().getSimpleName() + " " + a.name + " " + a.health);
            a.living();
        }
        System.out.println("--------------------------");
        for(var f : foods){
            System.out.println(f.getClass().getSimpleName());
        }
    }
}
