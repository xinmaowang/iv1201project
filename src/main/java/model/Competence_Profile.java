/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Yau
 */

/*
CREATE TABLE competence_profile (
competence_profile_id BIGINT PRIMARY KEY,
person_id BIGINT REFERENCES person,
competence_id BIGINT REFERENCES competence,
years_of_experience NUMERIC(4,2)
*/
@Entity
public class Competence_Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long competence_profile;
    
    
   
    @OneToOne
    private Person person_id;
    
    @OneToOne
    private Competence competence_id;
    
    private Double Years_of_experience;
    
    public Competence_Profile(){
        
    }
    
    public Competence_Profile(Long competence_profile_id, Person person_id, 
            Competence competence_id, double Years_of_experience) {
    }
   

    public Long getId() {
        return competence_profile;
    }

    public void setId(Long id) {
        this.competence_profile = competence_profile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competence_profile != null ? competence_profile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence_Profile)) {
            return false;
        }
        Competence_Profile other = (Competence_Profile) object;
        if ((this.competence_profile == null && other.competence_profile != null) || (this.competence_profile != null && !this.competence_profile.equals(other.competence_profile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Competence_Profile[ id=" + competence_profile + " ]";
    }
    
}
