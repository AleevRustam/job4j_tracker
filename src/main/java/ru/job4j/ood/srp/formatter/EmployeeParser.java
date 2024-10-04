package ru.job4j.ood.srp.formatter;

import ru.job4j.ood.srp.model.Employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmployeeParser {
    public static List<Employee> getEmployees(String engineReport) {
        String[] lines = engineReport.split(System.lineSeparator());
        List<Employee> employees = new ArrayList<>();

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();
            if (!line.isEmpty()) {
                Employee employee = parseEmployee(line);
                employees.add(employee);
            }
        }
        return employees;
    }

    private static Employee parseEmployee(String line) {
        String[] columns = line.split(" ", 6);
        String name = columns[0];
        Calendar hired = DateTimeParserFromStringToCalendar.parse(columns[1] + " " + columns[2]);
        Calendar fired = DateTimeParserFromStringToCalendar.parse(columns[3] + " " + columns[4]);
        double salary = Double.parseDouble(columns[5]);

        return new Employee(name, hired, fired, salary);
    }
}
