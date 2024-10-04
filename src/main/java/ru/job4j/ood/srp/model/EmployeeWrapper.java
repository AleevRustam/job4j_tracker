package ru.job4j.ood.srp.model;

import ru.job4j.ood.srp.formatter.DateTimeXMLParser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Calendar;

@XmlRootElement(name = "employee")
@XmlType(propOrder = {"name", "hired", "fired", "salary"})
public class EmployeeWrapper {
    private String name;
    private Calendar hired;
    private Calendar fired;
    private double salary;

    public EmployeeWrapper() {
    }

    public EmployeeWrapper(Employee employee) {
        this.name = employee.getName();
        this.hired = employee.getHired();
        this.fired = employee.getFired();
        this.salary = employee.getSalary();
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    @XmlJavaTypeAdapter(DateTimeXMLParser.class)
    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    @XmlElement
    @XmlJavaTypeAdapter(DateTimeXMLParser.class)
    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    @XmlElement
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
