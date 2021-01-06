package com.demo.studentmanage.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 表名：teacher_subject 教师课程关联表
 * @author kuangjiahua
 * @date   2021/01/05
 */
@Entity
@Data
@Table(name = "teacher_subject")
public class TeacherSubject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "school_year")
    private String schoolYear;
}
