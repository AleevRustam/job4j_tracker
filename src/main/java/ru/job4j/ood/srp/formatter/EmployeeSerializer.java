package ru.job4j.ood.srp.formatter;

import com.google.gson.*;
import ru.job4j.ood.srp.model.Employee;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmployeeSerializer implements JsonSerializer<Employee> {
    @Override
    public JsonElement serialize(Employee employee, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", employee.getName());
        jsonObject.addProperty("hired", formatDate(employee.getHired()));
        jsonObject.addProperty("fired", formatDate(employee.getFired()));
        jsonObject.addProperty("salary", employee.getSalary());
        return jsonObject;
    }

    private String formatDate(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        return dateFormat.format(calendar.getTime());
    }
}