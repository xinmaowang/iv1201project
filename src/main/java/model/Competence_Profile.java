/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private Long competence_profile_id;
    
    
   
    @OneToOne
    private Person person_id;
    
    @OneToOne
    private Competence competence_id;
    
    private Double years_of_experience;
    
    public Competence_Profile(){
        
    }
    
    public Competence_Profile(Double years_of_experience) {
        Random rand = new Random();
        this.competence_profile_id = new Long(rand.nextInt(Integer.MAX_VALUE) + 1);
        this.years_of_experience = years_of_experience;
    }

    public Person getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Person person_id) {
        this.person_id = person_id;
    }

    public Competence getCompetence_id() {
        return competence_id;
    }

    public void setCompetence_id(Competence competence_id) {
        this.competence_id = competence_id;
    }
   
}
