package br.com.lelo.fastlogin.config;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

@Component
public class HibernateComponent {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void sessionFactoryVerification() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        if (sessionFactory == null) {
            throw new RuntimeException("SessionFactory not found");
        }
    }

}
