package com.demo.studentmanage.repository;

import com.demo.studentmanage.dto.SchoolScoreDto;
import com.demo.studentmanage.dto.converter.SchoolScoreConverter;
import com.demo.studentmanage.model.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuangjiahua
 * @date   2021/01/04
 */
@Component
public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer>, JpaSpecificationExecutor<StudentScore> {
}
