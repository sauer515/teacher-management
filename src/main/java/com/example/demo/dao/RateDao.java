package com.example.demo.dao;

import com.example.demo.model.Rate;
import com.example.demo.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class RateDao extends GenericDao<Rate, Long> {
    // specific methods can be added here

    public List<Rate> findByClassTeacherId(String className) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Rate r where r.classTeacher.groupName = :className",
                    Rate.class).setParameter("className", className).list();
        } catch (Exception e) {
            throw new RuntimeException("Error while searching for rates for ClassTeacher with ID: " + className, e);
        }
    }
}
