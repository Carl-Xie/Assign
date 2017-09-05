package participants;

import games.Game;

import java.util.HashMap;
import java.util.Map;

public class Official extends Participant {

    private final Map<Athlete, Integer> athleteScore;

    public Official(int id, int age, String name, String state) {
        super(id, age, name, state);
        this.athleteScore = new HashMap<>();
    }

    /**
     * @param athlete the Athlete
     * @param points points witch the athlete achieved in the game
     */
    public void award(Athlete athlete, int points) {
        int previousPoints = this.athleteScore.getOrDefault(athlete, 0);
        this.athleteScore.put(athlete, previousPoints + points);
    }

    public Map<Athlete, Integer> getAthleteScore() {
        return athleteScore;
    }

    public String toString() {
        return this.getName();
    }

}
