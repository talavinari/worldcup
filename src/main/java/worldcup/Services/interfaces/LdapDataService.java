package worldcup.Services.interfaces;

import worldcup.Services.Person;

public interface LdapDataService {
    Person findBysAMAccountName(String sAMAccountName);
}
