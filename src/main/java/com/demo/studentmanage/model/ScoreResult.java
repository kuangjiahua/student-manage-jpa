package com.demo.studentmanage.model;

import lombok.Data;

@Data
public class ScoreResult {

    private Integer studentId;

    private Integer teacherId;

    private Integer subjectId;

    private String schoolYear;

    private Double score;
}
