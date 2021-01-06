package com.demo.studentmanage.dto.converter;

import com.demo.studentmanage.dto.SchoolScoreDto;

import java.math.BigDecimal;

/**
 * 数据类型转换器
 * @author kuangjiahua
 * @date   2021/01/04
 */
public class SchoolScoreConverter {

    public static SchoolScoreDto convertSchoolScoreToDto(Object[] object){
        SchoolScoreDto schoolScoreDto = new SchoolScoreDto();
        schoolScoreDto.setScore(((BigDecimal)object[0]).doubleValue());
        schoolScoreDto.setSubjectId((Integer)object[1]);
        return schoolScoreDto;
    }

    public static SchoolScoreDto convertSchoolTeacherScoreDto(Object[] object){
        SchoolScoreDto schoolScoreDto = new SchoolScoreDto();
        schoolScoreDto.setScore(((BigDecimal)object[0]).doubleValue());
        schoolScoreDto.setSubjectId((Integer)object[1]);
        schoolScoreDto.setTeacherId((Integer)object[2]);
        return schoolScoreDto;
    }

}
