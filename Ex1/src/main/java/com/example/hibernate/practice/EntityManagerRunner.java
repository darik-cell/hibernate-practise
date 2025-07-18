package com.example.hibernate.practice;

import com.example.hibernate.practice.converter.BirthdayConverter;
import com.example.hibernate.practice.entity.Birthday;
import com.example.hibernate.practice.entity.Role;
import com.example.hibernate.practice.entity.User;
import com.example.hibernate.practice.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;

@Slf4j
public class EntityManagerRunner {
  public static void main(String[] args) {
    HibernateUtil.resetUsers();
    try (var em = HibernateUtil.getSessionFactory().createEntityManager()) {
      em.getTransaction().begin();

      // Find: immediately hits the DB and returns managed entity or null
      User existing = em.find(User.class, "john1.doe@example.com");
      log.info("Found existing user: {}", existing);

      // getReference: returns a proxy, actual select occurs on first access
      User proxy = em.getReference(User.class, "john0.doe@example.com");
      log.info("Got reference (lazy): {}", proxy.getUsername());
      log.info("Вызвал метод у прокси {}", proxy.getFirstname());

      em.remove(existing);
      log.info("Пометил existing как remove: {}", existing);

      // Detach removes entity from the persistence context
      em.evict(existing);
      log.info("Is existing managed after detach? {}", em.contains(existing));

      // Merge copies state of the given entity onto a managed instance
//                existing.setFirstname("test");
//                User merged = em.merge(existing);
//                log.info("Merged entity. Managed instance: {}", merged);

      // Flush forces Hibernate to synchronize with DB before commit
      em.flush();
//                log.info("Flushed changes to the database");
//
//                // Refresh discards unsaved changes and reloads from DB
//                em.refresh(merged);
//                log.info("Refreshed entity from DB: {}", merged);
//
//                // Remove marks entity for deletion
//                em.remove(merged);
//                log.info("Entity scheduled for removal: {}", merged.getUsername());
//
//                // Clear evicts all managed entities from persistence context
//                em.clear();
//                log.info("Is merged managed after clear? {}", em.contains(merged));
//
      em.getTransaction().commit();
    }
  }
}
