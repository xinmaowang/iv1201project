/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Interface.personInterface;

import java.io.Serializable;
import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Person implements Serializable,personInterface {
    private static final long serialVersionUID = 1L;
 
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @OneToOne(cascade={CascadeType.PERSIST})
    @JoinColumn(name = "ROLE_ID")
    private Role role_id;
    
    @OneToOne()
    @JoinColumn(name = "competence_profile_id")
    private Competence_Profile competence_profile_id;
    
    @OneToOne()
    @JoinColumn(name = "availability_id")
    private Availability availability_id;
    
    private String name;
    private String surname;
    private String ssn;
    private String email;
    private String password;
    private String username;
    
    /**
     *
     */
    public Person(){
        
    }
    
    /**
     *
     * @param name
     * @param surname
     * @param ssn
     * @param email
     * @param password
     * @param username
     */
    public Person(String name, String surname, String ssn, String email, String password, String username){
        Random rand = new Random();
        id = new Long(rand.nextInt(Integer.MAX_VALUE)+ 1);
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.password = password;
        this.username = username;
    }
    
    /**
     *
     * @return id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     *
     * set en ny id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     *
     * @return role_id
     */
    @Override
    public Role getRole_id() {
        return role_id;
    }

    /**
     *
     * set en ny role id
     */
    public void setRole_id(Role role_id) {
        this.role_id = role_id;
    }

    /**
     *
     * @return namn
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * set en ny namn
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return efternamn
     */
    @Override
    public String getSurname() {
        return surname;
    }

    /**
     *
     * set en ny efternamn
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return personnummer
     */
    @Override
    public String getSsn() {
        return ssn;
    }

    /**
     *
     * set en ny personnummer
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     *
     * @return epost adress
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return lösenord
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return användarnamn
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Person[ id=" + id + " ]";
    }

    /**
     *
     * @return competence_profile_id
     */
    @Override
    public Competence_Profile getCompetence_profile_id() {
        return competence_profile_id;
    }

    /**
     *
     * @param competence_profile_id
     */
    public void setCompetence_profile_id(Competence_Profile competence_profile_id) {
        this.competence_profile_id = competence_profile_id;
    }

    /**
     *
     * @return availability_id
     */
    @Override
    public Availability getAvailability_id() {
        return availability_id;
    }

    /**
     *
     * @param availability_id
     */
    public void setAvailability_id(Availability availability_id) {
        this.availability_id = availability_id;
    }
    
}
