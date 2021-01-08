package com.demo.studentmanage.service;

import com.demo.studentmanage.model.ScoreResult;
import com.demo.studentmanage.model.TeacherSubject;
import com.demo.studentmanage.query.ScoreQuery;

import java.util.List;


/**
 * 查询教师本人每学年，学科平均成绩，最高分，最低分
 * @author kuangjiahua
 * @date   2021/01/05
 */
public interface TeacherService {

    /**
     * 查询学校教师各科平均分/最高分/最低分(支持多个学年)
     * @param scoreQuery
     * @return
     */
    List<ScoreResult> listSchoolTeacherScore(ScoreQuery scoreQuery);

    /**
     * 查询学年教师教授各科平均分/最高分/最低分(支持多个学年，多个教师)
     * @param scoreQuery
     * @return
     */
    List<ScoreResult> listTeacherScore(ScoreQuery scoreQuery);

    /**
     * 新增教师学科关联关系
     * @param teacherSubject
     * @return
     */
    void saveTeacherSubject(TeacherSubject teacherSubject);

    /**
     * 更新教师学科关联关系
     * @param teacherSubject
     */
    void updateTeacherSubject(TeacherSubject teacherSubject);

    /**
     * 删除教师学科关系
     * @param id
     */
    void deleteTeacherSubject(Integer id);



}
