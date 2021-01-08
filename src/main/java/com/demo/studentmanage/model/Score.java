package com.demo.studentmanage.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Score {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "score")
    private Double score;

    @Column(name = "school_year")
    private String schoolYear;


}
