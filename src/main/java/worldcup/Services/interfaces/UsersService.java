package worldcup.Services.interfaces;

import worldcup.Services.Person;
import worldcup.persistance.entities.User;

import java.util.ArrayList;

public interface UsersService {
    ArrayList<User> getAllUsers();

    User save(User newUser);

    User getUserFromDB();

    String getCurrentLoggedInUserName();

    User getSelfUser();

    Person getCurrentLoggedInUserLdapData();
}
