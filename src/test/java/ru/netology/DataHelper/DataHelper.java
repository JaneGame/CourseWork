package ru.netology.DataHelper;

import lombok.Value;

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
        return new AuthInfo("1111 2222 3333 4444", "05", "23", "Vasya Sorokin", "555");
    }

    public static AuthInfo getDeclinedAuthInfo() {
        return new AuthInfo("5555 6666 7777 8888", "05", "23", "Senya Sorokin", "555");
    }

    public static AuthInfo getRussianName() {
        return new AuthInfo("1111 2222 3333 4444", "05", "23", "Анна Павлова", "555");
    }

    public static AuthInfo getNoCardName() {
        return new AuthInfo("1111 2222 3333 4443", "05", "23", "Sonya Sorokina", "555");
    }


}
