package com.example.demo.dao;

import com.example.demo.model.User;
import com.example.demo.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Optional;

public class UserDao extends GenericDao<User, Long> {
    public Optional<User> findByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new RuntimeException("Error while searching for user with username: " + username, e);
        }
    }
}
