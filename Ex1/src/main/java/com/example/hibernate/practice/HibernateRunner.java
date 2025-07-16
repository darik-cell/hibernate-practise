package com.example.hibernate.practice;

import com.example.hibernate.practice.converter.BirthdayConverter;
import com.example.hibernate.practice.entity.Birthday;
import com.example.hibernate.practice.entity.Role;
import com.example.hibernate.practice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {
  public static void main(String[] args) {
    try {
      Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
      configuration.addAttributeConverter(new BirthdayConverter(), true);
      try (var sessionFactory = configuration.buildSessionFactory();
           var session = sessionFactory.openSession();) {
        session.beginTransaction();

//        session.persist(User.builder()
//                .username("ivan1@vail.ru")
//                .firstname("ivan")
//                .lastname("ivanov")
//                .birthDate(new Birthday(LocalDate.of(2000, 01, 01)))
//                .role(Role.ADMIN)
//                .build());
        User user = session.find(User.class, "ivan1@vail.ru");
        log.info("Получил из бд: {}", user);

        session.getTransaction().commit();
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e.getCause());
      throw e;
    }
  }
}
