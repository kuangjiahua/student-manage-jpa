package com.demo.studentmanage.repository;

import com.demo.studentmanage.dto.SchoolScoreDto;
import com.demo.studentmanage.dto.TeacherSubjectDto;
import com.demo.studentmanage.model.TeacherSubject;

import java.util.List;

/**
 * 教师学科分数查询，增删改教师学科关系
 * @author kuangjiahua
 * @date   2021/01/05
 */
public interface TeacherSubjectMapper {

    /**
     * 查询学年教师-学科平均分
     * @param schoolScoreDto
     * @return
     */
    List<SchoolScoreDto> findSchoolTeacherAvgScore(SchoolScoreDto schoolScoreDto);

    /**
     * 查询学年教师-学科最高分
     * @param schoolScoreDto
     * @return
     */
    List<SchoolScoreDto> findSchoolTeacherMaxScore(SchoolScoreDto schoolScoreDto);

    /**
     * 查询学年教师-学科最低分
     * @param schoolScoreDto
     * @return
     */
    List<SchoolScoreDto> findSchoolTeacherMinScore(SchoolScoreDto schoolScoreDto);

    /**
     * 查询某教师学年学科平均分
     * @param teacherSubjectDto
     * @return
     */
    List<TeacherSubjectDto> listAvgScore(TeacherSubjectDto teacherSubjectDto);

    /**
     * 查询某教师学年学科最高分
     * @param teacherSubjectDto
     * @return
     */
    List<TeacherSubjectDto> listMaxScore(TeacherSubjectDto teacherSubjectDto);

    /**
     * 查询某教师学年学科最低分
     * @param teacherSubjectDto
     * @return
     */
    List<TeacherSubjectDto> listMinScore(TeacherSubjectDto teacherSubjectDto);

    /**
     * 保存教师学科关联关系
     * @param teacherSubject
     */
    void saveTeacherSubject(TeacherSubject teacherSubject);

    /**
     * 更新教师学科关联关系
     * @param teacherSubjectDto
     */
    void updateTeacherSubject(TeacherSubjectDto teacherSubjectDto);

    /**
     * 根据id查询记录
     * @param id
     * @return
     */
    TeacherSubject findById(Integer id);

    /**
     * 根据条件查询记录
     * @param teacherSubjectDto
     * @return
     */
    TeacherSubject findByCondition(TeacherSubjectDto teacherSubjectDto);

    /**
     * 根据id删除记录
     * @param id
     */
    void deleteById(Integer id);

}
