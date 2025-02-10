package com.example.demo.model;

import java.util.*;

public class ClassContainer {
    private Map<String, ClassTeacher> groups;

    public ClassContainer() {
        this.groups = new HashMap<>();
    }

    public void addClass(String name, double capacity) {
        groups.put(name, new ClassTeacher(name, (int)capacity));
    }

    public void removeClass(String name) {
        groups.remove(name);
    }

    public List<String> findEmpty() {
        return groups.entrySet().stream()
                .filter(e -> e.getValue().getCurrentSize() == 0)
                .map(Map.Entry::getKey)
                .toList();
    }

    public void summary() {
        groups.forEach((name, group) -> {
            double fillPercentage = (double) group.getCurrentSize() / group.getMaxTeachers() * 100;
            System.out.printf("Grupa: %s, Zapełnienie: %.2f%%%n", name, fillPercentage);
        });
    }

    public Map<String, ClassTeacher> getGroups() {
        return new HashMap<>(groups);
    }
}
