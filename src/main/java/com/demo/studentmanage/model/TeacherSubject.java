package com.demo.studentmanage.model;


import lombok.Data;

@Data
public class TeacherSubject {

    private Integer id;

    private Integer teacherId;

    private Integer subjectId;

    private String schoolYear;

    private Teacher teacher;

    private Subject subject;

}
