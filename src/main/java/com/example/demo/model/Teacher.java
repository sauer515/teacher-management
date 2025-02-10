package com.example.demo.model;

import java.util.Objects;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teachers")
public class Teacher implements Comparable<Teacher>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "teacher_condition", nullable = false)
    @Enumerated(EnumType.STRING)
    private TeacherCondition condition;
    @Column(name = "birth_year")
    private int birthYear;
    @Column
    private double salary;

    public Teacher() {} //hibernate constructor

    public Teacher(String firstName, String lastName, TeacherCondition condition,
                   int birthYear, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.condition = condition;
        this.birthYear = birthYear;
        this.salary = salary;
    }

    // Getters and setters
    public Long getId() {return id;}
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public TeacherCondition getCondition() { return condition; }
    public int getBirthYear() { return birthYear; }
    public double getSalary() { return salary; }

    public void setSalary(double salary) { this.salary = salary; }
    public void setCondition(TeacherCondition condition) { this.condition = condition; }

    public void printing() {
        System.out.printf("Nauczyciel: %s %s%n", firstName, lastName);
        System.out.printf("Stan: %s%n", condition.getDescription());
        System.out.printf("Rok urodzenia: %d%n", birthYear);
        System.out.printf("Wynagrodzenie: %.2f zł%n", salary);
    }

    @Override
    public int compareTo(Teacher other) {
        return this.lastName.compareTo(other.lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(firstName, teacher.firstName) &&
                Objects.equals(lastName, teacher.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
