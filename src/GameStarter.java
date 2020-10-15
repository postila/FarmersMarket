import java.util.*;

public class GameStarter {
        Scanner scanner = new Scanner(System.in);
        public int numberOfPlayers;
        public int numberOfRounds;
        public ArrayList<Player> players;

        public GameStarter(){

            print("\n ==================  FARMERS MARKET  ================== " +
                    "\n" +
                    "\n Number of players\t: 1 to 4" +
                    "\n Number of rounds\t: 5 to 30" +
                    "\n" +
                    "\n  RULES AND HOW TO PLAY," +
                    "\n All players start with 150.000 SEK. You can earn more" +
                    "\n money by mating and selling your animals." +
                    "\n " +
                    "\n Keep your animals alive by feeding them specific foods." +
                    "\n If an animal get sick, it needs a treatment at the vet." +
                    "\n The animal then has a 50 % chance of survival." +
                    "\n " +
                    "\n The winner is the farmer with most value left, after the" +
                    "\n final round has been played and all animals has been sold." +
                    "\n" +
                    "\n  NOTICE," +
                    "\n If you do not have any animals or money left when it" +
                    "\n is your turn, you are out of the game." +
                    "\n" +
                    "\n  HAPPY FARMING!");

            while(numberOfPlayers > 4 || numberOfPlayers < 1)
                try {
                    numberOfPlayers = Integer.parseInt(prompt("\n =================  HOW MANY PLAYERS  ================= "));
                }
                catch(Exception ignore){ }
            while(numberOfRounds > 30 || numberOfRounds < 5)
                try{
                    numberOfRounds = Integer.parseInt(prompt(" =================  HOW  MANY ROUNDS  ================="));
                } catch (Exception ignore){ }

            print(" ===================  PARTICIPANTS  =================== ");
            players = new ArrayList<>();
            for(var i = 1; i <= numberOfPlayers; i++){
                players.add(new Player(prompt(" [PLAYER " + i + "]  REGISTER YOUR NAME:" )));
                print("-".repeat(56));
            }
            new Game(players, numberOfRounds);
        }

        private String prompt(String question){
            System.out.println(question);
            return scanner.nextLine();
        }
        private void print(String text){
            System.out.println(text);
        }
    }
