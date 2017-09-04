package games;

import participants.Athlete;
import participants.Official;

import java.util.List;

public class Cycling extends Game {

    public Cycling(String id, Official referee, List<Athlete> athletes) {
        super(id, referee, athletes);    }

}
