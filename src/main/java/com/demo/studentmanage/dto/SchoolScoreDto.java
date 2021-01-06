package com.demo.studentmanage.dto;

import com.demo.studentmanage.dto.common.PageRequest;
import lombok.Data;

/**
 * 教务主任查询分数请求对象
 * @author kuangjiahua
 * @date   2021/01/05
 */
@Data
public class SchoolScoreDto extends PageRequest {

    private String schoolYear;

    private Double score;

    private Integer subjectId;

    private Integer teacherId;


}
