package controller;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
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
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import model.Availability;
import model.Competence;
import model.Competence_Profile;
import model.Interface.personInterface;

/**
 * A controller. All calls to the model that are executed because of an action
 * taken by the cashier pass through here.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class AdminController {

    @PersistenceContext(unitName = "group12_IV1201Project_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    private Resource res = new Resource();
    
    /**
     * Lista för alla sökande
     * @param locale
     * @return List<Person>
     */
    public List<Person> getPersonList(String locale) {
        
        String queryString = "SELECT a FROM Person a "
                + "WHERE a.role_id = :role_id";
        Query query = em.createQuery(queryString);
        res.resourceBundle(locale);
        try {
            Account ac = em.find(Account.class, "user");
            Role role = ac.getPerson_id().getRole_id();

            query.setParameter("role_id", role);
        } catch (Exception e) {
            throw new EntityNotFoundException(res.bundle.getString("somethingWrong"));
        }
        return query.getResultList();

    }

    /**
     * Läsa av info om personen m.h.a person_id, skapar en pdf fil med infon.
     * @param person_id
     * @param locale
     * @throws IOException
     */
    public void createPDF(Long person_id, String locale) throws IOException {
           res.resourceBundle(locale);
        if (person_id == null){
           return;
        }
        
        Person p = em.find(Person.class, person_id);
        
        if (p == null){
            return;
        }
        
     
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

        
        String s = "ID: " + p.getId() + ",  Name: " + p.getName() + ",  Surname: " + p.getSurname()
                + ",  SSN: " + p.getSsn() + ",  Email: " + p.getEmail();

        Document a = new Document();
        try {
            response.setContentType("application/pdf");
            PdfWriter.getInstance(a, response.getOutputStream());
            a.open();
            a.addTitle("Applicant PDF");
            a.add(new Chunk(""));
            a.add(new Paragraph(s));
            a.add(new Paragraph(new Date().toString()));
        } catch (IOException | DocumentException e) {
            throw new IOException(res.bundle.getString("somethingWrong"));
        }
        fc.responseComplete();
        a.close();
    }

}
