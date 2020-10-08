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

    public Animal(String name, String gender){
        this.name = name;
        this.gender = Gender.valueOf(gender.toUpperCase());
    }

    public void animalDied() {
        if (health <= 0) {
            isAlive = false;
            System.out.println("Your animal is dead.");
        }
    }
    public void changeHealth(){
        health -= (healthDecrease);
    }
    public void decreaseHealth(){
        healthDecrease = RANDOM.nextInt(21) + 10;  // randomize a percentage between 10-30 for health loss
        healthDecrease /= 100;   //  Convert the unit of loss into percentage
        healthDecrease = (int)(health * healthDecrease);   // Calculates how much loss an animal should lose
        if(healthDecrease < 1){
            healthDecrease = 1; // So that animal life value always lose something
        }
    }
    public void increaseHealth(int kilo){
        if(health <= 100){
            double increasePerKilo = (0.1 * kilo) + 1.0;
            System.out.println(increasePerKilo + " percentage based on amount of food");
            health = (int)health * increasePerKilo;
            System.out.println(health + " that will be increased.");
                if(health > 100){
                health = 100;
            }
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
                print("Congratulations! You got a new " + newGender + " " + animal.getClass().getSimpleName().toLowerCase());
                var newName = prompt("Now you have to give it a name: ");
                player.animals.add(new Horse(newName, newGender));
            }
            if(animal instanceof Cow){
                var newGender = setGender();
                print("Congratulations! You got a new " + newGender + " " + animal.getClass().getSimpleName().toLowerCase());
                var newName = prompt("Now you have to give it a name: ");
                player.animals.add(new Cow(newName, newGender));
            }
            if(animal instanceof Pig){
                var newGender = setGender();
                print("Congratulations! You got a new " + newGender + " " + animal.getClass().getSimpleName().toLowerCase());
                var newName = prompt("Now you have to give it a name: ");
                player.animals.add(new Pig(newName, newGender));
            }
            if(animal instanceof Sheep){
                var newGender = setGender();
                print("Congratulations! You got a new " + newGender + " " + animal.getClass().getSimpleName().toLowerCase());
                var newName = prompt("Now you have to give it a name: ");
                player.animals.add(new Sheep(newName, newGender));
            }
            if(animal instanceof Llama){
                var newGender = setGender();
                print("Congratulations! You got a new " + newGender + " " + animal.getClass().getSimpleName().toLowerCase());
                var newName = prompt("Now you have to give it a name: ");
                player.animals.add(new Llama(newName, newGender));
            }
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
}
