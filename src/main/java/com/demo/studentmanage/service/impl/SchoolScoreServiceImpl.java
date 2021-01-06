package com.demo.studentmanage.service.impl;

import com.demo.studentmanage.dto.SchoolScoreDto;
import com.demo.studentmanage.dto.converter.SchoolScoreConverter;
import com.demo.studentmanage.repository.StudentScoreMapper;
import com.demo.studentmanage.repository.TeacherSubjectMapper;
import com.demo.studentmanage.service.SchoolScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查询学生本人每学年各学科成绩
 * 新增/修改/删除学生学年学科成绩
 * @author kuangjiahua
 * @date   2021/01/04
 */
@Service
public class SchoolScoreServiceImpl implements SchoolScoreService {

    @Autowired
    private StudentScoreMapper studentScoreMapper;

    @Autowired
    private TeacherSubjectMapper teacherSubjectMapper;

    @Override
    public List<SchoolScoreDto> listSubjectAvgScorePage(SchoolScoreDto schoolScoreDto) {
        return studentScoreMapper.findSchoolAvgScore(schoolScoreDto);
    }

    @Override
    public List<SchoolScoreDto> listSubjectMaxScorePage(SchoolScoreDto schoolScoreDto) {
        return studentScoreMapper.findSchoolMaxScore(schoolScoreDto);
    }

    @Override
    public List<SchoolScoreDto> listSubjectMinScorePage(SchoolScoreDto schoolScoreDto) {
        return studentScoreMapper.findSchoolMinScore(schoolScoreDto);
    }


    @Override
    public List<SchoolScoreDto> listTeacherSubjectAvgScorePage(SchoolScoreDto schoolScoreDto) {
        return teacherSubjectMapper.findSchoolTeacherAvgScore(schoolScoreDto);
    }

    @Override
    public List<SchoolScoreDto> listTeacherSubjectMaxScorePage(SchoolScoreDto schoolScoreDto) {
        return teacherSubjectMapper.findSchoolTeacherMaxScore(schoolScoreDto);
    }

    @Override
    public List<SchoolScoreDto> listTeacherSubjectMinScorePage(SchoolScoreDto schoolScoreDto) {
        return teacherSubjectMapper.findSchoolTeacherMinScore(schoolScoreDto);
    }

}
