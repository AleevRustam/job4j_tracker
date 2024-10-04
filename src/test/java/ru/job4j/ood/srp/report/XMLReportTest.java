package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XMLReportTest {
    @Test
    public void whenXMLReportGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar hired = new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41);
        Calendar fired = new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41);
        Employee employee1 = new Employee("Employee1", hired, fired, 5000.0);
        Employee employee2 = new Employee("Employee2", hired, fired, 6000.0);
        store.add(employee1);
        store.add(employee2);
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        Report engineReport = new ReportEngine(store, dateTimeParser);
        Report xmlReport = new XMLReport(engineReport);
        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>Employee1</name>
                        <hired>08:06:2023 17:41</hired>
                        <fired>08:06:2023 17:41</fired>
                        <salary>5000.0</salary>
                    </employee>
                    <employee>
                        <name>Employee2</name>
                        <hired>08:06:2023 17:41</hired>
                        <fired>08:06:2023 17:41</fired>
                        <salary>6000.0</salary>
                    </employee>
                </employees>
                """;
        assertThat(xmlReport.generate(employee -> true)).isEqualTo(expected);
    }
}