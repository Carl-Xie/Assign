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

    public Athlete play() {
        for(Athlete athlete : athletes) {
            result.put(athlete, athlete.compete(this));
        }
        List<Map.Entry<Athlete, Integer>> entries = new ArrayList<>(result.entrySet());
        entries.sort(new Comparator<Map.Entry<Athlete, Integer>>() {
            @Override
            public int compare(Map.Entry<Athlete, Integer> o1, Map.Entry<Athlete, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        athletes = new ArrayList<>(); // reassign the order according to the performance in the game
        for(Map.Entry<Athlete, Integer> entry : entries) {
            athletes.add(entry.getKey());
        }
        referee.award(athletes.get(0), 5); // 5 points for first place
        referee.award(athletes.get(1), 2); // 2 points for second place
        referee.award(athletes.get(2), 1); // 1 points for third place
        return athletes.get(0);
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public String getId() {
        return id;
    }

    public Official getReferee() {
        return referee;
    }

    public String toString() {
        String msg =  "Game "+this.id + "\n referee: "+referee + "\n HighScore: \n";
        for(Athlete athlete : athletes) {
            msg += "   "+athlete+"\n";
        }
        return msg;
    }


}
