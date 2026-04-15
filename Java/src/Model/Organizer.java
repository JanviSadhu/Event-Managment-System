package Model;

public class Organizer {
    private int id;
    private String name;

    public Organizer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Organizer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}