package com.example.demo.model;

import com.example.demo.exception.TeacherAlreadyExistsException;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "teacher_groups")
public class ClassTeacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String groupName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private List<Teacher> teachers;

    @Column(nullable = false)
    private int maxTeachers;

    @OneToMany(mappedBy = "classTeacher", fetch = FetchType.EAGER)
    private List<Rate> rates = new ArrayList<>();

    public ClassTeacher() {}

    public ClassTeacher(String groupName, int maxTeachers) {
        this.groupName = groupName;
        this.maxTeachers = maxTeachers;
        this.teachers = new ArrayList<>();
        this.rates = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher) {
        if (teachers.contains(teacher)) {
            throw new TeacherAlreadyExistsException("Nauczyciel już istnieje w grupie!");
        }
        if (teachers.size() >= maxTeachers) {
            throw new TeacherAlreadyExistsException("Przekroczono maksymalną liczbę nauczycieli!");
        }
        teachers.add(teacher);
    }

    public void addSalary(Teacher teacher, double amount) {
        teachers.stream()
                .filter(t -> t.equals(teacher))
                .findFirst()
                .ifPresent(t -> t.setSalary(t.getSalary() + amount));
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public void changeCondition(Teacher teacher, TeacherCondition condition) {
        teachers.stream()
                .filter(t -> t.equals(teacher))
                .findFirst()
                .ifPresent(t -> t.setCondition(condition));
    }

    public void addRate(int rate) {
        rates.add(new Rate(rate, this));
    }

    public Optional<Teacher> search(String lastName) {
        return teachers.stream()
                .filter(t -> t.getLastName().equals(lastName))
                .findFirst();
    }

    public List<Teacher> searchPartial(String query) {
        return teachers.stream()
                .filter(t -> t.getLastName().contains(query) ||
                        t.getFirstName().contains(query))
                .toList();
    }

    public long countByCondition(TeacherCondition condition) {
        return teachers.stream()
                .filter(t -> t.getCondition() == condition)
                .count();
    }

    public void summary() {
        teachers.forEach(Teacher::printing);
    }

    public List<Teacher> sortByName() {
        return teachers.stream()
                .sorted()
                .toList();
    }

    public List<Teacher> sortBySalary() {
        return teachers.stream()
                .sorted((t1, t2) -> Double.compare(t2.getSalary(), t1.getSalary()))
                .toList();
    }

    public Teacher max() {
        return Collections.max(teachers);
    }

    public Long getId() {
        return id;
    }

    //public List<Integer> getRates() {
      //  return rates;
    //}

    //public void setRates(List<Integer> rates) {
    //    this.rates = rates;
    //}

    public String getGroupName() {
        return groupName;
    }

    public int getCurrentSize() {
        return teachers.size();
    }

    public int getMaxTeachers() {
        return maxTeachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}