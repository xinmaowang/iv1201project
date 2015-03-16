/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Xinmao
 */
public class Resource {

    ResourceBundle bundle;

    public void resourceBundle(String locale) {
        try {
            String bundleName = "exceptionMessages.ExceptionsMessages";
            bundle = ResourceBundle.getBundle(bundleName, new Locale(locale));
        } catch (Exception e) {
            throw new NullPointerException("Something Wrong");
        }

    }
}
