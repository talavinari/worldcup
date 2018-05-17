package worldcup.Services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;
import worldcup.Services.Person;
import worldcup.Services.interfaces.LdapDataService;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class LdapDataServiceImpl implements LdapDataService {

    private static final String S_AM_ACCOUNT_NAME = "sAMAccountName";

    @Autowired
    private LdapTemplate ldapTemplate;

    public Person create(Person person) {
        ldapTemplate.create(person);
        return person;
    }

    public Person findByUid(String uid) {
        return ldapTemplate.findOne(query().where("uid").is(uid), Person.class);
    }

    public void update(Person person) {
        ldapTemplate.update(person);
    }

    public void delete(Person person) {
        ldapTemplate.delete(person);
    }

    public List<Person> findAll() {
        return ldapTemplate.findAll(Person.class);
    }

    public List<Person> findByLastName(String lastName) {
        return ldapTemplate.find(query().where("sn").is(lastName), Person.class);
    }

    @Override
    public Person findBysAMAccountName(String sAMAccountName) {
        List<Person> users = ldapTemplate.find(query().where(S_AM_ACCOUNT_NAME).is(sAMAccountName), Person.class);
        if (users.isEmpty()){
            return null;
        }
        return users.get(0);

    }
}
