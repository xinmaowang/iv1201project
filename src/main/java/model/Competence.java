/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Yau
 */

/*
 competence_id BIGINT PRIMARY KEY,
 name VARCHAR(255)
 */
@Entity
public class Competence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long competence_id;

    private String competence_name;

    public Competence() {

    }

    public Competence(Long competence_id, String name) {

        if (competence_id == null) {
            Random rand = new Random();
            this.competence_id = new Long(rand.nextInt(Integer.MAX_VALUE) + 1);
        } else {
            this.competence_id = competence_id;
        }
        this.competence_name = name;
    }

    public Long getCompetence_id() {
        return competence_id;
    }

    public String getCompetence_name() {
        return competence_name;
    }
}
