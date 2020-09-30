public class Main {

    public static void main(String[] args) {
        Horse horse = new Horse();
        Player player1 = new Player();  // Create new player
        player1.addAnimal(horse);       // Add horse to player1's arraylist of animals
        player1.showAnimal();           // Loop through & show what animal player have in his/hers list
    }
}
