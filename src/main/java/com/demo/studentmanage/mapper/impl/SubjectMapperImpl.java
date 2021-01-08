package com.demo.studentmanage.mapper.impl;

import com.demo.studentmanage.model.Subject;
import com.demo.studentmanage.mapper.repository.SubjectRepository;
import com.demo.studentmanage.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectMapperImpl implements SubjectMapper {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public Subject read(Integer id) {
        return subjectRepository.findById(id).orElse(null);
    }
}
