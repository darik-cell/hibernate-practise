package com.example.hibernate.practice.converter;

import com.example.hibernate.practice.entity.Birthday;
import jakarta.persistence.AttributeConverter;

import java.sql.Date;
import java.util.Optional;

public class BirthdayConverter implements AttributeConverter<Birthday, Date> {

  @Override
  public Date convertToDatabaseColumn(Birthday attribute) {
    return Optional.ofNullable(attribute)
            .map(Birthday::birthDate)
            .map(Date::valueOf)
            .orElse(null);
  }

  @Override
  public Birthday convertToEntityAttribute(Date dbData) {
    return Optional.ofNullable(dbData)
            .map(Date::toLocalDate)
            .map(Birthday::new)
            .orElse(null);
  }
}
