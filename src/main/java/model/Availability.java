/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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


    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date from_date;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date to_date;

    /**
     *
     */
    public Availability() {

    }

    /**
     * Initrerar värde
     * @param from_date
     * @param to_date
     */
    public Availability(Date from_date, Date to_date) {
        Random rand = new Random();
        this.availability_id = new Long(rand.nextInt(Integer.MAX_VALUE) + 1);
        this.from_date = from_date;
        this.to_date = to_date;
    }

    /**
     * get startdatum
     * @return
     */
    public Date getFrom_date() {
        return from_date;
    }

    /**
     * set startdatum
     * @param from_date
     */
    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    /**
     * get slutdatum
     * @return
     */
    public Date getTo_date() {
        return to_date;
    }

    /**
     * set slutdatum
     * @param to_date
     */
    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    /**
     * get id
     * @return
     */
    public Long getId() {
        return availability_id;
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
