package com.demo.studentmanage.mapper.impl;
import com.demo.studentmanage.model.Teacher;
import com.demo.studentmanage.model.TeacherSubject;
import com.demo.studentmanage.mapper.repository.TeacherRepository;
import com.demo.studentmanage.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

@Service
public class TeacherMapperImpl implements TeacherMapper {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher read(Integer id) {
        return teacherRepository.findById(id).orElse(null);
    }

}
