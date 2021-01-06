package com.demo.studentmanage.service.impl;

import com.demo.studentmanage.dto.SchoolScoreDto;
import com.demo.studentmanage.dto.converter.SchoolScoreConverter;
import com.demo.studentmanage.service.SchoolScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolScoreServiceImpl implements SchoolScoreService {

    private static final String AVG = "avg";
    private static final String MAX = "max";
    private static final String MIN = "min";

    @Autowired
    public EntityManager entityManager;

    @Override
    public List<SchoolScoreDto> listSubjectAvgScorePage(SchoolScoreDto schoolScoreDto) {
        return getSchoolScoreByType(schoolScoreDto, AVG);
    }

    @Override
    public List<SchoolScoreDto> listSubjectMaxScorePage(SchoolScoreDto schoolScoreDto) {
        return getSchoolScoreByType(schoolScoreDto, MAX);
    }

    @Override
    public List<SchoolScoreDto> listSubjectMinScorePage(SchoolScoreDto schoolScoreDto) {
        return getSchoolScoreByType(schoolScoreDto, MIN);
    }


    private List<SchoolScoreDto> getSchoolScoreByType(SchoolScoreDto schoolScoreDto, String type){
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


    @Override
    public List<SchoolScoreDto> listTeacherSubjectAvgScorePage(SchoolScoreDto schoolScoreDto) {
        return getSchoolTeacherScoreByType(schoolScoreDto, AVG);
    }

    @Override
    public List<SchoolScoreDto> listTeacherSubjectMaxScorePage(SchoolScoreDto schoolScoreDto) {
        return getSchoolTeacherScoreByType(schoolScoreDto, MAX);
    }

    @Override
    public List<SchoolScoreDto> listTeacherSubjectMinScorePage(SchoolScoreDto schoolScoreDto) {
        return getSchoolTeacherScoreByType(schoolScoreDto, MIN);
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
