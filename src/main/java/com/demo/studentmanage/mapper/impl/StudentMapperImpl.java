package com.demo.studentmanage.mapper.impl;

import com.demo.studentmanage.model.Student;
import com.demo.studentmanage.mapper.repository.StudentRepository;
import com.demo.studentmanage.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentMapperImpl implements StudentMapper {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student read(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }
}
