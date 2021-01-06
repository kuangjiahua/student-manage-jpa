package com.demo.studentmanage.dto.converter;

import com.demo.studentmanage.dto.TeacherSubjectDto;
import com.demo.studentmanage.model.TeacherSubject;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * 数据类型转换器
 * @author kuangjiahua
 * @date   2021/01/04
 */
public class TeacherScoreConverter {

    public static TeacherSubject convertDtoToModel(TeacherSubjectDto dto){
        TeacherSubject teacherSubject = new TeacherSubject();
        BeanUtils.copyProperties(dto, teacherSubject);
        return teacherSubject;
    }

    public static TeacherSubjectDto converterObjectToDto(Object[] object){
        TeacherSubjectDto teacherSubjectDto = new TeacherSubjectDto();
        teacherSubjectDto.setScore(((BigDecimal)object[0]).doubleValue());
        teacherSubjectDto.setSubjectId((Integer)object[1]);
        return teacherSubjectDto;
    }
}
