package worldcup.Services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.entities.User;
import worldcup.repository.UserRepository;

import java.util.ArrayList;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ArrayList<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }
}
