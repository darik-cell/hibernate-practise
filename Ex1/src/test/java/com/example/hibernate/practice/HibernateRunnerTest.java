package com.example.hibernate.practice;

import com.example.hibernate.practice.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {

//  @Test
//  public void testHibernateApi() throws SQLException, IllegalAccessException {
//    var user = User.builder()
//            .username("ivan1@vail.ru")
//            .firstname("ivan")
//            .lastname("ivanov")
//            .birthDate(LocalDate.of(2000, 01, 01))
//            .age(25)
//            .build();
//
//    var sql = """
//            insert into
//            %s
//            (%s)
//            values
//            (%s)
//            """;
//
//    var tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
//            .map(table -> table.schema() + "." +  table.name())
//            .orElse(user.getClass().getName());
//
//    Field[] fields = user.getClass().getDeclaredFields();
//
//    var columnNames = Arrays.stream(fields)
//            .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                    .map(Column::name)
//                    .orElse(field.getName())
//            ).collect(Collectors.joining(", "));
//
//    var columnValues = Arrays.stream(fields)
//            .map(f -> "?")
//            .collect(Collectors.joining(", "));
//
//    Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:1212/java-guru",
//            "alex", "12345");
//    PreparedStatement ps = conn.prepareStatement(sql.formatted(tableName, columnNames, columnValues));
//
//    for(int i = 0; i < fields.length; i++) {
//      fields[i].setAccessible(true);
//      ps.setObject(i + 1, fields[i].get(user));
//    }
//
//    System.out.println(ps);
//    ps.executeUpdate();
//
//    ps.close();
//    conn.close();
//  }



}