package com.demo.studentmanage.query;

import com.demo.studentmanage.dto.common.PageRequest;
import lombok.Data;

import java.util.List;

/**
 * @author kuangjaihua
 * @date   2020/01/07
 */
@Data
public class ScoreQuery extends PageRequest {

    private Integer queryType;

    private List<String> studentIdList;

    private List<String> schoolYearList;

    private List<String> subjectIdList;

    private List<String> teacherIdList;


}
