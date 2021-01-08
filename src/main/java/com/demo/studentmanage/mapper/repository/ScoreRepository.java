package com.demo.studentmanage.mapper.repository;

import com.demo.studentmanage.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScoreRepository extends JpaRepository<Score, Integer>, JpaSpecificationExecutor<Score> {
}
