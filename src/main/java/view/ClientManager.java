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
import validation.ValidFromDate;
import validation.ValidYoE;

/**
 * Denna Managed Bean kommer att sköta om alla funktionalitet när en sökande har
 * loggat in. T.ex. lägga/ändra in sin kompetens, lediga period m.m
 * @author Xinmao
 */
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
    @ValidFromDate
    private int from_year;
    private int to_day;
    private int to_month;
    @ValidFromDate
    private int to_year;
    private Date from_date;
    private Date to_date;
    private boolean successa = false;
    @ValidYoE
    private Double years_of_experience;
    private Competence[] competenceList;
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
        uController.init(locale);
    }

    /**
     *
     * @param person_id
     * @return
     */
    public String nextArea(Long person_id) {
        try {
            startConversation();
            transactionFailure = null;
            successC = true;
            uController.nextArea(competence_id, years_of_experience, person_id);

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    /**
     *
     * @return
     */
    public Competence[] getCompetence() {

        List<Competence> compe = uController.getCompetenceList();
        competenceList = new Competence[compe.size()];
        int i = 0;
        for (Competence com : compe) {
            competenceList[i] = com;
            i++;
        }
        return competenceList;

    }

    /**
     *
     * @param s
     * @return
     */
    public String finish(Long s) {
        try {
            startConversation();
            transactionFailure = null;
            successa = true;
            successC = false;
            from_date = new Date(from_year, from_month, from_day);
            to_date = new Date(to_year, to_month, to_day);
            uController.finish(s, from_date, to_date);
            exist = true;

        } catch (Exception e) {
            handleException(e);
        }
        return jsf22Bugfix();
    }

    /**
     *
     * @param s
     * @return
     */
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
    
    /**
     *
     * @return
     */
    public String change(){
       
        change = true;
        
        return jsf22Bugfix();
    }
    
    /**
     *
     * @return
     */
    public int getFrom_day() {
        return from_day;
    }

    /**
     *
     * @param from_day
     */
    public void setFrom_day(int from_day) {
        this.from_day = from_day;
    }

    /**
     *
     * @return
     */
    public int getFrom_month() {
        return from_month;
    }

    /**
     *
     * @param from_month
     */
    public void setFrom_month(int from_month) {
        this.from_month = from_month;
    }

    /**
     *
     * @return
     */
    public int getFrom_year() {
        return from_year;
    }

    /**
     *
     * @param from_year
     */
    public void setFrom_year(int from_year) {
        this.from_year = from_year - 1900;
    }

    /**
     *
     * @return
     */
    public int getTo_day() {
        return to_day;
    }

    /**
     *
     * @param to_day
     */
    public void setTo_day(int to_day) {
        this.to_day = to_day;
    }

    /**
     *
     * @return
     */
    public int getTo_month() {
        return to_month;
    }

    /**
     *
     * @param to_month
     */
    public void setTo_month(int to_month) {
        this.to_month = to_month;
    }

    /**
     *
     * @return
     */
    public int getTo_year() {
        return to_year;
    }

    /**
     *
     * @param to_year
     */
    public void setTo_year(int to_year) {
        this.to_year = to_year - 1900;
    }

    /**
     *
     * @return
     */
    public Double getYears_of_experience() {
        return years_of_experience;
    }

    /**
     *
     * @param years_of_experience
     */
    public void setYears_of_experience(Double years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    /**
     *
     * @return
     */
    public Long getCompetence_id() {
        return competence_id;
    }

    /**
     *
     * @param competence_id
     */
    public void setCompetence_id(Long competence_id) {
        this.competence_id = competence_id;
    }

    /**
     *
     * @return
     */
    public String getC() {
        return c;
    }

    /**
     *
     * @param c
     */
    public void setC(String c) {
        this.c = c;
    }

    /**
     *
     * @return
     */
    public boolean isSuccessC() {
        return successC;
    }

    /**
     *
     * @return
     */
    public boolean isSuccessa() {
        return successa;
    }

    /**
     *
     * @return
     */
    public boolean isExist() {
        if(change){
            return false;
        }
        return exist;
    }

    /**
     *
     * @return
     */
    public boolean isChange() {
        return change;
    }

}
