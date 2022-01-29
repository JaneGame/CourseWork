package ru.netology.DataHelper;

import lombok.SneakyThrows;
import lombok.Value;
import lombok.val;

import java.sql.DriverManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    @Value
    public static class AuthInfo {
        String card;
        String month;
        String year;
        String name;
        String cvv;
    }

    public static AuthInfo getApprovedAuthInfo() {
        return new AuthInfo("1111 2222 3333 4444", generateMonth(), generateYear(), "Vasya Sorokin", "555");
    }

    public static AuthInfo getDeclinedAuthInfo() {
        return new AuthInfo("5555 6666 7777 8888", generateMonth(), generateYear(), "Senya Sorokin", "555");
    }

    public static AuthInfo getRussianName() {
        return new AuthInfo("1111 2222 3333 4444", generateMonth(), generateYear(), "Анна Павлова", "555");
    }

    public static AuthInfo getNoCardName() {
        return new AuthInfo("1111 2222 3333 4443", generateMonth(), generateYear(), "Sonya Sorokina", "555");
    }

//    @SneakyThrows
//    public static void deleteInfo() {
//        var deleteCards = "DELETE FROM payment_entity;";
//
//        val runner = new QueryRunner();
//
//        try (
//                val conn = DriverManager.getConnection(
//                        "jdbc:postgresql://217.25.88.206:5432/app_db", "user", "pass")
//        ) {
//            runner.update(conn, deleteCards);
//
//        }
//
//    }

    @SneakyThrows
    public static StatusPayment getStatus() {
        String statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        String status = null;
        try (
                val conn = DriverManager.getConnection(
                        "jdbc:postgresql://217.25.88.206:5432/app_db", "user", "pass")
        ) {
            status = runner.query(conn, statusSQL, new ScalarHandler<>());
        }
        return new StatusPayment(status);
    }

    @Value
    public static class StatusPayment {
        String status;
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateMonth(){
        String month = generateDate(60).substring(3,5);
        return month;
    }

    public static String generateYear(){
        String year = generateDate(60).substring(8);
        return year;
    }

}
