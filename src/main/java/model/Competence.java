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
    private Long competence;
    
    private Long competence_id;
    private String name;
    
    public Competence() {
    
}
    public Competence(Long competence_id, String name) {
        this.competence_id = competence_id;
        this.name = name;
    }

    public Long getId() {
        return competence;
    }

    public void setId(Long id) {
        this.competence = competence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competence != null ? competence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.competence == null && other.competence != null) || (this.competence != null && !this.competence.equals(other.competence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Competence[ id=" + competence + " ]";
    }
    
}
