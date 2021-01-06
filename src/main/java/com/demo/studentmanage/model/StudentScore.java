package com.demo.studentmanage.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 表名：score 学生分数表
 * @author kuangjiahua
 * @date   2021/01/05
 */
@Data
@Entity
@Table(name = "score")
public class StudentScore {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "score")
    private Double score;

    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "school_year")
    private String schoolYear;

}
