package com.demo.studentmanage.service.impl;

import com.demo.studentmanage.mapper.ScoreMapper;
import com.demo.studentmanage.model.ScoreResult;
import com.demo.studentmanage.model.TeacherSubject;
import com.demo.studentmanage.model.TeacherSubjectEntity;
import com.demo.studentmanage.query.ScoreQuery;
import com.demo.studentmanage.mapper.SubjectMapper;
import com.demo.studentmanage.mapper.TeacherMapper;
import com.demo.studentmanage.mapper.TeacherSubjectMapper;
import com.demo.studentmanage.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询教师本人每学年，学科平均成绩，最高分，最低分
 * @author kuangjiahua
 * @date   2021/01/04
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherSubjectMapper teacherSubjectMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private ScoreMapper scoreMapper;


    @Override
    public List<ScoreResult> listSchoolTeacherScore(ScoreQuery scoreQuery) {
        return scoreMapper.listSchoolTeacherScore(scoreQuery);
    }

    @Override
    public List<ScoreResult> listTeacherScore(ScoreQuery scoreQuery) {
        return scoreMapper.listTeacherScore(scoreQuery);
    }

    @Override
    public void saveTeacherSubject(TeacherSubject teacherSubject) {
        //校验教师信息是否存在，若不存在则新增
        if(teacherMapper.read(teacherSubject.getTeacherId()) == null){
            teacherMapper.save(teacherSubject.getTeacher());
        }
        //校验课程信息是否存在，若不存在则新增
        if(subjectMapper.read(teacherSubject.getSubjectId()) == null){
            subjectMapper.save(teacherSubject.getSubject());
        }
        //新增教师课程关联关系
        teacherSubjectMapper.save(teacherSubject);
    }

    @Override
    public void updateTeacherSubject(TeacherSubject teacherSubject) {
        //根据id查询对应记录是否存在
        if(teacherSubjectMapper.read(teacherSubject.getId()) == null){
            return;
        }
        //校验是否已经关联重复课程
        TeacherSubjectEntity teacherSubjectRepeat =
                teacherSubjectMapper.readByTeacherAndSubjectAndSchoolYear(teacherSubject);
        if(teacherSubjectRepeat != null && !teacherSubjectRepeat.getId().equals(teacherSubject.getId())){
            return;
        }
        //更新教师课程关系
        teacherSubjectMapper.update(teacherSubject);
    }

    @Override
    public void deleteTeacherSubject(Integer id) {
        teacherSubjectMapper.delete(id);
    }
}



