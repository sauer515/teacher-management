package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.model.ClassContainer;
import com.example.demo.model.ClassTeacher;
import com.example.demo.model.Rate;
import com.example.demo.model.Teacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeacherService {
    private ClassContainer container;

    private final TeacherDao teacherDao;
    private final ClassTeacherDao classTeacherDao;
    private final ClassContainerDao classContainerDao;
    private final RateDao rateDao;


    public TeacherService() {
        this.container = new ClassContainer();
        this.teacherDao = new TeacherDao();
        this.classTeacherDao = new ClassTeacherDao();
        this.classContainerDao = new ClassContainerDao();
        this.rateDao = new RateDao();
        LoadDataFromDatabase();
    }

    private void LoadDataFromDatabase() {
        ClassContainer dbContainer = classContainerDao.findAll().stream()
                .findFirst()
                .orElse(new ClassContainer());

        this.container = dbContainer;
    }

    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherDao.findAll());
    }

    public ResponseEntity<String> addTeacher(Teacher teacher, String groupName) {
        ClassTeacher group = container.getGroups().get(groupName);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        group.addTeacher(teacher);
        teacherDao.save(teacher);
        classTeacherDao.update(group);
        return ResponseEntity.ok("Nauczyciel dodany");
    }

    public ResponseEntity<String> removeTeacher(String id,
                                                String groupName) {
        ClassTeacher group = container.getGroups().get(groupName);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        Optional<Teacher> teacher = group.search(id);
        if (teacher.isPresent()) {
            group.removeTeacher(teacher.get());
            teacherDao.delete(teacher.get());
            classTeacherDao.update(group);
            return ResponseEntity.ok("Nauczyciel usunięty");
        }
        return ResponseEntity.notFound().build();
    }

    public Set<String> getAllGroups() {
        return container.getGroups().keySet();
    }

    public ResponseEntity<ClassTeacher> addGroup(String name,
                                                 int capacity) {
        container.addClass(name, capacity);
        ClassTeacher group = container.getGroups().get(name);
        classTeacherDao.save(group);
        classContainerDao.update(container);
        return ResponseEntity.ok(group);
    }

    public ResponseEntity<String> getAllTeachersCSV() {
        StringBuilder csv = new StringBuilder("FirstName,LastName,Condition,BirthYear,Salary\n");
        container.getGroups().values().forEach(group ->
                group.getTeachers().forEach(teacher ->
                        csv.append(String.format("%s,%s,%s,%d,%.2f\n",
                                teacher.getFirstName(),
                                teacher.getLastName(),
                                teacher.getCondition(),
                                teacher.getBirthYear(),
                                teacher.getSalary()))
                )
        );
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv.toString());
    }

    public ResponseEntity<String> removeGroup(String id) {
        ClassTeacher group = container.getGroup(id);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        for (Teacher teacher : group.getTeachers()) {
            teacherDao.delete(teacher);
        }
        for (Rate rate : rateDao.findByClassTeacherId(id)) {
            rateDao.delete(rate);
        }
        container.removeClass(id);
        classTeacherDao.delete(group);
        classContainerDao.update(container);
        return ResponseEntity.ok("Grupa usunięta");
    }

    public ResponseEntity<List<Teacher>> getTeachersInGroup(String id) {
        ClassTeacher group = container.getGroups().get(id);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(group.getTeachers());
    }

    public ResponseEntity<String> getGroupFillPercentage(String id) {
        ClassTeacher group = container.getGroups().get(id);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        double fillPercentage = (double) group.getCurrentSize() / group.getMaxTeachers() * 100;
        String result = String.format("%.2f%%", fillPercentage);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> addRating(String groupId,
                                            int rating) {
        ClassTeacher group = container.getGroups().get(groupId);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        Rate newRate = new Rate(rating, group);
        group.addRate(rating);
        rateDao.save(newRate);
        return new ResponseEntity<>("Ocena dodana", HttpStatus.CREATED);
    }

}
