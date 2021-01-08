package com.demo.studentmanage.dto;

import com.demo.studentmanage.dto.common.PageRequest;
import com.demo.studentmanage.model.Subject;
import com.demo.studentmanage.model.Teacher;
import lombok.Data;

/**
 * @author kuangjiahua
 * @date 2020/01/07
 */
@Data
public class TeacherSubjectDTO {

    private Integer id;

    private Integer teacherId;

    private Integer subjectId;

    private String schoolYear;

    private TeacherDTO teacherDTO;

    private SubjectDTO subjectDTO;

}
