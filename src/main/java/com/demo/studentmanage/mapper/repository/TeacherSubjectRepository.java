package com.demo.studentmanage.mapper.repository;

import com.demo.studentmanage.model.TeacherSubject;
import com.demo.studentmanage.model.TeacherSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

/**
 * @author kuangjiahua
 * @date   2021/01/04
 */
@Component
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubjectEntity, Integer>, JpaSpecificationExecutor<TeacherSubjectEntity> {
}
