package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("ridermix@yandex.ru", "Rustam Aleev");
        map.put("mail@yandex.ru", "Ivan Ivanov");
        map.put("mail@mail.ru", "Ivan Ivanov");
        map.put("ridermix@yandex.ru", "Rustam Aleev");
        map.put("mail@mail.ru", "Petr Petrov");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + " : " + value);
        }
    }
}
