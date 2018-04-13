package worldcup.Services.impls;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.Services.interfaces.GroupService;
import worldcup.persistance.entities.Group;
import worldcup.persistance.repository.GroupRepository;

import java.util.ArrayList;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public ArrayList<Group> findAll() {
        return Lists.newArrayList(groupRepository.findAll());
    }
}
