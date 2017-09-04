package participants;


abstract class Participant {

    private int id;
    private int age;
    private String name;
    private String state;

    Participant(int id, int age, String name, String state) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.state = state;
    }

    public int getId() {
        return this.id;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

}
