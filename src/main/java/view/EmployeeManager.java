package view;

import controller.AdminController;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Person;

@Named("employee")
@ConversationScoped
public class EmployeeManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB

    private AdminController ac;
    private Long person_id;
    private Person[] personList;
    private Exception transactionFailure;
    private boolean succes = false;

    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }

    /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return transactionFailure;
    }

    /**
     * This return value is needed because of a JSF 2.2 bug. Note 3 on page 7-10
     * of the JSF 2.2 specification states that action handling methods may be
     * void. In JSF 2.2, however, a void action handling method plus an
     * if-element that evaluates to true in the faces-config navigation case
     * causes an exception.
     *
     * @return an empty string.
     */
    private String jsf22Bugfix() {
        return "";
    }

    public String list() {

        try {
            startConversation();
            transactionFailure = null;

            List<Person> list = ac.getPersonList();
            int i = 0;
            personList = new Person[list.size()];
            for (Person p : list) {
                personList[i] = p;
                i++;
            }

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();

    }
    
     public String createPDF() {

        try {
            startConversation();
            transactionFailure = null;

            ac.createPDF(person_id);

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();

    }

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public Person[] getPersonList() {
        return personList;
    }

}
