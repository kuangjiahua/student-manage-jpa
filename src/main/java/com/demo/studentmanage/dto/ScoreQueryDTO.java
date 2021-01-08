package com.demo.studentmanage.dto;

import com.demo.studentmanage.dto.common.PageRequest;
import lombok.Data;

import java.util.List;

/**
 * @author kuangjiahua
 * @date   2021/01/07
 */
@Data
public class ScoreQueryDTO extends PageRequest {

    private List<String> schoolYearList;

    private List<String> studentIdList;

    private List<String> subjectIdList;

    private List<String> teacherIdList;

    private Integer queryType;


}
