package participants;

import games.Game;
import games.Swimming;

import java.util.Random;

public class Swimmer extends Athlete {

    public Swimmer(int id, int age, String name, String state) {
        super(id, age, name, state);
    }

    public int compete(Game game) {
        if(!(game instanceof Swimming)) {
            throw new IllegalArgumentException("Swimmer "+this.getId()
                    +" can only participate sport of swimming");
        }
        Random random = new Random();
        // random return 100 to 200
        return 100 + random.nextInt(100);
    }

    public String toString() {
        return "Swimmer: " + this.getName();
    }

}
