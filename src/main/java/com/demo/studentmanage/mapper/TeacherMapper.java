package com.demo.studentmanage.mapper;

import com.demo.studentmanage.model.Teacher;
import com.demo.studentmanage.model.TeacherSubject;

/**
 * @author kuangjiahua
 * @date 2020/01/07
 */
public interface TeacherMapper {

    /**
     * 保存教师信息
     * @param teacher
     */
    void save(Teacher teacher);

    /**
     * 根据id查询记录
     * @param id
     * @return
     */
    Teacher read(Integer id);
}
