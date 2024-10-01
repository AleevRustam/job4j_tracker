package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;

import java.util.function.Predicate;

public class CSVReport implements Report {

    private final Report report;

    public CSVReport(Report report) {
        this.report = report;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String engineReport = report.generate(filter);
        String[] lines = engineReport.split(System.lineSeparator());

        StringBuilder csvReport = new StringBuilder();

        csvReport.append("Name,Hired,Fired,Salary").append(System.lineSeparator());

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();
            if (!line.isEmpty()) {
                String[] parts = splitEmployeeData(line);
                csvReport.append(parts[0]).append(",")
                        .append(parts[1]).append(",")
                        .append(parts[2]).append(",")
                        .append(parts[3])
                        .append(System.lineSeparator());
            }
        }

        return csvReport.toString();
    }

    private String[] splitEmployeeData(String line) {
        String[] parts = line.split(" ", 6);
        String name = parts[0];
        String hired = String.join(" ", parts[1], parts[2]);
        String fired = String.join(" ", parts[3], parts[4]);
        String salary = parts[5];

        return new String[]{name, hired, fired, salary};
    }
}
