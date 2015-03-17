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
    private Resource res = new Resource();

    /**
     * Initierar värder i databasen 
     * @param locale
     */
    public void init(String locale) {
        try {
            res.resourceBundle(locale);
        if (em.find(Competence.class, new Long(1)) == null) {
            Competence com = new Competence(new Long(1), "Java");
            Competence com1 = new Competence(null, "C++");
            Competence com2 = new Competence(null, "Web server");
            Competence com3 = new Competence(null, "Economic");
            em.persist(com);
            em.persist(com1);
            em.persist(com2);
            em.persist(com3);
        }
        } catch(Exception e){
            throw new EntityNotFoundException(res.bundle.getString("somethingWrong"));
        }
    }

    /**
     * 
     * @return Lista för alla kompetens
     */
    public List<Competence> getCompetenceList() {
        List<Competence> com = em.createQuery("from Competence m", Competence.class).getResultList();
        return com;
    }

    /**
     * Lägga in kompetens för sökande
     * @param competence_id
     * @param years_of_experience
     * @param person_id
     */
    public void nextArea(Long competence_id, Double years_of_experience, Long person_id) {
        if (em.find(Person.class, person_id).getCompetence_profile_id() == null) {
            Competence_Profile profile = new Competence_Profile(years_of_experience);
            Person p = em.find(Person.class, person_id);
            profile.setCompetence_id(em.find(Competence.class, competence_id));
            em.persist(profile);
            p.setCompetence_profile_id(profile);
        }

        Competence_Profile cp = em.find(Person.class, person_id).getCompetence_profile_id();
        cp.setCompetence_id(em.find(Competence.class, competence_id));
        cp.setYears_of_experience(years_of_experience);

    }

    /**
     * Controllera om kompetens profil finns redan för användaren
     * @param person_id
     * @return boolean 
     */
    public boolean ifCom(Long person_id) {
        boolean b = false;
        if (em.find(Person.class, person_id).getAvailability_id() != null) {
            b = true;
        }
        return b;
    }

    /**
     * Lägga in lediga period för sökande
     * @param person_id
     * @param from_date
     * @param to_date
     */
    public void finish(Long person_id, Date from_date, Date to_date) {
        if (em.find(Person.class, person_id).getAvailability_id() == null) {
            Availability av = new Availability(from_date, to_date);
            Person p = em.find(Person.class, person_id);
            em.persist(av);
            p.setAvailability_id(av);
        }
        
        Availability av = em.find(Person.class, person_id).getAvailability_id();
        av.setFrom_date(from_date);
        av.setTo_date(to_date);
    }
}
