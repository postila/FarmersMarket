import java.io.Serializable;
import java.util.*;

public class GameStarter implements Serializable {
        Scanner scanner = new Scanner(System.in);
        public int numberOfPlayers;
        public int numberOfRounds;
        public ArrayList<Player> players;

        public GameStarter(){

            while(numberOfPlayers > 4 || numberOfPlayers < 1 || numberOfRounds > 30 || numberOfRounds < 5)
                try {
                    numberOfPlayers = Integer.parseInt(prompt("How many players are you, (1-4)?"));
                    numberOfRounds = Integer.parseInt(prompt("How many rounds do you want to play (5-30)?"));
                }
                catch(Exception ignore){ }

            players = new ArrayList<>();
            for(var i = 1; i <= numberOfPlayers; i++){
                if(i==1) {
                    players.add(new Player(prompt("Enter " + i + ":st players name: ")));
                }
                if(i==2) {
                    players.add(new Player(prompt("Enter " + i + ":nd players name: ")));
                }
                if(i==3) {
                    players.add(new Player(prompt("Enter " + i + ":rd players name: ")));
                }
                if(i==4) {
                    players.add(new Player(prompt("Enter " + i + ":th players name: ")));
                }
            }
            new Game(players, numberOfRounds);
        }

        private String prompt(String question){
            System.out.println(question);
            return scanner.nextLine();
        }
    }
