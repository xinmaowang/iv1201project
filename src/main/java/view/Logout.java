package view;

import controller.Controller;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Interface.personInterface;
import model.Interface.roleInterface;

@Named("logout")
@ConversationScoped
public class Logout implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB

    private Controller controller;
    private boolean succ = false;
    private Exception transactionFailure;
   
   
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
    
    
    public String backToMain() {
        try {
            //startConversation();
            transactionFailure = null;
            
            succ = true;

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }
    
       

    public boolean getSucc() {
        return succ;
    }


 
}
