import games.Game;
import participants.Athlete;

import java.util.*;
import java.util.regex.Pattern;

public class Ozlympic {

    private Scanner scanner;

    private GameData data;

    private List<Game> gameHistories;
    private Game curGame;
    private Athlete predictWinner;

    private boolean isRunning; // indicate the game is running or not

    public Ozlympic() {
        scanner = new Scanner(System.in);
        data = new GameData(); // hard code game data
        gameHistories = new ArrayList<>(); // store all the games
    }

    private void showMenu() {
        System.out.println("Ozlympic Game");
        System.out.println("===========================================");
        System.out.println("1. Select a game to run");
        System.out.println("2. Predict the winner of the game");
        System.out.println("3. Start the game");
        System.out.println("4. Display the final results of all games");
        System.out.println("5. Display the points of all athletes");
        System.out.println("6. Exit");
        System.out.print("Enter an option:");
    }

    private void selectGame() {
        System.out.println("1. Running");
        System.out.println("2. Cycling");
        System.out.println("3. Swimming");
        System.out.print("Select a game to play: ");
        while(true) {
            String cmd = scanner.next();
            if (!checkIsNumber(cmd)) {
                System.out.println("Choose a game within [1-3]");
            } else {
                int id = Integer.parseInt(cmd);
                if (id < 1 || id > 3) {
                    System.out.println("Choose a game within [1-3]");
                } else {
                    if(id == 1) {
                        curGame = data.running();
                    }else if(id == 2) {
                        curGame = data.cycling();
                    }else {
                        curGame = data.swimming();
                    }
                    predictWinner = null; // reset the user's prediction
                    System.out.println("Game has been selected!");
                    break;
                }
            }
        }
        pause();
    }

    private void predict() {
        if(curGame != null) {
            for(int i = 0; i < curGame.getAthletes().size(); i++) {
                System.out.println(""+i+". "+curGame.getAthletes().get(i));
            }
            int numAthletes = curGame.getAthletes().size() - 1;
            while(true) {
                System.out.print("predict the winner of the game (choose [0-"+numAthletes+"]): ");
                String cmd = scanner.next();
                if(checkIsNumber(cmd)) {
                    int id = Integer.parseInt(cmd);
                    if(id < 0 || id > numAthletes) {
                        System.out.print("predict the winner of the game (choose [0-"+numAthletes+"]): ");
                    }else{
                        predictWinner = curGame.getAthletes().get(id);
                        System.out.println(predictWinner+" is your prediction winner!");
                        break;
                    }
                }
            }
            pause();
        }else{
            System.out.println("Please select a game first!");
        }
    }

    private void startGame() {
        if(curGame != null) {
            Athlete winner = curGame.play();
            if(predictWinner != null) { // if the user choose to predict the winner
                if (predictWinner == winner) {
                    System.out.println("Congratulation! Your guess is correct!");
                } else {
                    System.out.println("Sorry, the winner is " + winner + ", but your guess is " + predictWinner);
                }
            }
            gameHistories.add(curGame);
            curGame = null;
            predictWinner = null;
            pause();
        }else {
            System.out.println("Please select a game first!");
        }
    }

    private void displayGameResults() {
        if(gameHistories.isEmpty()) {
            System.out.println("No result to show!");
            pause();
            return;
        }
        for(Game game : gameHistories) {
            System.out.println(game);
        }
        pause();
    }

    private void displayAthletePoints() {
        if(gameHistories.isEmpty()) {
            System.out.println("No result to show!");
            pause();
            return;
        }

        Map<Athlete, Integer> athletePoints = new HashMap<>();
        for(Game game : gameHistories) { // iterate all the game, and collect the points each athlete has got
            Map<Athlete, Integer> athleteScore = game.getReferee().getAthleteScore();
            for(Athlete athlete : game.getAthletes()) {
                int points = athletePoints.getOrDefault(athlete, 0);
                points += athleteScore.getOrDefault(athlete, 0);
                athletePoints.put(athlete, points);
            }
        }

        System.out.println(" Athlete points:");
        for(Map.Entry<Athlete, Integer> entry : athletePoints.entrySet()) {
            System.out.println("   "+entry.getKey()+" total points: "+entry.getValue());
        }
        pause();
    }

    public void start() {
        isRunning = true;
        while(isRunning) {
            showMenu();
            String cmd = scanner.next();
            if(checkIsNumber(cmd)) {
                int id = Integer.parseInt(cmd);
                if(id < 1 || id > 6) {
                    System.out.println("Please input number between 1-6");
                }else{
                    switch (id) {
                        case 1: selectGame(); break;
                        case 2: predict(); break;
                        case 3: startGame(); break;
                        case 4: displayGameResults(); break;
                        case 5: displayAthletePoints(); break;
                        case 6:
                            isRunning = false;
                            stop();
                    }
                }
            }else{
                System.out.println("Please input number between 1-6");
            }
        }
    }

    private boolean checkIsNumber(String cmd) {
        // check the input is number or not
        Pattern pattern = Pattern.compile("\\d+");
        return pattern.matcher(cmd).matches();
    }

    private void stop() {
        // stop the game and close resource
        scanner.close();
    }

    private void pause() {
        // to pause the game in order to get better user experience
        while (true) {
            System.out.print("Press q to go back to main menu: ");
            String q = scanner.next();
            if (q.equals("q")) {
                break;
            }
        }
    }

    public static void main(String args[]) {
        Ozlympic oz = new Ozlympic();
        oz.start();
    }

}
