package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.EmployeeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XMLReport implements Report {

    private final Report report;

    public XMLReport(Report report) {
        this.report = report;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String engineReport = report.generate(filter);
        List<Employee> employees = EmployeeParser.getEmployees(engineReport);
        Employees employeesWrapper = new Employees(employees);
        return convertToXml(employeesWrapper);
    }

    private String convertToXml(Employees employeesWrapper) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter writer = new StringWriter();
            marshaller.marshal(employeesWrapper, writer);
            return writer.getBuffer().toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return "";
        }
    }
}
