import javax.net.ssl.HostnameVerifier;
import java.util.*;

public class Player {
    protected String name;
    protected int money;
    protected ArrayList<Animal> animals;
    protected ArrayList<Food> foods;

    public Player(String name){
        this.name = name;
        this.money = 500000;
        this.animals = new ArrayList<>();
        this.foods = new ArrayList<>();
    }

    public void addAnimal(Animal animal){
        if(money < animal.getPrice() || money == 0) {
            System.out.println("You do not have enough money to buy a " +
                    animal.getClass().getSimpleName().toLowerCase()+".");
            return;
        }
        animals.add(animal);    // Add animal to players list if player has enough money
        money = (money - animal.getPrice());
    }
    public void removeDeadAnimal() {
        if (!animals.isEmpty()) {
            animals.removeIf(animal -> animal.health == 0);
        }
    }
    public void addFood(Food food){
        var typeOfFood = food.getClass();
        Food foundFood = null;
        for(var foodInstance : foods){
            if(foodInstance.getClass().equals(typeOfFood)){
                foundFood = foodInstance;
            }
        }
        if(foundFood == null){
            foods.add(food);    // Add food to players list
        }
        else {
            // If type of food already exist in players food list
            foundFood.setAmount(foundFood.getAmount() + food.getAmount());
        }
        // decrease the right amount of money from players money
        money = (money - food.getFoodPrice() * food.getAmount());
    }
    public void reduceFood(Food food, int kilos){
        var typeOfFood = food.getClass();
        Food foundFood = null;
        for (var foodInstance : foods){
            if(foodInstance.getClass().equals(typeOfFood)){
                foundFood = foodInstance;   // If food is found in players list of foods, it's set to chosen Food type
            }
        }
        if(foundFood == null){
            System.out.println("Seems like you are out of " + food.getClass().getSimpleName() + "!");
            return;
        }
        foundFood.setAmount(foundFood.getAmount() - kilos); // Changes players amount of a food
    }
    public void notFeedingAnimal(){
        for(var a : animals){
            a.decreaseHealth();
            a.changeHealth();
            a.animalDied();
        }
    }

    public void feedHay(Animal animal, int kg) {
        animal.increaseHealth(kg);
    }
    public void feedGrass(Animal animal, int kg) {
        animal.increaseHealth(kg);
    }
    public void feedGrain(Animal animal, int kg){
        animal.increaseHealth(kg);
    }
    public void mateAnimal(){
        var scanner = new Scanner(System.in);
        System.out.println("Pick one of your animals to mate:");
        showPlayersAnimals();
        var animalOne = scanner.next();
        String animalTwo = null;

        //  Check if animals exists in players list
        for(var animal : animals) {
            if (animal.name.toUpperCase().equals(animalOne.toUpperCase())) {
                System.out.println("Who would you like to mate " + animal.name + " with?");
            }
        }
    }

    public void showPlayersAnimals(){
        for(var a : animals){
            System.out.println(a.getClass().getSimpleName() + " " + a.name + " " + a.gender.name().toLowerCase());
        }
    }
    public void showPlayerInfo(){
        System.out.println(this.name + " information" +
                "\n-----------------------------\n" +
                "[Money]\t " + this.money + "SEK" +
                "\n-----------------------------\n" +
                "[Animal List]");
        for(var a : animals){
            System.out.println("The " + a.getClass().getSimpleName().toLowerCase() + ", " + a.name + " " + (int)a.health);
        }
        System.out.println("--------------------------");
        for(var f : foods){
            System.out.println(f.getClass().getSimpleName() + " Amount: " + f.getAmount() + " kg.");
        }
    }
}
