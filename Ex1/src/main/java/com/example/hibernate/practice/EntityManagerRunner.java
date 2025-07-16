package com.example.hibernate.practice;

import com.example.hibernate.practice.converter.BirthdayConverter;
import com.example.hibernate.practice.entity.Birthday;
import com.example.hibernate.practice.entity.Role;
import com.example.hibernate.practice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;

@Slf4j
public class EntityManagerRunner {
    public static void main(String[] args) {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAttributeConverter(new BirthdayConverter(), true);
            try (var sessionFactory = configuration.buildSessionFactory();
                 EntityManager entityManager = sessionFactory.createEntityManager()) {
                entityManager.getTransaction().begin();

                // Persist: adds a new entity to the persistence context
                User newUser = User.builder()
                        .username("john.doe@example.com")
                        .firstname("John")
                        .lastname("Doe")
                        .birthDate(new Birthday(LocalDate.of(1990, 2, 15)))
                        .role(Role.USER)
                        .build();
                entityManager.persist(newUser);
                log.info("Persisted new user: {}", newUser);

                // Find: immediately hits the DB and returns managed entity or null
                User existing = entityManager.find(User.class, "ivan1@vail.ru");
                log.info("Found existing user: {}", existing);

                // getReference: returns a proxy, actual select occurs on first access
                User proxy = entityManager.getReference(User.class, "ivan1@vail.ru");
                log.info("Got reference (lazy): {}", proxy.getUsername());

                // Detach removes entity from the persistence context
                entityManager.detach(existing);
                log.info("Is existing managed after detach? {}", entityManager.contains(existing));

                // Merge copies state of the given entity onto a managed instance
                existing.setFirstname("Ivan-updated");
                User merged = entityManager.merge(existing);
                log.info("Merged entity. Managed instance: {}", merged);

                // Flush forces Hibernate to synchronize with DB before commit
                entityManager.flush();
                log.info("Flushed changes to the database");

                // Refresh discards unsaved changes and reloads from DB
                entityManager.refresh(merged);
                log.info("Refreshed entity from DB: {}", merged);

                // Remove marks entity for deletion
                entityManager.remove(merged);
                log.info("Entity scheduled for removal: {}", merged.getUsername());

                // Clear evicts all managed entities from persistence context
                entityManager.clear();
                log.info("Is merged managed after clear? {}", entityManager.contains(merged));

                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            log.error("Unexpected error", e);
            throw e;
        }
    }
}
