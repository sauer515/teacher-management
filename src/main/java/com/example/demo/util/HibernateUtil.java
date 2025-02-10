package com.example.demo.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    // Statyczny blok inicjalizacyjny - tworzy SessionFactory podczas ładowania klasy
    static {
        try {
            // Ładowanie konfiguracji z pliku hibernate.cfg.xml
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception ex) {
            // Logowanie błędu inicjalizacji
            System.err.println("Inicjalizacja SessionFactory nie powiodła się: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metoda zwracająca aktualną fabrykę sesji
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Metoda zamykająca fabrykę sesji
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
