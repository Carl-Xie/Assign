package games;

import participants.Athlete;
import participants.Official;

import java.util.*;

public abstract class Game {

    private String id;
    private Official referee;
    private List<Athlete> athletes;
    private Map<Athlete, Integer> result;

    public Game(String id, Official referee, List<Athlete> athletes) {
        if(athletes.size() < 4 || athletes.size() > 8) {
            throw new IllegalArgumentException("A game participants " +
                    "must greater than 4 and less than 8");
        }
        this.referee = referee;
        this.id = id;
        this.athletes = athletes;
        this.result = new HashMap<>();
    }

    public void startGame() {
        for(Athlete athlete : athletes) {
            result.put(athlete, athlete.compete(this));
        }
        List<Map.Entry<Athlete, Integer>> entries = new ArrayList<>(result.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Athlete, Integer>>() {
            @Override
            public int compare(Map.Entry<Athlete, Integer> o1, Map.Entry<Athlete, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        referee.award(entries.get(0).getKey(), 5); // 5 points for first place
        referee.award(entries.get(1).getKey(), 2); // 2 points for second place
        referee.award(entries.get(2).getKey(), 1); // 1 points for third place
    }

    public String getId() {
        return id;
    }

    public Official getReferee() {
        return referee;
    }

}
