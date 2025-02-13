package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "groups_container")
public class ClassContainer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapKey(name = "groupName")
    @JoinColumn(name = "container_id")
    private Map<String, ClassTeacher> groups;

    public ClassContainer() {
        this.groups = new HashMap<>();
    }

    public void addClass(String name, int capacity) {
        if (groups.containsKey(name)) {
            System.out.println("Grupa o tej nazwie juz istnieje");
            return;
        }
        groups.put(name, new ClassTeacher(name, capacity));
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

    public ClassTeacher getGroup(String name) {
        return groups.get(name);
    }
}
