package participants;

import games.Cycling;
import games.Game;

import java.util.Random;

public class Cyclist extends Athlete {

    public Cyclist(int id, int age, String name, String state) {
        super(id, age, name, state);
    }

    public int compete(Game game) {
        if(!(game instanceof Cycling)) {
            throw new IllegalArgumentException("Cyclist "+this.getId()
                    +" can only participate sport of Cycling");
        }
        Random random = new Random();
        // random return 500 to 800
        return 500 + random.nextInt(300);
    }

    public String toString() {
        return "Cyclist: " + this.getName();
    }
}
