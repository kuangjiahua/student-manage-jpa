package com.demo.studentmanage.service.impl;
import com.demo.studentmanage.model.*;
import com.demo.studentmanage.query.ScoreQuery;
import com.demo.studentmanage.mapper.ScoreMapper;
import com.demo.studentmanage.mapper.StudentMapper;
import com.demo.studentmanage.mapper.SubjectMapper;
import com.demo.studentmanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 查询学生本人每学年各学科成绩
 * 新增/修改/删除学生学年学科成绩
 * @author kuangjiahua
 * @date   2021/01/04
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public List<ScoreResult> listSchoolScore(ScoreQuery scoreQuery) {
        return scoreMapper.listSchoolScore(scoreQuery);
    }

    @Override
    public List<ScoreResult> listStudentScore(ScoreQuery scoreQuery) {
        return scoreMapper.listStudentScore(scoreQuery);
    }

    @Override
    public void saveStudentSubject(StudentSubject studentSubject) {
        //校验学生信息是否存在，若不存在则更新
        Student student = studentMapper.read(studentSubject.getStudentId());
        if(student == null){
            studentMapper.save(studentSubject.getStudent());
        }
        //校验课程信息是否存在，若不存在则更新
        Subject subject = subjectMapper.read(studentSubject.getSubjectId());
        if(subject == null){
            subjectMapper.save(studentSubject.getSubject());
        }
        //校验学生是否重复选课
        if(scoreMapper.readByStudnetAndSubjectAndSchoolYear(studentSubject) != null){
            return;
        }
        //添加学生课程关联信息
        Score score = new Score();
        score.setScore(0.0);//分数初始化为0
        score.setStudentId(studentSubject.getStudentId());
        score.setSchoolYear(studentSubject.getSchoolYear());
        score.setSubjectId(studentSubject.getSubjectId());
        scoreMapper.save(score);

    }

    @Override
    public void updateScore(Score score) {
        //校验id对应记录是否存在
        Score scoreExist = scoreMapper.read(score.getId());
        if(scoreExist == null){
            return;
        }
        //更新课程分数
        scoreMapper.update(score);
    }

    @Override
    public void deleteStudentSubject(int id) {
        scoreMapper.delete(id);
    }
}
