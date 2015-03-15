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
    public String changeLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(getLanguageCode()));
        return "";
    }

    private String getLanguageCode() {
        String s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("languageCode");
        return s;
    }
}
