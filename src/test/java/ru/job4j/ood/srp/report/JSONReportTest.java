package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.assertj.core.api.Assertions.assertThat;

class JSONReportTest {
    @Test
    public void whenJsonReportGenerated() {

        MemoryStore store = new MemoryStore();
        Calendar hired = new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41);
        Calendar fired = new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41);
        Employee employee1 = new Employee("Employee1", hired, fired, 5000.0);
        Employee employee2 = new Employee("Employee2", hired, fired, 6000.0);
        store.add(employee1);
        store.add(employee2);
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        Report engineReport = new ReportEngine(store, dateTimeParser);
        Report jsonReport = new JSONReport(engineReport);

        String expected = """
                [
                  {
                    "name": "Employee1",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 5000.0
                  },
                  {
                    "name": "Employee2",
                    "hired": "08:06:2023 17:41",
                    "fired": "08:06:2023 17:41",
                    "salary": 6000.0
                  }
                ]""";

        assertThat(jsonReport.generate(employee -> true)).isEqualTo(expected);
    }
}