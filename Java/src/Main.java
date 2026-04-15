import Controller.EventForm;
import Controller.ParticipantForm;
import View.MainView;
import View.RegistrationForm;
import db.DBConnection;

public class Main {
    public static void main(String[] args) {

        new RegistrationForm();
        //new ParticipantForm();
       // new EventForm();
        System.out.println(DBConnection.getConnection());



    }
}