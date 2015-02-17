package view;

import controller.Controller;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("client")
@ConversationScoped
public class ClientManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB

    private Controller controller;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String ssn;
    private String email;
    private boolean succ = true;



    public boolean getSucc() {
        return succ;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
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
    
    public void init(){
        controller.init();
    }
    
    
    public String login() {
        try {
            startConversation();
            transactionFailure = null;
            succ = false;
            if(controller.login(username, password).equals("success")){
                succ = true;
            }
            

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }
    
        public String newAccount() {
        try {
            startConversation();
            transactionFailure = null;
            succ = false;
            if(controller.newAccount(name, surname, username, password, ssn, email).equals("success")){
                //succ = true;
            }
            

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }


 
}
