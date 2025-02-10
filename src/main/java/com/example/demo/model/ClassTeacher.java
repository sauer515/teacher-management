package com.example.demo.model;

import java.util.*;

public class ClassTeacher {
    private Long id;
    private String groupName;
    private List<Teacher> teachers;
    private int maxTeachers;
    private List<Integer> rates;
    private static long nextId = 1;

    public ClassTeacher(String groupName, int maxTeachers) {
        this.id = nextId++;
        this.groupName = groupName;
        this.maxTeachers = maxTeachers;
        this.teachers = new ArrayList<>();
        this.rates = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher) {
        if (teachers.contains(teacher)) {
            System.out.println("Nauczyciel już istnieje w grupie!");
            return;
        }
        if (teachers.size() >= maxTeachers) {
            System.out.println("Przekroczono maksymalną liczbę nauczycieli!");
            return;
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
        rates.add(rate);
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

    public List<Integer> getRates() {
        return rates;
    }

    public void setRates(List<Integer> rates) {
        this.rates = rates;
    }

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
        return new ArrayList<>(teachers);
    }
}