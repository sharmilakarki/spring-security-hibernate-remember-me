/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author sharmila
 */
@Entity
@Table(name = "user_role")
public class UserRoles {
    
    @Id
    @GeneratedValue
    @Column(name="role_id")
    private int id;
    
    @Column(name="user_id")
    private int userId;
    @Column(name="role")
    private String role;
    @Column(name="username")
    private String username;
//    
//    @JoinColumns({
//        @JoinColumn(name = "username", referencedColumnName = "userName", nullable = false,insertable = false,updatable = false),
//        @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false,insertable = false,updatable = false)})
//    @ManyToOne(optional = false)
//    private User user;

    public UserRoles() {
    }

    public UserRoles(int id, String role, String username, User user) {
        this.id = id;
        this.role = role;
        this.username = username;
//        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

  
 
}
