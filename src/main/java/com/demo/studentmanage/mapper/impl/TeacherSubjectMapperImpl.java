package com.demo.studentmanage.mapper.impl;
import com.demo.studentmanage.constant.QueryTypeConstant;
import com.demo.studentmanage.dto.converter.ScoreConverter;
import com.demo.studentmanage.model.ScoreResult;
import com.demo.studentmanage.model.TeacherSubject;
import com.demo.studentmanage.model.TeacherSubjectEntity;
import com.demo.studentmanage.query.ScoreQuery;
import com.demo.studentmanage.mapper.TeacherSubjectMapper;
import com.demo.studentmanage.mapper.repository.TeacherSubjectRepository;
import org.apache.tomcat.util.buf.StringUtils;
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

    @Autowired
    public EntityManager entityManager;

    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;

    @Override
    public void save(TeacherSubject teacherSubject) {
        TeacherSubjectEntity entity = new TeacherSubjectEntity();
        entity.setSubjectId(teacherSubject.getSubjectId());
        entity.setTeacherId(teacherSubject.getTeacherId());
        entity.setSchoolYear(teacherSubject.getSchoolYear());
        teacherSubjectRepository.save(entity);
    }

    @Override
    public TeacherSubjectEntity read(Integer id) {
        return teacherSubjectRepository.findById(id).orElse(null);
    }

    @Override
    public TeacherSubjectEntity readByTeacherAndSubjectAndSchoolYear(TeacherSubject teacherSubject) {
        Specification<TeacherSubjectEntity> specification = (Specification<TeacherSubjectEntity>) (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("teacherId"), teacherSubject.getTeacherId()));
            list.add(criteriaBuilder.equal(root.get("subjectId"), teacherSubject.getSubjectId()));
            list.add(criteriaBuilder.equal(root.get("schoolYear"), teacherSubject.getSchoolYear()));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        return teacherSubjectRepository.findOne(specification).orElse(null);
    }


    @Override
    public void delete(Integer id) {
        teacherSubjectRepository.deleteById(id);
    }

    @Override
    public void update(TeacherSubject teacherSubject) {
        TeacherSubjectEntity teacherSubjectEntity = new TeacherSubjectEntity();
        teacherSubjectEntity.setId(teacherSubject.getId());
        teacherSubjectEntity.setSchoolYear(teacherSubject.getSchoolYear());
        teacherSubjectEntity.setTeacherId(teacherSubject.getTeacherId());
        teacherSubjectEntity.setSubjectId(teacherSubject.getSubjectId());
        teacherSubjectRepository.save(teacherSubjectEntity);
    }


}
