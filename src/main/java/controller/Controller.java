package controller;

import model.Person;
import model.Role;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

/**
 * A controller. All calls to the model that are executed because of an action
 * taken by the cashier pass through here.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class Controller {

    @PersistenceContext(unitName = "group12_IV1201Project_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public void init(){
        if(em.find(Role.class, new Long(1)) == null){
        Role role = new Role(new Long(1),"BOSS");
        Person person = new Person(new Long(1),"Xinmao", "Wang", "1231", "xinmao@kth.se", "123", "xinmao");
        person.setRole_id(role);
        em.persist(role);
        em.persist(person);
        }
    }
    
    
      public String login(String username, String password) {
        Person account = em.find(Person.class, username);
        if (account == null) {
            //throw new EntityNotFoundException("No such account");
            return "No such account";
        }

        

        if (!account.getPassword().equals(password)) {
            //throw new EntityNotFoundException("Wrong username or password");
            return "Wrong username or password";
        }
        return "success";
    }
   
}
