package model;

public class Participant {

    private int participantId;
    private String name;
    private String email;
    private String phone;

    public Participant(int participantId, String name, String email, String phone) {
        this.participantId = participantId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Registration register(Event event) {
        if (event.isFull()) {
            System.out.println("Cannot register. Event is full.");
            return null;
        }

        event.addParticipant();
        return new Registration(0, event, this);
    }

    // Getters & Setters
    public int getParticipantId() { return participantId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + participantId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
