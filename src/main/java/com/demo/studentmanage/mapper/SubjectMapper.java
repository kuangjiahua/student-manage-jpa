package com.demo.studentmanage.mapper;

import com.demo.studentmanage.model.Subject;

/**
 * @author kuangjiahua
 * @date 2020/01/07
 */
public interface SubjectMapper {

    /**
     * 保存课程信息
     * @param subject
     */
    void save(Subject subject);

    /**
     * 根据id查询课程信息
     * @param id
     * @return
     */
    Subject read(Integer id);
}
