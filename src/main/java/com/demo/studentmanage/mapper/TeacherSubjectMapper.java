package com.demo.studentmanage.mapper;
import com.demo.studentmanage.model.TeacherSubject;
import com.demo.studentmanage.model.TeacherSubjectEntity;

/**
 * @author kuangjiahua
 * @date 2020/01/07
 */
public interface TeacherSubjectMapper {

    /**
     * 根据id查询记录
     * @param id
     * @return
     */
    TeacherSubjectEntity read(Integer id);

    /**
     * 根据教师、课程、学年查询记录
     * @param teacherSubject
     * @return
     */
    TeacherSubjectEntity readByTeacherAndSubjectAndSchoolYear(TeacherSubject teacherSubject);

    /**
     * 保存教师课程关联信息
     * @param teacherSubject
     */
    void save(TeacherSubject teacherSubject);

    /**
     * 更新教师课程关联信息
     * @param teacherSubject
     */
    void update(TeacherSubject teacherSubject);

    /**
     * 删除教师课程关联信息
     * @param id
     */
    void delete(Integer id);

}
