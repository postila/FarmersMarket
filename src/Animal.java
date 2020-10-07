import java.util.Random;

public abstract class Animal {
    enum Gender{
        FEMALE,
        MALE
    }
    public String name;
    public Gender gender;
    public double health = 100.0; // Health set to 100% from beginning.
    public boolean isAlive = true;
    double healthDecrease = 0.0;
    public Random random = new Random();
    public int price;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = Gender.valueOf(gender.toUpperCase());
    }

    public void animalDie() {
        if (health <= 0) {
            isAlive = false;
            System.out.println("Your animal is dead.");
        }
    }
    public void changeHealth(){
        health -= (healthDecrease);
    }
    public void decreaseHealth(){
        healthDecrease = random.nextInt(21) + 10;  // randomize a percentage between 10-30 for health loss
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
