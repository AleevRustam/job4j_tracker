package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class HRReportTest {

    @Test
    public void whenHRGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Ivan", now, now, 100);
        Employee emp2 = new Employee("Petr", now, now, 200);
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        store.add(emp1);
        store.add(emp2);

        Report engineReport = new ReportEngine(store, dateTimeParser);
        Report hrReport = new HRReport(engineReport);

        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(emp2.getName()).append("; ").append(emp2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(emp1.getName()).append("; ").append(emp1.getSalary()).append(";")
                .append(System.lineSeparator());

        assertThat(hrReport.generate(employee -> true)).isEqualTo(expected.toString());
    }
}