package com.example.hibernate.practice.util;

import com.example.hibernate.practice.converter.BirthdayConverter;
import com.example.hibernate.practice.entity.Birthday;
import com.example.hibernate.practice.entity.Role;
import com.example.hibernate.practice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

@Slf4j
public class HibernateUtil {

  public static SessionFactory getSessionFactory() {
    Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
    configuration.addAttributeConverter(new BirthdayConverter(), true);
    return configuration.buildSessionFactory();
  }

  public static void deleteUsers() {
    try (var em = getSessionFactory().createEntityManager()) {
      em.beginTransaction();
      em.createMutationQuery("delete from User").executeUpdate();
      em.getTransaction().commit();
    }
  }

  public static void fillUsers() {
    try (var em = getSessionFactory().createEntityManager()) {
      em.beginTransaction();
      for (int i = 0; i < 10; i++) {
        User newUser = User.builder()
                .username("john" + i + ".doe@example.com")
                .firstname("John")
                .lastname("Doe")
                .birthDate(new Birthday(LocalDate.of(1990, 2, 15)))
                .role(Role.USER)
                .build();
        em.persist(newUser);
        log.info("Persisted new user: {}", newUser);
      }
      em.getTransaction().commit();
    }
  }

  public static void resetUsers() {
    deleteUsers();
    fillUsers();
  }
}
