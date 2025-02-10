package com.example.demo;

import com.example.demo.model.ClassTeacher;
import com.example.demo.model.Teacher;
import com.example.demo.model.TeacherCondition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeacherController.class)
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private ObjectMapper objectMapper;

    private Teacher testTeacher;
    private ClassTeacher testGroup;

    @BeforeEach
    void setUp() {
        testTeacher = new Teacher("Jan", "Kowalski", TeacherCondition.PRESENT, 1980, 5000.0);
        testGroup = new ClassTeacher("Grupa1", 30);
    }

    @Test
    void addTeacher_Success() throws Exception {
        when(teacherService.addTeacher(any(Teacher.class), anyString()))
                .thenReturn(ResponseEntity.ok("Nauczyciel dodany"));

        mockMvc.perform(post("/api/teacher")
                        .param("groupName", "Grupa1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTeacher)))
                .andExpect(status().isOk())
                .andExpect(content().string("Nauczyciel dodany"));
    }

    @Test
    void removeTeacher_Success() throws Exception {
        when(teacherService.removeTeacher(anyString(), anyString()))
                .thenReturn(ResponseEntity.ok("Nauczyciel usunięty"));

        mockMvc.perform(delete("/api/teacher/123")
                        .param("groupName", "Grupa1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Nauczyciel usunięty"));
    }

    @Test
    void getAllTeachersCSV_Success() throws Exception {
        String csvContent = "FirstName,LastName,Condition,BirthYear,Salary\nJan,Kowalski,obecny,1980,5000.00\n";
        when(teacherService.getAllTeachersCSV())
                .thenReturn(ResponseEntity.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(csvContent));

        mockMvc.perform(get("/api/teacher/csv"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string(csvContent));
    }

    @Test
    void getAllGroups_Success() throws Exception {
        Set<String> groups = new HashSet<>(Arrays.asList("Grupa1", "Grupa2"));
        when(teacherService.getAllGroups()).thenReturn(groups);

        mockMvc.perform(get("/api/group"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(groups)));
    }

    @Test
    void addGroup_Success() throws Exception {
        when(teacherService.addGroup(anyString(), anyDouble()))
                .thenReturn(ResponseEntity.ok(testGroup));

        mockMvc.perform(post("/api/group")
                        .param("name", "Grupa1")
                        .param("capacity", "30.0"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(testGroup)));
    }

    @Test
    void removeGroup_Success() throws Exception {
        when(teacherService.removeGroup(anyString()))
                .thenReturn(ResponseEntity.ok("Grupa usunięta"));

        mockMvc.perform(delete("/api/group/Grupa1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Grupa usunięta"));
    }

    @Test
    void getTeachersInGroup_Success() throws Exception {
        List<Teacher> teachers = Arrays.asList(testTeacher);
        when(teacherService.getTeachersInGroup(anyString()))
                .thenReturn(ResponseEntity.ok(teachers));

        mockMvc.perform(get("/api/group/Grupa1/teacher"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(teachers)));
    }

    @Test
    void getGroupFillPercentage_Success() throws Exception {
        when(teacherService.getGroupFillPercentage(anyString()))
                .thenReturn(ResponseEntity.ok("33.33%"));

        mockMvc.perform(get("/api/group/Grupa1/fill"))
                .andExpect(status().isOk())
                .andExpect(content().string("33.33%"));
    }

    @Test
    void addRating_Success() throws Exception {
        when(teacherService.addRating(anyString(), anyInt()))
                .thenReturn(ResponseEntity.ok("Ocena dodana"));

        mockMvc.perform(post("/api/rating")
                        .param("groupId", "Grupa1")
                        .param("rating", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ocena dodana"));
    }

    // Testy dla przypadków błędów
    @Test
    void addTeacher_GroupNotFound() throws Exception {
        when(teacherService.addTeacher(any(Teacher.class), anyString()))
                .thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(post("/api/teacher")
                        .param("groupName", "NieistniejącaGrupa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testTeacher)))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTeachersInGroup_GroupNotFound() throws Exception {
        when(teacherService.getTeachersInGroup(anyString()))
                .thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(get("/api/group/NieistniejącaGrupa/teacher"))
                .andExpect(status().isNotFound());
    }
}