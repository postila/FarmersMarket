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

    public void changeHealth(double x){
        health -= x;
    }
}
