/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.service;

import com.sharmila.hibernatespringsecurity.dao.UserDao;
import com.sharmila.hibernatespringsecurity.entity.Role;
import com.sharmila.hibernatespringsecurity.entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author sharmila
 */
@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    public void insert(User user) {
        userDao.insert(user);

    }

    public void delete(int id) {
        userDao.delete(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public User getByUserName(String username) {
        return userDao.getByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = getByUserName(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    //converting com.....User to org.springframework.security.core.userdetails.User
    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        // boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked for true
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.isStatus(), true, true, true, authorities);

    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> role) {
        Set<GrantedAuthority> setAutho = new HashSet<GrantedAuthority>();

        //build's users authority
        for (Role r : role) {
            setAutho.add(new SimpleGrantedAuthority(r.getRole()));
        }
        List<GrantedAuthority> result = new ArrayList<>(setAutho);
        return result;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public User login(String username){
        return userDao.login(username);
    }
}
