package com.demo.studentmanage.repository;

import com.demo.studentmanage.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer>, JpaSpecificationExecutor<StudentScore> {



}
