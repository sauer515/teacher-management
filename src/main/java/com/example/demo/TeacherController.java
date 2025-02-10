package com.example.demo;

import com.example.demo.model.ClassTeacher;
import com.example.demo.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/teacher")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher,
                                             @RequestParam String groupName) {
        return teacherService.addTeacher(teacher, groupName);
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<String> removeTeacher(@PathVariable String id,
                              @RequestParam String groupName) {
        return teacherService.removeTeacher(id, groupName);
    }

    @GetMapping("/teacher/csv")
    public ResponseEntity<String> getAllTeachersCSV() {
        return teacherService.getAllTeachersCSV();
    }

    @GetMapping("/group")
    public ResponseEntity<Set<String>> getAllGroups() {
        return ResponseEntity.ok(teacherService.getAllGroups());
    }

    @PostMapping("/group")
    public ResponseEntity<ClassTeacher> addGroup(@RequestParam String name,
                                                 @RequestParam double capacity) {
        return teacherService.addGroup(name, capacity);
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<String> removeGroup(@PathVariable String id) {
        return teacherService.removeGroup(id);
    }

    @GetMapping("/group/{id}/teacher")
    public ResponseEntity<List<Teacher>> getTeachersInGroup(@PathVariable String id) {
        return teacherService.getTeachersInGroup(id);
    }

    @GetMapping("/group/{id}/fill")
    public ResponseEntity<String> getGroupFillPercentage(@PathVariable String id) {
        return teacherService.getGroupFillPercentage(id);
    }

    /*@PostMapping("/rating")
    public ResponseEntity<String> addRating(@RequestParam String groupId,
                                            @RequestParam int rating) {
        return teacherService.addRating(groupId, rating);
    }*/
}
