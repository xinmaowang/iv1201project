/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Entity klassen används för slå upp i databasen med användarnamn
 * @author Yau
 */
@Entity
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    
    @OneToOne(cascade={CascadeType.ALL})
    private Person person_id;
    
    /**
     * 
     */
    public Account(){ 
        
    }

    /**
     * initrerar värder
     * @param username
     */
    public Account(String username) {
        this.username = username;
    }
    
    /**
     * set person_id
     * @param person_id
     */
    public void setPerson_id(Person person_id) {
        this.person_id = person_id;
    }

    /**
     * get person_id
     * @return
     */
    public Person getPerson_id() {
        return person_id;
    }

    /**
     * get användarnamn
     * @return
     */
    public String getUsername() {
        return username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Account[ id=" + username + " ]";
    }
    
}
