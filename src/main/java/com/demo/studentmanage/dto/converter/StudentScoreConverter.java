package com.demo.studentmanage.dto.converter;

import com.demo.studentmanage.dto.StudentScoreDto;
import com.demo.studentmanage.model.StudentScore;
import org.springframework.beans.BeanUtils;

/**
 * 数据类型转换器
 * @author kuangjiahua
 * @date   2021/01/04
 */
public class StudentScoreConverter {

    public static StudentScoreDto convertModelToDto(StudentScore studentScore){
        StudentScoreDto dto = new StudentScoreDto();
        BeanUtils.copyProperties(studentScore, dto);
        return dto;
    }

    public static StudentScore convertDtoToModel(StudentScoreDto dto){
        StudentScore studentScore = new StudentScore();
        BeanUtils.copyProperties(dto, studentScore);
        return studentScore;
    }

}
