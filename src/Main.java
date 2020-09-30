public class Main {

    public static void main(String[] args) {
        Horse horse = new Horse();
        horse.decreaseHealth(); //  Randomize a number to decrease from horse life value
        horse.changeHealth();   //  Change the horse life value
        System.out.println(horse.health);   //  Print out the new life value
        System.out.println(horse.increaseHealth()); //  Increase life value with 10%

    }
}
