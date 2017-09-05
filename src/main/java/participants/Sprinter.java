package participants;

import games.Game;
import games.Running;

import java.util.Random;

public class Sprinter extends Athlete {

    public Sprinter(int id, int age, String name, String state) {
        super(id, age, name, state);
    }

    public int compete(Game game) {
        if(!(game instanceof Running)) {
            throw new IllegalArgumentException("Sprinter "+this.getId()
                    +" can only participate sport of running");
        }
        Random random = new Random();
        // random return 10 to 20
        return 10 + random.nextInt(10);
    }

    public String toString() {
        return "Sprinter: " + this.getName();
    }

}
