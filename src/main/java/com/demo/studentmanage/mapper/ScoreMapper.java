package com.demo.studentmanage.mapper;

import com.demo.studentmanage.model.Score;
import com.demo.studentmanage.model.ScoreResult;
import com.demo.studentmanage.model.StudentSubject;
import com.demo.studentmanage.query.ScoreQuery;

import java.util.List;

/**
 * @author kuangjiahua
 * @date 2020/01/07
 */
public interface ScoreMapper {

    /**
     * 查询分数平均分/最高分/最低分(支持多个年份)
     * @param scoreQuery
     * @return
     */
    List<ScoreResult> listSchoolScore(ScoreQuery scoreQuery);

    /**
     * 查询学生各科分数(支持多个学生，多个年份)
     * @param scoreQuery
     * @return
     */
    List<ScoreResult> listStudentScore(ScoreQuery scoreQuery);

    /**
     * 根据
     * @param scoreQuery
     * @return
     */
    List<ScoreResult> listSchoolTeacherScore(ScoreQuery scoreQuery);

    /**
     * 查询
     * @param scoreQuery
     * @return
     */
    List<ScoreResult> listTeacherScore(ScoreQuery scoreQuery);

    /**
     * 根据学生、课程、学年查询学生选课记录
     * @param studentSubject
     * @return
     */
    Score readByStudnetAndSubjectAndSchoolYear(StudentSubject studentSubject);

    /**
     * 根据id查询选课记录
     * @param id
     * @return
     */
    Score read(int id);

    /**
     * 保存选课记录
     * @param score
     */
    void save(Score score);

    /**
     * 更新选课记录
     * @param score
     */
    void update(Score score);

    /**
     * 删除选课记录
     * @param id
     */
    void delete(Integer id);


}
