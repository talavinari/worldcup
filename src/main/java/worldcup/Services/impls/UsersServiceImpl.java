package worldcup.Services.impls;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Service;
import worldcup.Services.interfaces.UsersService;
import worldcup.persistance.entities.Group;
import worldcup.persistance.entities.User;
import worldcup.persistance.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ArrayList<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    private User getUserByName(String name){
        List<User> users = userRepository.findByName(name);
        if (users.isEmpty()){
            User user = new User();
            String lowered = name.toLowerCase();
            String[] splited = lowered.split("\\.");
            String normalizedName = Character.toUpperCase(splited[0].charAt(0)) + splited[0].substring(1);
            if (splited.length >= 2){
                normalizedName += " " + Character.toUpperCase(splited[1].charAt(0)) + splited[1].substring(1);
            }
            user.setName(normalizedName);
            Group group = new Group();
            group.setName("TBD: Dummy Group");
            user.setGroup(group);
            return user;
        }
        return users.get(0);
    }

    @Override
    public User getSelfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((LdapUserDetailsImpl) authentication.getPrincipal()).getUsername();

        return getUserByName(username);
    }
}
