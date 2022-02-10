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
    public static class FillInfo {
        String card;
        String month;
        String year;
        String name;
        String cvv;
    }

    public static FillInfo getApprovedInfo() {
        return new FillInfo("1111 2222 3333 4444", generateMonth(), generateYear(), "Vasya Sorokin", "555");
    }

    public static FillInfo getDeclinedInfo() {
        return new FillInfo("5555 6666 7777 8888", generateMonth(), generateYear(), "Senya Sorokin", "555");
    }

    public static FillInfo getRussianName() {
        return new FillInfo("1111 2222 3333 4444", generateMonth(), generateYear(), "Анна Павлова", "555");
    }

    public static FillInfo getNoCardName() {
        return new FillInfo("1111 2222 3333 4443", generateMonth(), generateYear(), "Sonya Sorokina", "555");
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
