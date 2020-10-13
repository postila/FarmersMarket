import java.util.*;

public class Player {
    protected String name;
    protected int money;
    protected ArrayList<Animal> animals;
    protected ArrayList<Food> foods;
    private final Scanner scanner = new Scanner(System.in);

    public Player(String name){
        this.name = name;
        this.money = 100000;
        this.animals = new ArrayList<>();
        this.foods = new ArrayList<>();
    }
    public void addAnimal(Animal animal){
        if(money < animal.getPrice() || money == 0) {
            print("You do not have enough money to buy a " +
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
            if(money < (food.getFoodPrice() * food.getAmount())|| money <= 0){     // Check if player has enough money
                print("You don't have enough money!");
                return;
            }
            foods.add(food);    // Add food to players list
        }
        else {
            if(money < (foundFood.getFoodPrice() * foundFood.getAmount()) || money <= 0){     // Check if player has enough money
                print("You don't have enough money!");
                return;
            }
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
            a.animalDied();
        }
    }
    public void mateAnimal(){
        boolean exit = false;
        Animal animalOne = null;
        Animal animalTwo = null;
        while(!exit) {
        do {
            clear();
            int count = 0;
            print("[" + name.toUpperCase() + " ANIMALS LIST]");
            for(var a : animals){
                print("[" + ++count + "] " + a.name + " the " + a.getClass().getSimpleName().toLowerCase());
            }
            try {
                var inputOne = prompt("\nWhich one of your animals would you like to mate?");
                animalOne = animals.get(Integer.parseInt(inputOne) - 1);
                var inputTwo = prompt("Choose a second animal.");
                animalTwo = animals.get(Integer.parseInt(inputTwo) - 1);
            }catch (Exception e) {
                print("You have to choose animals between 1-" + count);
            }
        } while (animalOne == null || animalTwo == null);
            if (!animalOne.getClass().equals(animalTwo.getClass()) || animalOne.gender.equals(animalTwo.gender)) {
                print("Animals must be of same breed and have opposite genders!");
                var input = prompt("\n[C] to CONTINUE.\n[E] to EXIT");
                if(input.toUpperCase().equals("E")){
                    exit = true;
                }
            }
            else {
                print("It's possible to mate your animals!");
                animalOne.mateTwoAnimals(animalTwo, this);
                exit = true;
            }
        }
    }
    public void showPlayersAnimals() {
        var count = 0;
        for (Animal animal : animals) {
            print("[" + ++count + "] " + animal.name + " the " + animal.getClass().getSimpleName());
        }
    }
    public void showPlayerInfo(){
        System.out.println(this.name + " information" +
                "\n-----------------------------\n" +
                "[Money]\t " + this.money + "SEK" +
                "\n-----------------------------\n" +
                "[Animal List]");
        for(var a : animals){
            print("The " + a.getClass().getSimpleName().toLowerCase() + ", " + a.name + " " + (int)a.health);
        }
        System.out.println("--------------------------");
        for(var f : foods){
            print(f.getClass().getSimpleName() + " Amount: " + f.getAmount() + " kg.");
        }
    }
    public String prompt(String question){  // help method
        System.out.println(question);
        return scanner.nextLine();
    }
    public void print(String text){    // help method
        System.out.println(text);
    }
    public void clear(){
        System.out.println("\n".repeat(50));
    }
}
