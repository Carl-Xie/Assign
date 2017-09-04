package games;

import participants.Athlete;
import participants.Official;

import java.util.List;

public class Running extends Game {

    public Running(String id, Official referee, List<Athlete> athletes) {
        super(id, referee, athletes);
    }

}
