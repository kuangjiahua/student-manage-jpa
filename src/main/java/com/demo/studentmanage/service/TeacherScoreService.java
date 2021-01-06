package com.demo.studentmanage.service;

import com.demo.studentmanage.dto.TeacherSubjectDto;

import java.util.List;


/**
 * 查询教师本人每学年，学科平均成绩，最高分，最低分
 * @author kuangjiahua
 * @date   2021/01/05
 */
public interface TeacherScoreService {

    /**
     * 查询学年各科平均分分页
     * @param teacherSubjectDto
     * @return
     */
    List<TeacherSubjectDto> listAvgScorePage(TeacherSubjectDto teacherSubjectDto);

    /**
     * 查询学年各科最高分分页
     * @param teacherSubjectDto
     * @return
     */
    List<TeacherSubjectDto> listMaxScorePage(TeacherSubjectDto teacherSubjectDto);

    /**
     * 查询学年各科最低分分页
     * @param teacherSubjectDto
     * @return
     */
    List<TeacherSubjectDto> listMinScorePage(TeacherSubjectDto teacherSubjectDto);

    /**
     * 保存教师学科关系
     * @param teacherSubjectDto
     */
    boolean saveTeacherSubject(TeacherSubjectDto teacherSubjectDto);

    /**
     * 更新教师学科关系
     * @param teacherSubjectDto
     */
    boolean updateTeacherSubject(TeacherSubjectDto teacherSubjectDto);

    /**
     * 删除教师学科关系
     * @param id
     */
    void deleteTeacherSubject(int id);



}
