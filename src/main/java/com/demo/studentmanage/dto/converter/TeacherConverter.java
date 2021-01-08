package com.demo.studentmanage.dto.converter;

import com.demo.studentmanage.dto.TeacherSubjectDTO;
import com.demo.studentmanage.model.Subject;
import com.demo.studentmanage.model.Teacher;
import com.demo.studentmanage.model.TeacherSubject;
import org.springframework.beans.BeanUtils;

public class TeacherConverter {

    public static TeacherSubject converterDtoToModel(TeacherSubjectDTO teacherSubjectDTO){
        TeacherSubject teacherSubject = new TeacherSubject();
        if(teacherSubjectDTO.getTeacherDTO() != null) {
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacherSubjectDTO.getTeacherDTO(), teacher);
            teacherSubject.setTeacher(teacher);
        }
        if(teacherSubjectDTO.getSubjectDTO() != null) {
            Subject subject = new Subject();
            BeanUtils.copyProperties(teacherSubjectDTO.getSubjectDTO(), subject);
            teacherSubject.setSubject(subject);
        }
        BeanUtils.copyProperties(teacherSubjectDTO, teacherSubject);
        return teacherSubject;
    }


}
