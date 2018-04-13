package worldcup.Services.interfaces;

import worldcup.persistance.entities.Group;

import java.util.ArrayList;

public interface GroupService {
    ArrayList<Group> findAll();
}
