package com.example.hibernate.practice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "users")
public class User {
  @Id
  private String username;
  private String firstname;
  private String lastname;
  private LocalDate birthDate;
  private Integer age;
}
