package controller;

import model.Person;

import model.Role;
import model.Account;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import model.Interface.roleInterface;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import model.Availability;
import model.Competence;
import model.Competence_Profile;
import model.Interface.personInterface;

/**
 * A controller. All calls to the model that are executed because of an action
 * taken by the cashier pass through here.
 */

@Stateless
public class UserController {

    @PersistenceContext(unitName = "group12_IV1201Project_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public void init() {
        if (em.find(Competence.class, new Long(1)) == null) {
            Competence com = new Competence(null, "Java");
            Competence com1 = new Competence(null, "C++");
            Competence com2 = new Competence(null, "Web server");
            Competence com3 = new Competence(null, "Economic");
            em.persist(com);
            em.persist(com1);
            em.persist(com2);
            em.persist(com3);
            
        }
    }

    public List<Competence> getCompetenceList(){
         List<Competence> com = em.createQuery("from Competence m", Competence.class).getResultList();
         return com;
    }
    
    public void nextArea(Long competence_id, Double years_of_experience, Long person_id){
        Competence_Profile profile = new Competence_Profile(years_of_experience);
        profile.setCompetence_id(em.find(Competence.class, competence_id));
        profile.setPerson_id(em.find(Person.class, person_id));
        em.persist(profile);
    }
    
    public void finish(Long s, Date from_date, Date to_date){
        Availability av = new Availability(from_date, to_date);
        av.setPerson_id(em.find(Person.class, s));
        em.persist(av);
    }

}
