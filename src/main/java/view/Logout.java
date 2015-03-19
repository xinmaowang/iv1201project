package view;

import controller.Controller;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Interface.personInterface;
import model.Interface.roleInterface;

/**
 * Denna Managed Bean kommer att sköta utloggning för inloggad användare.
 * @author Xinmao
 */
@Named("logout")
@RequestScoped
public class Logout implements Serializable {

    private static final long serialVersionUID = 16247164405L;


    @Inject
    private Conversation conversation;


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
     * metoden för utloggning
     * @return
     */
    public String logout() {
     
        String destination = "/index?faces-redirect=true";

       
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request
                = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
         
            HttpSession session = request.getSession();
            session.invalidate();

            
            request.logout();
        } catch (ServletException e) {
            destination = "/loginerror?faces-redirect=true";
        }

        return destination; 
    }

}
