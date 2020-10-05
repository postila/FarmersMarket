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

    public void living() {
        if (health > 0) {
            System.out.println("Your animal is still alive.");
            return;
        }
        isAlive = false;
        System.out.println("Your animal is dead.");
    }
    public void changeHealth(){
        health -= (healthDecrease);
    }

    // randomize a percentage between 10-30 for health loss
    public void decreaseHealth(){
        healthDecrease = random.nextInt(21) + 10;
        //  Convert the unit of loss into percentage
        healthDecrease /= 100;
        // Calculates how much loss an animal should lose
        healthDecrease = Math.round(health * healthDecrease);
        if(healthDecrease < 1){
            healthDecrease = 1; // So that animal life value always lose something
        }
        System.out.println("The amount of life that should be lost if animal not fed: " + (int)healthDecrease);
    }
    public void increaseHealth(){
        if(health <= 100){
        health = (int)health * 1.1;
            if(health > 100){
                health = 100;
            }
        }
    }
    public int getPrice(){
        return price;
    }

    public String getName(){
        return this.name;
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
