/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Person;
import java.io.Serializable;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Xinmao
 */
@Entity
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long role_id;
    
    private String name;
    
    public Role(){
        
    }
  
    public Role(String name){
        Random rand = new Random();
        role_id = new Long(rand.nextInt(Integer.MAX_VALUE)+ 1);
        this.name = name;
    }
   
 

    public Long getId() {
        return role_id;
    }

    public void setId(Long id) {
        this.role_id = id;
    }
    
    public String getName(){
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (role_id != null ? role_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.role_id == null && other.role_id != null) || (this.role_id != null && !this.role_id.equals(other.role_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Role[ id=" + role_id + " ]";
    }
    
}
