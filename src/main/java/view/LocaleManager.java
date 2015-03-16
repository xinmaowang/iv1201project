package view;

import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Sets the correct locale for the entire application.
 */
@Named(value="locale")
@ApplicationScoped
public class LocaleManager {
    
    private String s = "sv";
    public String changeLocale() {
        getLanguageCode();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(s));
        return "";
    }
    
     public String initLocale() {
        //String s = FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(s));
        return "";
    }

    private void getLanguageCode() {
        s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("languageCode");
        
    }

    public String getS() {
        return s;
    }
}
