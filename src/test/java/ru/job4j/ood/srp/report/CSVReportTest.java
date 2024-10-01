package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class CSVReportTest {

    @Test
    public void whenCSVGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Ivan", now, now, 100);
        Employee emp2 = new Employee("Petr", now, now, 200);
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        store.add(emp1);
        store.add(emp2);

        Report engineReport = new ReportEngine(store, dateTimeParser);
        Report csvReport = new CSVReport(engineReport);

        StringBuilder expected = new StringBuilder()
                .append("Name,Hired,Fired,Salary")
                .append(System.lineSeparator())
                .append(emp1.getName()).append(",")
                .append(dateTimeParser.parse(emp1.getHired())).append(",")
                .append(dateTimeParser.parse(emp1.getFired())).append(",")
                .append(emp1.getSalary())
                .append(System.lineSeparator())
                .append(emp2.getName()).append(",")
                .append(dateTimeParser.parse(emp2.getHired())).append(",")
                .append(dateTimeParser.parse(emp2.getFired())).append(",")
                .append(emp2.getSalary())
                .append(System.lineSeparator());

        assertThat(csvReport.generate(employee -> true)).isEqualTo(expected.toString());
    }

}