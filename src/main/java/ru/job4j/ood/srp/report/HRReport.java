package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HRReport implements Report {

    private final Report report;

    public HRReport(Report report) {
        this.report = report;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String engineReport = report.generate(filter);
        String[] lines = engineReport.split(System.lineSeparator());
        StringBuilder hrReport = new StringBuilder();
        hrReport.append("Name; Salary;").append(System.lineSeparator());
        List<String> sortedEmployees = filterAndSortEmployees(lines);
        for (String line : sortedEmployees) {
            hrReport.append(line).append(System.lineSeparator());
        }
        return hrReport.toString();
    }

    private List<String> filterAndSortEmployees(String[] lines) {
        return List.of(lines).stream()
                .skip(1)
                .map(line -> {
                    String[] columns = line.split(" ");
                    String name = columns[0];
                    String salary = columns[columns.length - 1];
                    return name + "; " + salary + ";";
                })
                .sorted(Comparator.comparingDouble(
                        line -> -Double.parseDouble(line.split("; ")[1].replace(";", ""))))
                .collect(Collectors.toList());
    }
}
