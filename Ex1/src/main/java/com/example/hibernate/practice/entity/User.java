package com.example.hibernate.practice.entity;

import com.example.hibernate.practice.converter.BirthdayConverter;
import jakarta.persistence.*;
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
@Table(name = "users", schema = "public")
public class User {
  @Id
  private String username;
  private String firstname;
  private String lastname;

  @Column(name = "birth_date")
  @Convert(converter = BirthdayConverter.class)
  private Birthday birthDate;

  @Enumerated(EnumType.STRING)
  private Role role;
}
