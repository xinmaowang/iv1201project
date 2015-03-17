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
    private Competence competence_id;
    
    private Double years_of_experience;
    
    /**
     *
     */
    public Competence_Profile(){
        
    }
    
    /**
     *
     * @Initrearar värde
     */
    public Competence_Profile(Double years_of_experience) {
        Random rand = new Random();
        this.competence_profile_id = new Long(rand.nextInt(Integer.MAX_VALUE) + 1);
        this.years_of_experience = years_of_experience;
    }

    /**
     *
     * @return competence_id
     */
    public Competence getCompetence_id() {
        return competence_id;
    }

    /**
     *
     * @set en ny competence_id
     */
    public void setCompetence_id(Competence competence_id) {
        this.competence_id = competence_id;
    }

    /**
     *
     * @return years_of_experience
     */
    public Double getYears_of_experience() {
        return years_of_experience;
    }

    /**
     *
     * @set en ny years_of_experience
     */
    public void setYears_of_experience(Double years_of_experience) {
        this.years_of_experience = years_of_experience;
    }
   
}
