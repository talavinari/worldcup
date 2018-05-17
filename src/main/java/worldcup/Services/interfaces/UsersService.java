package worldcup.Services.interfaces;

import worldcup.persistance.entities.User;

import java.util.ArrayList;

public interface UsersService {
    ArrayList<User> getAllUsers();

    User save(User newUser);

    String getCurrentLoggedInUserName();

    User getSelfUser();
}
