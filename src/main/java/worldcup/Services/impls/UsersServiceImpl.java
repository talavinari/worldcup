package worldcup.Services.impls;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Service;
import worldcup.Services.Person;
import worldcup.Services.interfaces.LdapDataService;
import worldcup.Services.interfaces.UsersService;
import worldcup.persistance.entities.User;
import worldcup.persistance.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LdapDataService ldapDataService;

    @Override
    public ArrayList<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User getUserFromDB(){
        List<User> byName = userRepository.findByName(normalizeName(getCurrentLoggedInUserName()));
        if (byName.isEmpty()){
            return null;
        }
        else{
            return byName.get(0);
        }
    }

    private User getUserByName(String name) {
        List<User> users = userRepository.findByName(name);
        if (users.isEmpty()) {
            User user = new User();
            String normalizedName = normalizeName(name);
            user.setName(normalizedName);
            return user;
        }
        return users.get(0);
    }

    private String normalizeName(String name) {
        String lowered = name.toLowerCase();
        String[] splited = lowered.split("\\.");
        String normalizedName = Character.toUpperCase(splited[0].charAt(0)) + splited[0].substring(1);
        if (splited.length >= 2) {
            normalizedName += " " + Character.toUpperCase(splited[1].charAt(0)) + splited[1].substring(1);
        }
        return normalizedName;
    }

    @Override
    public String getCurrentLoggedInUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((LdapUserDetailsImpl) authentication.getPrincipal()).getUsername();
    }

    @Override
    public User getSelfUser() {
        return getUserByName(getCurrentLoggedInUserName());
    }

    @Override
    public Person getCurrentLoggedInUserLdapData() {
        return ldapDataService.findBysAMAccountName(getCurrentLoggedInUserName());
    }
}
