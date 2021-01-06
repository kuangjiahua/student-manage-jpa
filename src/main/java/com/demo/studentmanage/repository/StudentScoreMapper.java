package com.demo.studentmanage.repository;

import com.demo.studentmanage.dto.SchoolScoreDto;
import com.demo.studentmanage.dto.StudentScoreDto;
import com.demo.studentmanage.model.StudentScore;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页查询学生学科分数
 * 增删改学生分数
 * @author kuangjiahua
 * @date   2021/01/05
 */
public interface StudentScoreMapper {

    /**
     * 查询学年各科平均成绩
     * @param schoolScoreDto
     * @return
     */
    List<SchoolScoreDto> findSchoolAvgScore(SchoolScoreDto schoolScoreDto);

    /**
     * 查询学年各科最高成绩
     * @param schoolScoreDto
     * @return
     */
    List<SchoolScoreDto> findSchoolMaxScore(SchoolScoreDto schoolScoreDto);

    /**
     * 查询学年各科最低成绩
     * @param schoolScoreDto
     * @return
     */
    List<SchoolScoreDto> findSchoolMinScore(SchoolScoreDto schoolScoreDto);

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    StudentScore findById(Integer id);

    /**
     * 获取学生学年各科成绩
     * @param studentScoreDto
     * @return
     */
    Page<StudentScore> listStudentScorePage(StudentScoreDto studentScoreDto);

    /**
     * 根据条件查询记录
     * @param studentScoreDto
     * @return
     */
    StudentScore findByCondition(StudentScoreDto studentScoreDto);

    /**
     * 保存学生学年分数
     * @param studentScore
     */
    void saveStudentScore(StudentScore studentScore);

    /**
     * 更新学生学年分数
     * @param studentScoreDto
     */
    void updateStudentScore(StudentScoreDto studentScoreDto);

    /**
     * 删除学生学年分数
     * @param id
     */
    void deleteById(int id);
}
