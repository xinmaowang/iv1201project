/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Interface;

import model.Role;

/**
 *
 * @author Xinmao
 */
public interface personInterface {
    public String getName();
    public String getSurname();
    public String getEmail();
    public String getSsn();
    public Role getRole_id();
}
