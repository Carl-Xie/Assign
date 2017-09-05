import games.Cycling;
import games.Game;
import games.Running;
import games.Swimming;
import participants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameData {

    private final String[] states = new String[] {
            "AAT", "ACT", "HIMI", "JBT", "NSW", "NF", "QId",
            "SA", "Tas", "Vic", "WA"
    };

    private List<Athlete> swimmers = new ArrayList<>();
    private List<Athlete> cyclists = new ArrayList<>();
    private List<Athlete> sprinters = new ArrayList<>();
    private List<Athlete> superAthletes = new ArrayList<>();

    private List<Official> officials = new ArrayList<>();
    private List<Game> games;

    private int globalId = 0;

    /**
     *  hard code to produce game data
     */
    public GameData() {
        Random random = new Random();
        for(int i = 0; i < 10; i++) { // 10 swimmers
            int id = next();
            int age = 15 + random.nextInt(10);
            String name = "Athlete"+id;
            swimmers.add(new Swimmer(id, age, name, states[random.nextInt(states.length)]));
        }
        for(int i = 0; i < 10; i++) { // 10 cyclists
            int id = next();
            int age = 15 + random.nextInt(10);
            String name = "Athlete"+id;
            cyclists.add(new Cyclist(id, age, name, states[random.nextInt(states.length)]));
        }
        for(int i = 0; i < 10; i++) { // 10 sprinters
            int id = next();
            int age = 15 + random.nextInt(10);
            String name = "Athlete"+id;
            sprinters.add(new Sprinter(id, age, name, states[random.nextInt(states.length)]));
        }
        for(int i = 0; i < 5; i++) { // 10 superAthletes
            int id = next();
            int age = 15 + random.nextInt(10);
            String name = "Athlete"+id;
            superAthletes.add(new SuperAthlete(id, age, name, states[random.nextInt(states.length)]));
        }
        for(int i = 0; i < 5; i++) { // 5 officials
            int id = next();
            int age = 15 + random.nextInt(10);
            String name = "Official"+id;
            officials.add(new Official(id, age, name, states[random.nextInt(states.length)]));
        }
    }

    public Game swimming() {
        Random random = new Random();
        if(random.nextDouble() < 0.5) { // half chance to generate a game with super athlete
            return new Swimming("S"+next(),
                    officials.get(random.nextInt(officials.size())),
                    swimmers.subList(0, 7));
        }
        return new Swimming("S"+next(),
                    officials.get(random.nextInt(officials.size())),
                    Arrays.asList(
                            swimmers.get(9),
                            swimmers.get(8),
                            swimmers.get(5),
                            superAthletes.get(random.nextInt(superAthletes.size()))));
    }

    public Game running() {
        Random random = new Random();
        if(random.nextDouble() < 0.5) { // half chance to generate a game with super athlete
            return new Running("R"+next(),
                    officials.get(random.nextInt(officials.size())),
                    sprinters.subList(0, 6));
        }
        return new Running("R"+next(),
                officials.get(random.nextInt(officials.size())),
                Arrays.asList(
                        sprinters.get(8),
                        sprinters.get(7),
                        sprinters.get(6),
                        superAthletes.get(random.nextInt(superAthletes.size()))));
    }

    public Game cycling() {
        Random random = new Random();
        if(random.nextDouble() < 0.5) { // half chance to generate a game with super athlete
            return new Cycling("C"+next(),
                    officials.get(random.nextInt(officials.size())),
                    cyclists.subList(0, 6));
        }
        return new Cycling("C"+next(),
                officials.get(random.nextInt(officials.size())),
                Arrays.asList(
                        cyclists.get(8),
                        cyclists.get(7),
                        cyclists.get(6),
                        superAthletes.get(random.nextInt(superAthletes.size()))));
    }

    /**
     * generate unique id
     * @return id
     */
    private int next() {
        int id = globalId;
        ++globalId;
        return id;
    }


}
