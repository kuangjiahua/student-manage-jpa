package com.demo.studentmanage.repository.impl;

import com.demo.studentmanage.dto.SchoolScoreDto;
import com.demo.studentmanage.dto.TeacherSubjectDto;
import com.demo.studentmanage.dto.converter.SchoolScoreConverter;
import com.demo.studentmanage.dto.converter.TeacherScoreConverter;
import com.demo.studentmanage.model.TeacherSubject;
import com.demo.studentmanage.repository.TeacherSubjectMapper;
import com.demo.studentmanage.repository.TeacherSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TeacherSubjectMapperImpl implements TeacherSubjectMapper {

    private static final String AVG = "avg";
    private static final String MAX = "max";
    private static final String MIN = "min";

    @Autowired
    public EntityManager entityManager;

    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;

    @Override
    public List<SchoolScoreDto> findSchoolTeacherAvgScore(SchoolScoreDto schoolScoreDto) {
        return getSchoolTeacherScoreByType(schoolScoreDto, AVG);
    }

    @Override
    public List<SchoolScoreDto> findSchoolTeacherMaxScore(SchoolScoreDto schoolScoreDto) {
        return getSchoolTeacherScoreByType(schoolScoreDto, MAX);
    }

    @Override
    public List<SchoolScoreDto> findSchoolTeacherMinScore(SchoolScoreDto schoolScoreDto) {
        return getSchoolTeacherScoreByType(schoolScoreDto, MIN);
    }

    @Override
    public List<TeacherSubjectDto> listAvgScore(TeacherSubjectDto teacherSubjectDto) {
        return listScoreByType( teacherSubjectDto, AVG);
    }

    @Override
    public List<TeacherSubjectDto> listMaxScore(TeacherSubjectDto teacherSubjectDto) {
        return listScoreByType( teacherSubjectDto, MAX);
    }

    @Override
    public List<TeacherSubjectDto> listMinScore(TeacherSubjectDto teacherSubjectDto) {
        return listScoreByType( teacherSubjectDto, MIN);
    }

    @Override
    public void saveTeacherSubject(TeacherSubject teacherSubject) {
        teacherSubjectRepository.save(teacherSubject);
    }

    @Override
    public void updateTeacherSubject(TeacherSubjectDto teacherSubjectDto) {
        TeacherSubject teacherSubject = teacherSubjectRepository.findById(teacherSubjectDto.getId()).orElse(null);
        teacherSubject.setTeacherId(teacherSubjectDto.getTeacherId());
        teacherSubject.setSchoolYear(teacherSubjectDto.getSchoolYear());
        teacherSubject.setSubjectId(teacherSubjectDto.getSubjectId());
        teacherSubjectRepository.save(teacherSubject);

    }

    @Override
    public TeacherSubject findById(Integer id) {
        return teacherSubjectRepository.findById(id).orElse(null);
    }

    @Override
    public TeacherSubject findByCondition(TeacherSubjectDto teacherSubjectDto) {
        Specification<TeacherSubject> specification = (Specification<TeacherSubject>) (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("teacherId"), teacherSubjectDto.getTeacherId()));
            list.add(criteriaBuilder.equal(root.get("subjectId"), teacherSubjectDto.getSubjectId()));
            list.add(criteriaBuilder.equal(root.get("schoolYear"), teacherSubjectDto.getSchoolYear()));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        return teacherSubjectRepository.findOne(specification).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        teacherSubjectRepository.deleteById(id);
    }

    private List<TeacherSubjectDto> listScoreByType(TeacherSubjectDto teacherSubjectDto, String type) {
        StringBuffer sb =  new StringBuffer(" select ");
        if(AVG.equals(type)){
            sb.append(" avg(score) ");
        } else if( MAX.equals(type)) {
            sb.append(" max(score) ");
        } else if( MIN.equals(type)){
            sb.append(" min(score) ");
        }
        sb.append(" as score , a.subject_id as subjectId ");
        sb.append(" from score a inner join teacher_subject b  on a.subject_id = b.subject_id ");
        sb.append(" and a.school_year = b.school_year ");
        sb.append(" where b.teacher_id = ").append(teacherSubjectDto.getTeacherId());
        sb.append(" and b.school_year = ").append(teacherSubjectDto.getSchoolYear());
        sb.append(" group by a.subject_id limit ").append(teacherSubjectDto.getStart()).append(teacherSubjectDto.getSize());
        List<Object[]> list = entityManager.createNativeQuery(sb.toString()).getResultList();
        return list.stream().map(TeacherScoreConverter::converterObjectToDto).collect(Collectors.toList());
    }

    private List<SchoolScoreDto> getSchoolTeacherScoreByType(SchoolScoreDto schoolScoreDto, String type){
        StringBuilder sb = new StringBuilder("select ");
        if(AVG.equals(type)){
            sb.append(" avg(score) ");
        } else if( MAX.equals(type)) {
            sb.append(" max(score) ");
        } else if( MIN.equals(type)){
            sb.append(" min(score) ");
        }
        sb.append(" as score, a.subject_id as subjectId, b.teacher_id as teacherId  " );
        sb.append(" from score a inner join teacher_subject b ");
        sb.append(" on a.subject_id = b.subject_id and a.school_year = b.school_year ");
        sb.append(" where a.school_year = ").append(schoolScoreDto.getSchoolYear());
        sb.append(" group by a.subject_id, teacher_id limit ").append(schoolScoreDto.getStart()).append(schoolScoreDto.getSize());
        List<Object[]> list = entityManager.createNativeQuery(sb.toString()).getResultList();
        return list.stream().map(SchoolScoreConverter::convertSchoolTeacherScoreDto).collect(Collectors.toList());
    }
}
