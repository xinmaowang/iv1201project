package controller;

import model.Person;

import model.Role;
import model.Account;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import model.Interface.roleInterface;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import model.Interface.personInterface;

/**
 * A controller. All calls to the model that are executed because of an action
 * taken by the cashier pass through here.
 */
@Stateless
public class Controller {
    
    @PersistenceContext(unitName = "group12_IV1201Project_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    ResourceBundle bundle;
    
    public void init() {
        if (em.find(Account.class, "user") == null) {
            
            Role role = new Role("user");
            Account account = new Account("user");
            Person person = new Person("User", "User", "123456789", "user@user.com", "123", "user");
            person.setRole_id(role);
            account.setPerson_id(person);
            em.persist(account);
            
            role = new Role("admin");
            account = new Account("admin");
            person = new Person("Admin", "Admin", "1234567890", "admin@admin.com", "admin", "admin");
            person.setRole_id(role);
            account.setPerson_id(person);
            em.persist(account);
            
        }
    }
    
    public void resourceBundle(String locale) {
        
        String bundleName = "exceptionMessages.ExceptionsMessages";
        bundle = ResourceBundle.getBundle(bundleName, new Locale(locale));
    }
    
    public personInterface login(String username, String password, String locale) {
        try {
            resourceBundle(locale);
        } catch (Exception e) {
            throw new NullPointerException("Something Wrong");
        }
       
            Account account = em.find(Account.class, username);
            if (account == null) {
                throw new EntityNotFoundException(bundle.getString("noAccount"));
                //return null;
            }
            if (!account.getPerson_id().getPassword().equals(password)) {
                throw new EntityNotFoundException(bundle.getString("noAccount"));
                //return null;
            }
            return account.getPerson_id();        
    }
    
    public String newAccount(String name, String surname, String username, String password, String ssn, String email, String locale) {
        try {
            resourceBundle(locale);
        } catch (Exception e) {
            throw new NullPointerException("Something Wrong");
        }
        
        try {
            
            Account account = em.find(Account.class, username);
            
            if (account == null) {
                account = new Account(username);
                Person person = new Person(name, surname, ssn, email, password, username);
                Role role = em.find(Account.class, "user").getPerson_id().getRole_id();
                person.setRole_id(role);
                account.setPerson_id(person);
                em.persist(account);
                return "success";
            }
            return "Not Success";
        } catch (Exception e) {
            throw new NullPointerException(bundle.getString("somethingWrong"));
        }
        
    }
    
}
