package com.demo.studentmanage.dto.converter;

import com.demo.studentmanage.dto.StudentDTO;
import com.demo.studentmanage.dto.StudentSubjectDTO;
import com.demo.studentmanage.dto.SubjectDTO;
import com.demo.studentmanage.model.Student;
import com.demo.studentmanage.model.StudentSubject;
import com.demo.studentmanage.model.Subject;
import org.springframework.beans.BeanUtils;

/**
 * 数据类型转换器
 * @author kuangjiahua
 * @date   2021/01/04
 */
public class StudentSubjectConverter {

    public static StudentSubject convertDtoToModel(StudentSubjectDTO studentSubjectDTO){
        StudentSubject studentSubject = new StudentSubject();
        BeanUtils.copyProperties(studentSubjectDTO, studentSubject);
        Student student = new Student();
        BeanUtils.copyProperties(studentSubjectDTO.getStudentDTO(), student);
        studentSubject.setStudent(student);
        Subject subject = new Subject();
        BeanUtils.copyProperties(studentSubjectDTO.getSubjectDTO(), subject);
        studentSubject.setSubject(subject);
        return studentSubject;
    }


}
