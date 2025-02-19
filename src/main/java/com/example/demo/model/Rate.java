package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "rates")
public class Rate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private int value;

    @ManyToOne
    @JoinColumn(name = "class_teacher_id")
    private ClassTeacher classTeacher;

    public Rate() {}

    public Rate(int value, ClassTeacher classTeacher) {
        if (value < 0 || value > 6) {
            throw new IllegalArgumentException("Ocena musi być w zakresie 0-6.");
        }
        this.value = value;
        this.classTeacher = classTeacher;
    }

    public Long getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ClassTeacher getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(ClassTeacher classTeacher) {
        this.classTeacher = classTeacher;
    }
}
