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


    public String logout() {
        // Notice the redirect syntax. The forward slash means start at
        // the root of the web application.
        String destination = "/index?faces-redirect=true";

        // FacesContext provides access to other container managed objects,
        // such as the HttpServletRequest object, which is needed to perform
        // the logout operation.
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request
                = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            // added May 12, 2014
            HttpSession session = request.getSession();
            session.invalidate();

            // this does not invalidate the session but does null out the user Principle
            request.logout();
        } catch (ServletException e) {
            destination = "/loginerror?faces-redirect=true";
        }

        return destination; // go to destination
    }

}
