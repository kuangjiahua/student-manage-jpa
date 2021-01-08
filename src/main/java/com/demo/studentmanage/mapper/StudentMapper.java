package com.demo.studentmanage.mapper;

import com.demo.studentmanage.model.Student;

/**
 * @author kuangjiahua
 * @date 2020/01/07
 */
public interface StudentMapper {

    /**
     * 保存选课信息
     * @param student
     */
    void save(Student student);

    /**
     * 根据id查询记录
     * @param id
     * @return
     */
    Student read(Integer id);
}
