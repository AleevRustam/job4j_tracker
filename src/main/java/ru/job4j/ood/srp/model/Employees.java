package ru.job4j.ood.srp.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
public class Employees {

    private List<EmployeeWrapper> employees;

    public Employees() {

    }

    public Employees(List<Employee> employees) {
        this.employees = employees.stream()
                .map(EmployeeWrapper::new)
                .toList();
    }

    @XmlElement(name = "employee")
    public List<EmployeeWrapper> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeWrapper> employees) {
        this.employees = employees;
    }
}
