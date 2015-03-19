package view;

import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Denna Managed Bean kommer att sköta språket för hela web applikation.
 */
@Named(value="locale")
@ApplicationScoped
public class LocaleManager {
    
    private String s = "sv";

    /**
     *
     * @return
     */
    public String changeLocale() {
        getLanguageCode();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(s));
        return "";
    }
    
    /**
     *
     * @return
     */
    public String initLocale() {
        //String s = FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(s));
        return "";
    }

    private void getLanguageCode() {
        s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("languageCode");
        
    }

    /**
     *
     * @return
     */
    public String getS() {
        return s;
    }
}
