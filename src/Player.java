import java.util.*;

public class Player {
    protected String name;
    protected int money;
    protected ArrayList<Animal> animals;
    protected ArrayList<Animal> sickAnimals;
    protected ArrayList<Food> foods;
    private final Scanner scanner = new Scanner(System.in);

    public Player(String name){
        this.name = name;
        this.money = 150000;
        this.animals = new ArrayList<>();
        this.foods = new ArrayList<>();
        this.sickAnimals = new ArrayList<>();
    }
    public void addAnimal(Animal animal){
        if(money < animal.getPrice() || money == 0) {
            print("You do not have enough money to buy a " +
                    animal.getClass().getSimpleName().toLowerCase()+".");
            sleep(1000);
            return;
        }
        animals.add(animal);    // Add animal to players list if player has enough money
        money = (money - animal.getPrice());
    }
    public void increaseAnimalAge(){
        for(var a : animals){
            a.age++;
        }
        removeDeadAnimal();
    }
    public void animalsGetSick(){
        for(var a : animals){
            print(a.animalName());
            a.getSick();
            if(a.sick){
                sickAnimals.add(a);
            }
        }
    }
    public void removeSickAnimals(){
        for(var i = sickAnimals.size()-1; i >= 0; i--){
            var thisAnimal = sickAnimals.get(i);
            animals.remove(thisAnimal); // Makes sure that the right animals get removed if not healed
            sickAnimals.remove(i);
        }
    }
    public void removeDeadAnimal() {
        if (!animals.isEmpty()) {
            animals.removeIf(animal -> animal.health == 0);
            animals.removeIf(animal -> animal.age >= animal.maxAge); // Check if animals age has passed it's max age.
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
        if(food.getFoodPrice() * food.getAmount() > money || money <= 0){
            print("You don't have enough money!");
            sleep(1000);
            return;
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
            print(" =============== " + name.toUpperCase() + " ANIMALS LIST =============== ");
            for(var a : animals){
                print(++count + ".\t" + a.animalName() + "   \t Breed: " + a.getClass().getSimpleName() +
                "\t Gender: " + a.gender);
            }
            try {
                var inputOne = prompt("-".repeat(56) +
                        "\nWhich one of your animals would you like to mate?");
                animalOne = animals.get(Integer.parseInt(inputOne) - 1);
                var inputTwo = prompt("-".repeat(56) +
                        "\nChoose a partner to your animal.");
                animalTwo = animals.get(Integer.parseInt(inputTwo) - 1);
            }catch (Exception e) {
                print("-".repeat(56) +
                        "\nYou have to choose animals between 1-" + count);
            }
        } while (animalOne == null || animalTwo == null);
            if (!animalOne.getClass().equals(animalTwo.getClass()) || animalOne.gender.equals(animalTwo.gender)) {
                print("-".repeat(56) +
                        "\nAnimals must be of same breed and have opposite genders!");
                var input = prompt("\n[C] to CONTINUE.\n[E] to EXIT");
                if(input.toUpperCase().equals("E")){
                    exit = true;
                }
            }
            else {
                print("-".repeat(56));
                animalOne.mateTwoAnimals(animalTwo, this);
                exit = true;
            }
        }
    }
    public void showPlayerInfo(){
        int count = 0;
        print("\n" +
                "\t========= BALANCE ========= \t" + this.money + " SEK" +
                "\n\n" +
                "\t========= ANIMALS ========= \t\t "+ animals.size());
        for(var a : animals) {
            if (!a.sick) {
                print(++count + ".\t" + a.name.toUpperCase() + " THE " + a.getClass().getSimpleName().toUpperCase() +
                        "\n\tHealth: " + (int) a.health + "\t|\tAge: " + a.age + "\t|\tMax age: " + a.maxAge);
            }
        }
        print("\n" +
                "\t====== SICK  ANIMALS ====== \t\t " + sickAnimals.size());
        count = 0;
        for(var a : sickAnimals){
            print(++count + ".\t" + a.name.toUpperCase() + " THE " + a.getClass().getSimpleName().toUpperCase() +
                    "\n\tHealth: " + (int) a.health + "\t|\tAge: " + a.age + "\t|\tMax age: " + a.maxAge);
            }
        print("\n\t========== FOODS ========== \t");
        for(var f : foods){
            print("\t " + f.getClass().getSimpleName().toUpperCase() + "\t|\tAmount: " + f.getAmount() + " KG");
        }
        print("\n");
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
    public void sleep(int ms){
        try{
            Thread.sleep(ms);
        }catch (Exception ignore){}
    }
    public String niceName(){
        return name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
