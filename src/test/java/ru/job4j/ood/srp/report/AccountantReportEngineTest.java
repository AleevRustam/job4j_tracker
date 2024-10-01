package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.assertThat;

class AccountantReportEngineTest {

    @Test
    public void whenAccountantReportGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee emp1 = new Employee("Ivan", now, now, 100);
        Employee emp2 = new Employee("Petr", now, now, 200);
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        store.add(emp1);
        store.add(emp2);

        Report engineReport = new ReportEngine(store, dateTimeParser);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Currency targetCurrency = Currency.USD;

        Report accountantReport = new AccountantReportEngine(engineReport, converter, targetCurrency);

        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary (in USD);")
                .append(System.lineSeparator())
                .append(emp1.getName()).append(" ")
                .append(dateTimeParser.parse(emp1.getHired())).append(" ")
                .append(dateTimeParser.parse(emp1.getFired())).append(" ")
                .append(String.format("%.2f", converter.convert(Currency.RUB, emp1.getSalary(), targetCurrency)))
                .append(System.lineSeparator())
                .append(emp2.getName()).append(" ")
                .append(dateTimeParser.parse(emp2.getHired())).append(" ")
                .append(dateTimeParser.parse(emp2.getFired())).append(" ")
                .append(String.format("%.2f", converter.convert(Currency.RUB, emp2.getSalary(), targetCurrency)))
                .append(System.lineSeparator());

        assertThat(accountantReport.generate(employee -> true)).isEqualTo(expected.toString());
    }

}