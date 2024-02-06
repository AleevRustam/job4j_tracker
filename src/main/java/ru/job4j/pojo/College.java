package ru.job4j.pojo;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Rustam Aleev");
        student.setGroup("PE1-91");
        student.setDataOfAdmission("08.08.1991");
        System.out.println(student.getFullName() + " учится в группе " + student.getGroup() + " с " + student.getDataOfAdmission());
    }
}
