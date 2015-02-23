package controller;

import model.Person;

import model.Role;
import model.Account;
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
        if(em.find(Account.class, "xinmao") == null){
        Role role = new Role("BOSS");
        Account account = new Account("xinmao");
       
        Person person = new Person("Xinmao", "Wang", "1231", "xinmao@kth.se", "123", "xinmao");
        person.setRole_id(role);
        account.setPerson_id(person);
        em.persist(person);
        }
    }
    
    
      public String login(String username, String password) {
        Account account = em.find(Account.class, username);
        if (account == null) {
            //throw new EntityNotFoundException("No such account");
            return "No such account";
        }
        if (!account.getPerson_id().getPassword().equals(password)) {
            //throw new EntityNotFoundException("Wrong username or password");
            return "Wrong username or password";
        }
        return "success";
    }
      
      public String newAccount(String name, String surname, String username, String password, String ssn, String email){
          Account account = new Account(username);
          Person person = new Person(name, surname,ssn, email,password, username);
          account.setPerson_id(person);
          em.persist(account);
          return "success";
      }
   
}
