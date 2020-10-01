import java.util.*;

public class GameStarter{
        Scanner scanner = new Scanner(System.in);

        public GameStarter(){
            var numberOfPlayers = 0;
            var numberOfRounds = 0;
            while(numberOfPlayers > 4 || numberOfPlayers < 1 || numberOfRounds > 30 || numberOfRounds < 5)
                try {
                    numberOfPlayers = Integer.parseInt(prompt("Hur många spelare (1-4)?"));
                    numberOfRounds = Integer.parseInt(prompt("Hur många rundor (5-30)?"));
                }
                catch(Exception ignore){ }
            var players = new ArrayList<Player>();
            for(var i = 1; i <= numberOfPlayers; i++){
                players.add(new Player(prompt("Spelare " + i + ":s namn?")));
            }
            new Game(players);
        }

        private String prompt(String question){
            System.out.println(question);
            return scanner.nextLine();
        }
    }
