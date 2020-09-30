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

    public void living(){
        System.out.println("Your animals health level is: " + health);
        if(health > 0){
            System.out.println("Your animal is still alive.");
            return;
        }
        isAlive = false;
        System.out.println("Your animal is dead.");
        // NOTE: remember to remove animal from players list.
    }
    public void changeHealth(){
        health -= healthDecrease;
    }
    // randomize a percentage between 10-30 for health loss
    public void decreaseHealth(){
        healthDecrease = random.nextInt(21) + 10;
        //  Convert the unit of loss into percentage
        healthDecrease /= 100;
        // Calculates how much loss an animal should lose
        healthDecrease = (int)health * healthDecrease;
        System.out.println("The amount of life that should be lost if animal not fed: " + (int)healthDecrease);
    }
    public int increaseHealth(){
        return (int)(health * 1.1);
    }
}
