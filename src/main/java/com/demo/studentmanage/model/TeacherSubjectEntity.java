package com.demo.studentmanage.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "teacher_subject")
public class TeacherSubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "school_year")
    private String schoolYear;
}
