package view;

import validation.ValidEmail;
import controller.Controller;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Interface.personInterface;
import model.Interface.roleInterface;
import validation.ValidPassword;
import validation.ValidSsn;
import validation.ValidUsername;

/**
 *
 * @author Xinmao
 */
@Named("login")
@ConversationScoped
public class LoginManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB

    private Controller controller;
    
    @ValidUsername
    private String username;
    
    @ValidPassword
    private String password;
    private String name;
    private String surname;
    
    @ValidSsn
    private String ssn;
    
    @ValidEmail
    private String email;
    
    private boolean succ = false;
    private boolean newAccount = false;
    private boolean succAdmin = false;
    private boolean result = true;
    private boolean resultLogin = true;
    private personInterface person = null;
    private Exception transactionFailure;
    private boolean succes = false;
    private boolean au = false;

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
     * @return 
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

    /**
     *
     * @param locale
     */
    public void init(String locale) {
        controller.init(locale);
    }

    /**
     *
     * @param locale
     * @return
     */
    public String login(String locale) {
        try {
            startConversation();
            transactionFailure = null;
            succ = false;
            person = controller.login(username, password, locale);
            if (person != null) {
                if (person.getRole_id().getName().equals("user")) {
                    succ = true;
                } else if (person.getRole_id().getName().equals("admin")) {
                    succAdmin = true;
                }
            } else {
                resultLogin = false;
            }

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    /**
     *
     * @param locale
     * @return
     */
    public String newAccount(String locale) {
        try {
            startConversation();
            transactionFailure = null;
            succ = false;
            newAccount = false;
            au = false;
            if (controller.newAccount(name, surname, username, password, ssn, email, locale).equals("success")) {
                au = true;
                newAccount = true;
            } else {
                result = false;
            }

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    /**
     *
     * @return
     */
    public boolean isResultLogin() {
        return resultLogin;
    }

    /**
     *
     * @return
     */
    public boolean getResult() {
        return result;
    }

    /**
     *
     * @return
     */
    public boolean isSuccAdmin() {
        return succAdmin;
    }

    /**
     *
     * @return
     */
    public boolean isNewAccount() {
        boolean b = newAccount;
        newAccount = false;
        return b;
    }

    /**
     *
     * @return
     */
    public boolean getSucc() {
        return succ;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     */
    public String getSsn() {
        return ssn;
    }

    /**
     *
     * @param ssn
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public personInterface getPerson() {
        return person;
    }

    /**
     *
     * @return
     */
    public boolean isAu() {
        return au;
    }


}
