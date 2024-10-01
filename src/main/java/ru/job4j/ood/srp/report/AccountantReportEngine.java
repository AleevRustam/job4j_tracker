package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.model.Employee;

import java.util.function.Predicate;

public class AccountantReportEngine implements Report {

    private final Report report;
    private final CurrencyConverter currencyConverter;
    private final Currency targetCurrency;

    public AccountantReportEngine(Report report, CurrencyConverter currencyConverter, Currency targetCurrency) {
        this.report = report;
        this.currencyConverter = currencyConverter;
        this.targetCurrency = targetCurrency;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String engineReport = report.generate(filter);
        StringBuilder accountantReport = new StringBuilder();
        String[] lines = engineReport.split(System.lineSeparator());

        if (lines.length > 0) {
            accountantReport
                    .append(lines[0].replace("Salary", "Salary (in " + targetCurrency + ")"))
                    .append(System.lineSeparator());
        }

        for (int i = 1; i < lines.length; i++) {
            String[] columns = lines[i].split(" ");
            if (columns.length >= 5) {
                double originalSalary = Double.parseDouble(columns[columns.length - 1]);
                double convertedSalary = currencyConverter.convert(Currency.RUB, originalSalary, targetCurrency);
                columns[columns.length - 1] = String.format("%.2f", convertedSalary);
                accountantReport.append(String.join(" ", columns)).append(System.lineSeparator());
            }
        }

        return accountantReport.toString();
    }
}
