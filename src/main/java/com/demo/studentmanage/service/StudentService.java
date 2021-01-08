package com.demo.studentmanage.service;

import com.demo.studentmanage.model.Score;
import com.demo.studentmanage.model.ScoreResult;
import com.demo.studentmanage.model.StudentSubject;
import com.demo.studentmanage.query.ScoreQuery;

import java.util.List;

/**
 * 学生选课，查询相关接口
 * @author kuangjiahua
 * @date   2021/01/05
 */
public interface StudentService {

    /**
     * 查询学年各科分数平均分/最高分/最低分(支持多个学年)
     * @param scoreQuery
     * @return
     */
    List<ScoreResult> listSchoolScore(ScoreQuery scoreQuery);

    /**
     * 查询学生各科分数(支持多个学生)
     * @param scoreQuery
     * @return
     */
    List<ScoreResult> listStudentScore(ScoreQuery scoreQuery);

    /**
     * 学生选课
     * @param studentSubject
     */
    void saveStudentSubject(StudentSubject studentSubject);

    /**
     * 更新分数
     * @param score
     */
    void updateScore(Score score);

    /**
     * 删除学生分数
     * @param id
     */
    void deleteStudentSubject(int id);






}
