package participants;

import games.Cycling;
import games.Game;
import games.Running;
import games.Swimming;

import java.util.Random;

public class SuperAthlete extends Athlete {

    public SuperAthlete(int id, int age, String name, String state) {
        super(id, age, name, state);
    }

    public int compete(Game game) {
        Random random = new Random();
        if(game instanceof Running) {
            // random return 10 to 20 for running
            return 10 + random.nextInt(10);
        }else if(game instanceof Swimming) {
            // random return 100 to 200 for swimming
            return 100 + random.nextInt(100);
        }else if(game instanceof Cycling){
            // random return 500 to 800 for cycling
            return 500 + random.nextInt(300);
        }
        throw new IllegalStateException("Unknown Game for SuperAthlete!");
    }
}
