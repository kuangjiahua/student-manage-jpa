package com.demo.studentmanage.mapper.impl;
import com.demo.studentmanage.constant.QueryTypeConstant;
import com.demo.studentmanage.dto.converter.ScoreConverter;
import com.demo.studentmanage.model.Score;
import com.demo.studentmanage.model.ScoreResult;
import com.demo.studentmanage.model.StudentSubject;
import com.demo.studentmanage.query.ScoreQuery;
import com.demo.studentmanage.mapper.repository.ScoreRepository;
import com.demo.studentmanage.mapper.ScoreMapper;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreMapperImpl implements ScoreMapper {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    public EntityManager entityManager;

    @Override
    public List<ScoreResult> listSchoolScore(ScoreQuery scoreQuery) {
        StringBuilder sb = new StringBuilder("select ");
        Integer queryType = scoreQuery.getQueryType();
        if( QueryTypeConstant.AVG.equals(queryType) ){
            sb.append(" avg(score) ");
        } else if( QueryTypeConstant.MAX.equals(queryType)) {
            sb.append(" max(score) ");
        } else if( QueryTypeConstant.MIN.equals(queryType)){
            sb.append(" min(score) ");
        }
        sb.append(" as score, subject_id as subjectId, school_year as schoolYear from score " );
        sb.append(" where school_year in (").append(StringUtils.join(scoreQuery.getSchoolYearList(), ','));
        sb.append(") group by subject_id, school_year limit ");
        sb.append(scoreQuery.getStart()).append(" , ").append(scoreQuery.getSize());
        List<Object[]> list = entityManager.createNativeQuery(sb.toString()).getResultList();
        return list.stream().map(ScoreConverter::convertSchoolScore).collect(Collectors.toList());
    }

    @Override
    public List<ScoreResult> listStudentScore(ScoreQuery scoreQuery) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select score, subject_id as subjectId, student_id as studentId, school_year as schoolYear");
        sb.append(" from score where student_id in ( ").append(StringUtils.join(scoreQuery.getStudentIdList(), ',')).append(" ) ");
        sb.append(" and school_year in (").append(StringUtils.join(scoreQuery.getSchoolYearList(), ',')).append(" ) ");
        List<Object[]> list = entityManager.createNativeQuery(sb.toString()).getResultList();
        return list.stream().map(ScoreConverter::convertStudentScore).collect(Collectors.toList());
    }

    @Override
    public List<ScoreResult> listSchoolTeacherScore(ScoreQuery scoreQuery) {
        Integer queryType = scoreQuery.getQueryType();
        StringBuilder sb = new StringBuilder();
        sb.append(" select ");
        if(queryType.equals(QueryTypeConstant.AVG)){
            sb.append(" avg(score) ");
        } else if(queryType.equals(QueryTypeConstant.MAX)){
            sb.append(" max(score) ");
        } else if(queryType.equals(QueryTypeConstant.MIN)){
            sb.append(" min(score) ");
        }
        sb.append(" as score , a.subject_id as subjectId, b.teacher_id as teacherId ");
        sb.append(" from score a inner join teacher_subject b  on a.subject_id = b.subject_id ");
        sb.append(" and a.school_year = b.school_year ");
        sb.append(" and b.school_year in (").append(StringUtils.join(scoreQuery.getSchoolYearList(), ',')).append(")");
        sb.append(" and a.subject_id in (").append(StringUtils.join(scoreQuery.getSubjectIdList(), ',')).append(")");
        sb.append(" group by a.subject_id, a.school_year, teacher_id limit ").append(scoreQuery.getStart()).append(" , ").append(scoreQuery.getSize());
        List<Object[]> results = entityManager.createNativeQuery(sb.toString()).getResultList();
        return results.stream().map(ScoreConverter::convertSchoolTeacherScore).collect(Collectors.toList());
    }

    @Override
    public List<ScoreResult> listTeacherScore(ScoreQuery scoreQuery){
        Integer queryType = scoreQuery.getQueryType();
        StringBuilder sb = new StringBuilder();
        sb.append(" select ");
        if(queryType.equals(QueryTypeConstant.AVG)){
            sb.append(" avg(score) ");
        } else if(queryType.equals(QueryTypeConstant.MAX)){
            sb.append(" max(score) ");
        } else if(queryType.equals(QueryTypeConstant.MIN)){
            sb.append(" min(score) ");
        }
        sb.append(" as score , a.subject_id as subjectId, a.school_year as schoolYear, b.teacher_id as teacherId");
        sb.append(" from score a inner join teacher_subject b  on a.subject_id = b.subject_id ");
        sb.append(" and a.school_year = b.school_year ");
        sb.append(" where b.teacher_id in (").append(StringUtils.join(scoreQuery.getTeacherIdList(), ',')).append(")");
        sb.append(" and b.school_year in (").append(StringUtils.join(scoreQuery.getSchoolYearList(), ',')).append(")");
        sb.append(" and b.subject_id in (").append(StringUtils.join(scoreQuery.getSubjectIdList(), ',')).append(")");
        sb.append(" group by a.subject_id , a.school_year, b.teacher_id limit ").append(scoreQuery.getStart()).append(scoreQuery.getSize());
        List<Object[]> results = entityManager.createNativeQuery(sb.toString()).getResultList();
        return results.stream().map(ScoreConverter::convertTeacherScore).collect(Collectors.toList());
    }

    @Override
    public Score readByStudnetAndSubjectAndSchoolYear(StudentSubject studentSubject) {
        Specification<Score> specification = (Specification<Score>) (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("studentId"), studentSubject.getStudentId()));
            list.add(criteriaBuilder.equal(root.get("subjectId"), studentSubject.getSubjectId()));
            list.add(criteriaBuilder.equal(root.get("schoolYear"), studentSubject.getSchoolYear()));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        return scoreRepository.findOne(specification).orElse(null);
    }

    @Override
    public Score read(int id) {
        return scoreRepository.getOne(id);
    }

    @Override
    public void save(Score score) {
        scoreRepository.save(score);
    }

    @Override
    public void update(Score score) {
        scoreRepository.save(score);
    }

    @Override
    public void delete(Integer id) {
        scoreRepository.deleteById(id);
    }


}
