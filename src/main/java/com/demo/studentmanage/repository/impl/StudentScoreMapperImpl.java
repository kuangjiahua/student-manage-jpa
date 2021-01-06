package com.demo.studentmanage.repository.impl;

import com.demo.studentmanage.dto.SchoolScoreDto;
import com.demo.studentmanage.dto.StudentScoreDto;
import com.demo.studentmanage.dto.converter.SchoolScoreConverter;
import com.demo.studentmanage.model.StudentScore;
import com.demo.studentmanage.repository.StudentScoreMapper;
import com.demo.studentmanage.repository.StudentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentScoreMapperImpl implements StudentScoreMapper {

    private static final String AVG = "avg";
    private static final String MAX = "max";
    private static final String MIN = "min";

    @Autowired
    public EntityManager entityManager;

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    @Override
    public List<SchoolScoreDto> findSchoolAvgScore(SchoolScoreDto schoolScoreDto) {
        return getSchoolScoreByType(schoolScoreDto, AVG);
    }

    @Override
    public List<SchoolScoreDto> findSchoolMaxScore(SchoolScoreDto schoolScoreDto) {
        return getSchoolScoreByType(schoolScoreDto, MAX);
    }

    @Override
    public List<SchoolScoreDto> findSchoolMinScore(SchoolScoreDto schoolScoreDto) {
        return getSchoolScoreByType(schoolScoreDto, MIN);
    }

    @Override
    public StudentScore findById(Integer id) {
        return studentScoreRepository.findById(id).orElse(null);
    }

    @Override
    public Page<StudentScore> listStudentScorePage(StudentScoreDto studentScoreDto) {
        Specification<StudentScore> specification = (Specification<StudentScore>) (root, query, criteriaBuilder) -> {
            query.multiselect(root.get("score"), root.get("subjectId"));
            ArrayList<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("studentId"), studentScoreDto.getStudentId()));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        Pageable pageable = PageRequest.of(studentScoreDto.getStart(),studentScoreDto.getSize());
        return studentScoreRepository.findAll(specification, pageable);
    }

    @Override
    public StudentScore findByCondition(StudentScoreDto studentScoreDto) {
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
    public void saveStudentScore(StudentScore studentScore) {
        studentScoreRepository.save(studentScore);
    }

    @Override
    public void updateStudentScore(StudentScoreDto studentScoreDto) {
        StudentScore scoreExist = studentScoreRepository.findById(studentScoreDto.getId()).orElse(null);
        scoreExist.setSchoolYear(studentScoreDto.getSchoolYear());
        scoreExist.setStudentId(studentScoreDto.getStudentId());
        scoreExist.setSubjectId(studentScoreDto.getSubjectId());
        scoreExist.setScore(studentScoreDto.getScore());
        studentScoreRepository.save(scoreExist);
    }

    @Override
    public void deleteById(int id) {
        studentScoreRepository.deleteById(id);
    }

    public List<SchoolScoreDto> getSchoolScoreByType(SchoolScoreDto schoolScoreDto, String type){
        StringBuilder sb = new StringBuilder("select ");
        if(AVG.equals(type)){
            sb.append(" avg(score) ");
        } else if( MAX.equals(type)) {
            sb.append(" max(score) ");
        } else if( MIN.equals(type)){
            sb.append(" min(score) ");
        }
        sb.append(" as score, subject_id as subjectId from score " );
        sb.append(" where school_year = ").append(schoolScoreDto.getSchoolYear());
        sb.append(" group by subject_id limit ").append(schoolScoreDto.getStart()).append(schoolScoreDto.getSize());
        List<Object[]> list = entityManager.createNativeQuery(sb.toString()).getResultList();
        return list.stream().map(SchoolScoreConverter::convertSchoolScoreToDto).collect(Collectors.toList());
    }
}
