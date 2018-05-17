package worldcup.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import worldcup.Services.interfaces.ConverterService;
import worldcup.Services.interfaces.UsersService;
import worldcup.persistance.entities.User;

import java.util.ArrayList;


@RestController
@RequestMapping(path = "/users")
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @Autowired
    private ConverterService converterService;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getAllUsers() {
        ArrayList<User> all = usersService.getAllUsers();
        return new ResponseEntity<>(converterService.covertUsersToUserDtos(all), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/self")
    @ResponseBody
    public ResponseEntity<?> getSelfInfo() {
        User user = usersService.getSelfUser();
        return new ResponseEntity<>(converterService.covertUserToUserDto(user), HttpStatus.OK);
    }





}
