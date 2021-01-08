package com.demo.studentmanage.dto;

import lombok.Data;

/**
 * @author kuangjiahua
 * @date 2020/01/07
 */
@Data
public class ScoreResultDTO {

    private Integer studentId;

    private Integer teacherId;

    private Integer subjectId;

    private String schoolYear;

    private Double score;



}
