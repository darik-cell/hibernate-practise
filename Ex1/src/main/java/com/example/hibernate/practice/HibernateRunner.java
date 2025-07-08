package com.example.hibernate.practice;

import com.example.hibernate.practice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {
  public static void main(String[] args) {
    try {
      User user = new User();
      Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
      try (var sessionFactory = configuration.buildSessionFactory();
           var session = sessionFactory.openSession();) {
        session.beginTransaction();

        session.persist(User.builder()
                .username("ivan@vail.ru")
                .firstname("ivan")
                .lastname("ivanov")
                .birthDate(LocalDate.of(2000, 01, 01))
                .age(25)
                .build());

        session.getTransaction().commit();
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e.getCause());
      throw e;
    }
  }
}
