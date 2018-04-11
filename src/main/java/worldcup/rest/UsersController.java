package worldcup.rest;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import worldcup.entities.User;
import worldcup.repository.UserRepository;

import java.util.ArrayList;


@RestController
@RequestMapping(path = "/users")
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @ResponseBody
    public ResponseEntity<?> getAllUsers() {
        Iterable<User> all = userRepository.findAll();
        ArrayList<User> bets = Lists.newArrayList(all);
        return new ResponseEntity<>(bets, HttpStatus.OK);
    }

}
