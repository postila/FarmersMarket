import java.util.Random;

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
    public void mateTwoAnimals() {
    }
}
