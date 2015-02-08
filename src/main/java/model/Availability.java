/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/*availability_id BIGINT PRIMARY KEY,
person_id BIGINT REFERENCES person,
from_date DATE,
to_date DATE*/

/**
 *
 * @author Yau
 */
@Entity
public class Availability implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long availability_id;
    
    
   @OneToOne()
    private Person person_id;
    private Date from_date;
    private Date to_date;

    public Availability() {
        
    }
    
    public Availability(Long availability_id, Date from_date, Date to_date) {
    this.availability_id = availability_id;
    this.from_date = from_date;
    this.to_date = to_date;
}
    
    public Person getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Person person_id) {
        this.person_id = person_id;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public Long getId() {
        return availability_id;
    }

    public void setId(Long availability_id) {
        this.availability_id = availability_id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (availability_id != null ? availability_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability other = (Availability) object;
        if ((this.availability_id == null && other.availability_id != null) || (this.availability_id != null && !this.availability_id.equals(other.availability_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Availability[ id=" + availability_id + " ]";
    }
    
}
