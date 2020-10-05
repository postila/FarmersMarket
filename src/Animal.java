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
    public Player owner;
    public int price;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = Gender.valueOf(gender.toUpperCase());
    }

    public void living(){
        //System.out.println("Your animals health level is: " + health);
        if(health >= 0){
            System.out.println("Your animal is still alive.");
            return;
        }
        isAlive = false;
        System.out.println("Your animal is dead.");
        owner.animals.remove(this); // Removes animal from players list
    }
    public void changeHealth(){
        health -= Math.round(healthDecrease);
    }

    // randomize a percentage between 10-30 for health loss
    public void decreaseHealth(){
        healthDecrease = random.nextInt(21) + 10;
        //  Convert the unit of loss into percentage
        healthDecrease /= 100;
        // Calculates how much loss an animal should lose
        healthDecrease = Math.round(health * healthDecrease);
        System.out.println("The amount of life that should be lost if animal not fed: " + (int)healthDecrease);
    }
    public void increaseHealth(){
        health = (int)health * 1.1;
    }
    public int getPrice(){
        return price;
    }

    public Animal mateTwoAnimals(Animal animal) {
            if (animal.equals(this)) {
                System.out.println("I can not mate with myself!");
                return null;
            }
            if (animal.getClass().equals(this.getClass()) && animal.gender != this.gender) {
                boolean yesNo = random.nextBoolean();
                if (!yesNo) {
                    System.out.println("Unsuccessful!");
                    return null;
                } else {
                    System.out.println("Mating was successful! Chose a name: ");
                }
            }
        return null;    // If the mating was unsuccessful
    }
}
