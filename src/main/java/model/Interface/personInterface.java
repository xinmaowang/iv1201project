/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Interface;

import model.Availability;
import model.Competence_Profile;
import model.Role;

/**
 *
 * @author Xinmao
 */
public interface personInterface {
    public Long getId();
    public String getName();
    public String getSurname();
    public String getEmail();
    public String getSsn();
    public Role getRole_id();
    public Availability getAvailability_id();
   public Competence_Profile getCompetence_profile_id();
}
