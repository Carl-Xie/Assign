package participants;

import games.Game;

public abstract class Athlete extends Participant {

    Athlete(int id, int age, String name, String state) {
        super(id, age, name, state);
    }

    public abstract int compete(Game game);

}
