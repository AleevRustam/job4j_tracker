package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.EmployeeParser;
import ru.job4j.ood.srp.formatter.EmployeeSerializer;
import ru.job4j.ood.srp.model.Employee;

import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {

    private final Report report;
    private final Gson gson;

    public JSONReport(Report report) {
        this.report = report;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Employee.class, new EmployeeSerializer())
                .create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String engineReport = report.generate(filter);
        List<Employee> employees = EmployeeParser.getEmployees(engineReport);
        return gson.toJson(employees);
    }
}
