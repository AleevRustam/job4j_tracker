package ru.job4j;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.*;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Store store = new MemoryStore();

        store.add(new Employee("Employee1", new GregorianCalendar(2015, Calendar.JANUARY, 1), new GregorianCalendar(2020, Calendar.JANUARY, 1), 60000));
        store.add(new Employee("JEmployee2", new GregorianCalendar(2016, Calendar.FEBRUARY, 15), new GregorianCalendar(2021, Calendar.FEBRUARY, 15), 55000));
        store.add(new Employee("Employee3", new GregorianCalendar(2017, Calendar.MARCH, 20), new GregorianCalendar(2022, Calendar.MARCH, 20), 70000));
        store.add(new Employee("Employee4", new GregorianCalendar(2018, Calendar.APRIL, 10), new GregorianCalendar(2023, Calendar.APRIL, 10), 75000));
        store.add(new Employee("Employee5", new GregorianCalendar(2019, Calendar.MAY, 5), new GregorianCalendar(2024, Calendar.MAY, 5), 80000));
        store.add(new Employee("Employee6", new GregorianCalendar(2014, Calendar.JUNE, 25), new GregorianCalendar(2019, Calendar.JUNE, 25), 65000));
        store.add(new Employee("Employee7", new GregorianCalendar(2013, Calendar.JULY, 15), new GregorianCalendar(2018, Calendar.JULY, 15), 62000));
        store.add(new Employee("Employee8", new GregorianCalendar(2020, Calendar.AUGUST, 5), new GregorianCalendar(2025, Calendar.AUGUST, 5), 85000));
        store.add(new Employee("Employee9", new GregorianCalendar(2021, Calendar.SEPTEMBER, 12), new GregorianCalendar(2026, Calendar.SEPTEMBER, 12), 90000));
        store.add(new Employee("Employee10", new GregorianCalendar(2022, Calendar.OCTOBER, 30), new GregorianCalendar(2027, Calendar.OCTOBER, 30), 95000));

        Predicate<Employee> filter = employee -> employee.getSalary() > 70000;
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        ReportEngine reportEngine = new ReportEngine(store, dateTimeParser);

        System.out.println(reportEngine.generate(filter));

        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Report accountantReport = new AccountantReportEngine(reportEngine, converter, Currency.EUR);
        System.out.println(accountantReport.generate(filter));

        Report hrReport = new HRReport(reportEngine);
        System.out.println(hrReport.generate(filter));

        Report csvReport = new CSVReport(reportEngine);
        System.out.println(csvReport.generate(filter));

        Report jsonReport = new JSONReport(reportEngine);
        System.out.println(jsonReport.generate(filter));

        Report xmlReport = new XMLReport(reportEngine);
        System.out.println(xmlReport.generate(filter));

    }

}
