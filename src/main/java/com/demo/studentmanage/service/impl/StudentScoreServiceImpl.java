package com.demo.studentmanage.service.impl;

import com.demo.studentmanage.dto.StudentScoreDto;
import com.demo.studentmanage.dto.converter.StudentScoreConverter;
import com.demo.studentmanage.model.StudentScore;
import com.demo.studentmanage.repository.StudentScoreRepository;
import com.demo.studentmanage.service.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuangjiahua
 * @date   2021/01/04
 */
@Service
public class StudentScoreServiceImpl implements StudentScoreService {

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    @Override
    public List<StudentScoreDto> listScorePage(StudentScoreDto studentScoreDto) {
        Specification<StudentScore> specification = (Specification<StudentScore>) (root, query, criteriaBuilder) -> {
            query.multiselect(root.get("score"), root.get("subjectId"));
            ArrayList<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("studentId"), studentScoreDto.getStudentId()));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        Pageable pageable = PageRequest.of(studentScoreDto.getStart(),studentScoreDto.getSize());
        Page<StudentScore> page = studentScoreRepository.findAll(specification, pageable);
        return page.stream().map(StudentScoreConverter::convertModelToDto).collect(Collectors.toList());
    }

    @Override
    public boolean saveStudentScore(StudentScoreDto studentScoreDto) {
        //校验当前学生分数信息是否已经存在
        StudentScore studentExist = findExistSchoolScore(studentScoreDto);
        if(studentExist != null){
            return false;
        }
        StudentScore studentScore = StudentScoreConverter.convertDtoToModel(studentScoreDto);
        studentScoreRepository.save(studentScore);
        return true;
    }

    @Override
    public boolean updateStudentScore(StudentScoreDto studentScoreDto) {
        //校验当前id对应记录是否存在
        StudentScore scoreExist = studentScoreRepository.findById(studentScoreDto.getId()).orElse(null);
        if(scoreExist == null){
            return false;
        }
        //校验当前学生信息是否重复
        StudentScore scoreRepeat = findExistSchoolScore(studentScoreDto);
        if(scoreRepeat != null && !scoreRepeat.getId().equals(studentScoreDto.getId())){
            return false;
        }
        scoreExist.setSchoolYear(studentScoreDto.getSchoolYear());
        scoreExist.setStudentId(studentScoreDto.getStudentId());
        scoreExist.setSubjectId(studentScoreDto.getSubjectId());
        scoreExist.setScore(studentScoreDto.getScore());
        studentScoreRepository.save(scoreExist);
        return false;
    }

    private StudentScore findExistSchoolScore(StudentScoreDto studentScoreDto){
        Specification<StudentScore> specification = (Specification<StudentScore>) (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("studentId"), studentScoreDto.getStudentId()));
            list.add(criteriaBuilder.equal(root.get("subjectId"), studentScoreDto.getSubjectId()));
            list.add(criteriaBuilder.equal(root.get("schoolYear"), studentScoreDto.getSchoolYear()));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        return studentScoreRepository.findOne(specification).orElse(null);
    }

    @Override
    public void deleteStudentScore(int id) {
        studentScoreRepository.deleteById(id);
    }
}
