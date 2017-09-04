package games;

import participants.Athlete;
import participants.Official;

import java.util.List;

public class Swimming extends Game {

    public Swimming(String id, Official referee, List<Athlete> athletes) {
        super(id, referee, athletes);
    }

}
