package view;

import controller.UserController;
import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Competence;

@Named("client")
@ConversationScoped
public class ClientManager implements Serializable {

    private static final long serialVersionUID = 16247164405L;
    @EJB

    private UserController uController;
    private Long competence_id;
    private String c;
    private int from_day;
    private int from_month;
    private int from_year;
    private int to_day;
    private int to_month;
    private int to_year;
    private Date from_date;
    private Date to_date;
    private boolean successa = false;
    private Double years_of_experience;
    private Competence[] coffee3List;
    private Exception transactionFailure;
    private boolean successC = false;
    private boolean exist = false;
    private boolean change = false;

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

    public void init() {
        uController.init();
    }

    public String nextArea(Long s) {
        try {
            startConversation();
            transactionFailure = null;
            successC = true;
            uController.nextArea(competence_id, years_of_experience, s);

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    public Competence[] getFavCoffee3Value() {

        List<Competence> compe = uController.getCompetenceList();
        coffee3List = new Competence[compe.size()];
        int i = 0;
        for (Competence com : compe) {
            coffee3List[i] = com;
            i++;
        }
        return coffee3List;

    }

    public String finish(Long s) {
        try {
            startConversation();
            transactionFailure = null;
            successa = true;
            from_date = new Date(from_year, from_month, from_day);
            to_date = new Date(to_year, to_month, to_day);
            uController.finish(s, from_date, to_date);
            exist = true;

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    public String ifCom(Long s){
         try {
            startConversation();
            transactionFailure = null;
            if(uController.ifCom(s)){
                exist = true;
            }

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }
    
    public String change(){
       
        change = true;
        
        return jsf22Bugfix();
    }
    
    public int getFrom_day() {
        return from_day;
    }

    public void setFrom_day(int from_day) {
        this.from_day = from_day;
    }

    public int getFrom_month() {
        return from_month;
    }

    public void setFrom_month(int from_month) {
        this.from_month = from_month;
    }

    public int getFrom_year() {
        return from_year;
    }

    public void setFrom_year(int from_year) {
        this.from_year = from_year - 1900;
    }

    public int getTo_day() {
        return to_day;
    }

    public void setTo_day(int to_day) {
        this.to_day = to_day;
    }

    public int getTo_month() {
        return to_month;
    }

    public void setTo_month(int to_month) {
        this.to_month = to_month;
    }

    public int getTo_year() {
        return to_year;
    }

    public void setTo_year(int to_year) {
        this.to_year = to_year - 1900;
    }

    public Double getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(Double years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public Long getCompetence_id() {
        return competence_id;
    }

    public void setCompetence_id(Long competence_id) {
        this.competence_id = competence_id;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public boolean isSuccessC() {
        return successC;
    }

    public boolean isSuccessa() {
        return successa;
    }

    public boolean isExist() {
        if(change){
            return false;
        }
        return exist;
    }

    public boolean isChange() {
        return change;
    }

}
