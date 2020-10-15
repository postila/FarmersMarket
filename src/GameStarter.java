import java.util.*;

public class GameStarter {
        Scanner scanner = new Scanner(System.in);
        public int numberOfPlayers;
        public int numberOfRounds;
        public ArrayList<Player> players;

        public GameStarter(){

            print("\n ==================  FARMERS MARKET  ================== ");

            while(numberOfPlayers > 4 || numberOfPlayers < 1)
                try {
                    numberOfPlayers = Integer.parseInt(prompt("\n\n =================  HOW MANY PLAYERS  ================= " +
                            "\n NOTICE: one to four participants"));
                }
                catch(Exception ignore){ }
            while(numberOfRounds > 30 || numberOfRounds < 5)
                try{
                    numberOfRounds = Integer.parseInt(prompt("\n =================  HOW MANY ROUNDS  ==================" +
                            "\n NOTICE: five to thirty rounds"));
                } catch (Exception ignore){ }

            print("\n ===================  PARTICIPANTS  =================== ");
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
