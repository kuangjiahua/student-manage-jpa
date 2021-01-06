package com.demo.studentmanage.service;

import com.demo.studentmanage.dto.StudentScoreDto;
import java.util.List;

/**
 * 查询学生本人每学年
 * @author kuangjiahua
 * @date   2021/01/05
 */
public interface StudentScoreService {

    /**
     * 学生获取学年各科成绩分页
     * @param studentScoreDto
     * @return
     */
    List<StudentScoreDto> listScorePage(StudentScoreDto studentScoreDto);

    /**
     * 保存学生学年学科成绩
     * @param studentScoreDto
     * @return
     */
    boolean saveStudentScore(StudentScoreDto studentScoreDto);

    /**
     * 更新学生学年学科成绩
     * @param studentScoreDto
     * @return
     */
    boolean updateStudentScore(StudentScoreDto studentScoreDto);

    /**
     * 删除学生学年学科成绩
     * @param id
     * @return
     */
    void deleteStudentScore(int id);


}
