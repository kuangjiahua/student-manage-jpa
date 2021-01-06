package com.demo.studentmanage.repository;

import com.demo.studentmanage.model.TeacherSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Integer>, JpaSpecificationExecutor<TeacherSubject> {


}
