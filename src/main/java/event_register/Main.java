package event_register;

import event_register.manager.EventManager;
import event_register.manager.UserManager;
import event_register.model.Event;
import event_register.model.EventType;
import event_register.model.User;
import lombok.SneakyThrows;

import java.sql.SQLException;


public class Main {
    @SneakyThrows
    public static void main(String[] args) {


        UserManager userManager = new UserManager();
        EventManager eventManager = new EventManager();


        User user = new User("Armen", "Hambaryan", "hambaryan@mail.ru", 1);
        try {
            userManager.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
          eventManager.addEvent(new Event("hamerg", "hamalir", true, 1.1, EventType.THEATRE_FESTIVAL));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(eventManager.showEvent());
        System.out.println(userManager.showUser());
    }


    }

