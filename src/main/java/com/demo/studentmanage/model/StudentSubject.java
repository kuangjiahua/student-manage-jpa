package com.demo.studentmanage.model;

import lombok.Data;

@Data
public class StudentSubject {

    private Integer id;

    private Integer studentId;

    private Integer subjectId;

    private Student student;

    private Subject subject;

    private String schoolYear;
}
