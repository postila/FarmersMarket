import java.io.Serializable;
import java.util.*;

public abstract class Animal {
    enum Gender{
        FEMALE,
        MALE
    }
    protected String name;
    protected Gender gender;
    protected double health = 100.0; // Health set to 100% from beginning.
    protected boolean isAlive = true;
    protected double healthDecrease = 0.0;
    private final Random RANDOM = new Random();
    private final Scanner SCANNER = new Scanner(System.in);
    protected int price;
    protected int age;
    protected int maxAge;
    protected boolean sick;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = Gender.valueOf(gender.toUpperCase());
    }
    public void animalDied() {
        if (health <= 0 || age >= maxAge) {
            isAlive = false;
            System.out.println("Your animal is dead.");
        }
    }
    public void decreaseHealth(){
        healthDecrease = RANDOM.nextInt(21) + 10;  // randomize a percentage between 10-30 for health loss
        healthDecrease /= 100;   //  Convert the unit of loss into percentage
        healthDecrease = (int)(health * healthDecrease);   // Calculates how much loss an animal should lose
        if(healthDecrease < 1){
            healthDecrease = 1; // So that animal life value always lose something
        }
        health -= (healthDecrease);
    }
    public void increaseHealth(int kilo){
        if(health <= 100){
            health = (int)health * ((0.1 * kilo) + 1);
                if(health > 100){
                health = 100;
            }
        }
    }
    public void getSick(){
        var sick = RANDOM.nextInt(5)+1;
        if(sick == 1){
            print("OH NO!! Your animal is sick.");
            this.sick = true;
        }
        else{
            print("Healthy Animal!");
            this.sick = false;
        }
    }
    public void healAnimal(){
        boolean healed = RANDOM.nextBoolean();
        if(healed) {
            print("-".repeat(56) +
                    "\nWe are happy to inform that your animal is well again!\n");
            sleep(1000);
            this.sick = false;
        }
    }
    public int getPrice(){
        return price;
    }
    public void mateTwoAnimals(Animal animal, Player player) {
        boolean trueOrFalse = RANDOM.nextBoolean();
        if(trueOrFalse){
            if(animal instanceof Horse){
                var newGender = setGender();
                print("CONGRATULATIONS! You got a new " + newGender + " " + animal.getClass().getSimpleName().toLowerCase());
                var newName = prompt("Now you have to give it a name: ");
                player.animals.add(new Horse(newName, newGender));
            }
            if(animal instanceof Cow){
                var newGender = setGender();
                print("CONGRATULATIONS! You got a new " + newGender + " " + animal.getClass().getSimpleName().toLowerCase());
                var newName = prompt("Now you have to give it a name: ");
                player.animals.add(new Cow(newName, newGender));
            }
            if(animal instanceof Pig) {
                var numberOfPiglets = RANDOM.nextInt(8) + 3; // A sow can have between 3-10 piglets
                print("CONGRATULATIONS! \nYou just got " + numberOfPiglets + " piglets!");
                for (var i = 1; i <= numberOfPiglets; i++) {
                    var newGender = setGender();
                    var newName = prompt("Name " + newGender + " piglet number " + i + ":");
                    player.animals.add(new Pig(newName, newGender));
                }
            }
            if(animal instanceof Sheep) {
                int numberOfLambs = RANDOM.nextInt(3) + 1;  // A sheep can have between 1-3 lambs
                print("CONGRATULATIONS! \nYou just got " + numberOfLambs + " lambs!");
                for (var i = 1; i <= numberOfLambs; i++) {
                    var newGender = setGender();
                    var newName = prompt("Name " + newGender + " lamb number " + i + ":");
                    player.animals.add(new Sheep(newName, newGender));
                }
            }
            if(animal instanceof Llama){
                var newGender = setGender();
                print("Congratulations! You got a new " + newGender + " " + animal.getClass().getSimpleName().toLowerCase());
                var newName = prompt("Now you have to give it a name: ");
                player.animals.add(new Llama(newName, newGender));
            }
        } else{
            print("[UNSUCCESSFUL MATING]");
            sleep(700);
        }
    }
    public String setGender(){
        boolean female = RANDOM.nextBoolean();  // Randomize gender
        if(female){
            return "female";
        }
        return "male";
    }
    public void print(String text){     // help method
        System.out.println(text);
    }
    public String prompt(String question){      // help method
        System.out.println(question);
        return SCANNER.nextLine();
    }
    public void sleep(int ms){
        try{
            Thread.sleep(ms);
        } catch (Exception ignore){}
    }
    public String animalName(){
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
