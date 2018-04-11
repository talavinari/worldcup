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
import worldcup.entities.Group;
import worldcup.entities.User;
import worldcup.repository.GroupRepository;
import worldcup.repository.UserRepository;

import java.util.ArrayList;


@RestController
@RequestMapping(path = "/groups")
public class GroupsController {

    private static final Logger logger = LoggerFactory.getLogger(GroupsController.class);

    @Autowired
    private GroupRepository groupRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @ResponseBody
    public ResponseEntity<?> getAllUsers() {
        Iterable<Group> all = groupRepository.findAll();
        ArrayList<Group> bets = Lists.newArrayList(all);
        return new ResponseEntity<>(bets, HttpStatus.OK);
    }

}
