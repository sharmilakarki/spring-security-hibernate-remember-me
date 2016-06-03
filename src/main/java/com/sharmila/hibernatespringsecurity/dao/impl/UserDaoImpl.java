/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharmila.hibernatespringsecurity.dao.impl;

import com.sharmila.hibernatespringsecurity.dao.UserDao;
import com.sharmila.hibernatespringsecurity.entity.Role;
import com.sharmila.hibernatespringsecurity.entity.User;
import java.util.ArrayList;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sharmila
 */
//@Repository("userDao")
@org.springframework.transaction.annotation.Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Transaction transaction;

    public UserDaoImpl() {
    }

    @Override
    public void insert(User user) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
        //sessionFactory.getCurrentSession().saveOrUpdate(user);
        
    }

    @Override
    public void delete(int id) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        User u = (User) session.load(User.class,new Integer(id));
        if (null!=u) {
            session.delete(u);
        }
       
        
        transaction.commit();
        session.close();
        
    }

    @Override
    public void update(User user) {
       session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
        
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        session = sessionFactory.openSession();
        userList = session.createQuery("select u from User u").list();
        session.close();
        return userList;
    }

    @Override
    public User getById(int id) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        User user = (User) session.get(User.class, new Integer(id));    
       
        transaction.commit();
        session.close();
        System.out.println("got by user's id");
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getByUserName(String username) {
        session=sessionFactory.openSession();
        List<User> userList=new ArrayList<User>();
        userList=session.createQuery("select u from User u where username=:username").setString("username", username).list();
        if(userList.size()>0){
            return userList.get(0);
        }
      
         
        session.close();
        return null;
       
    }
    
    @Override
    public User login(String username){
        session=sessionFactory.openSession();
        session=sessionFactory.openSession();
        List<User> userList=new ArrayList<User>();
        userList=session.createQuery("select u.password,u.username from User u where username=?").setParameter(0, username).list();
        if(userList.size()>0){
            return userList.get(0);
        }
      
         
        session.close();
        return null;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}
