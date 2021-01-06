package com.demo.studentmanage.controller;

import com.demo.studentmanage.dto.StudentScoreDto;
import com.demo.studentmanage.dto.common.ApiResponse;
import com.demo.studentmanage.service.StudentScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 查询学生本人每学年各学科成绩
 * 新增/修改/删除学生学年学科成绩
 * @author kuangjiahua
 * @date   2021/01/05
 */
@Api(tags = "学生分数")
@RestController
public class StudentScoreController {

    @Autowired
    private StudentScoreService studentScoreService;

    @ApiOperation("查询学年各科分数")
    @GetMapping("nhsoft.scoreManageDemo.studentScore.list")
    public ApiResponse listStudentSubjectScore(StudentScoreDto studentScoreDto){
        List<StudentScoreDto> studentScoreRespList = studentScoreService.listScorePage(studentScoreDto);
        return new ApiResponse(studentScoreRespList);
    }

    @ApiOperation("保存学年学科分数")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("nhsoft.scoreManageDemo.studentScore.save")
    public ApiResponse saveStudentScore(StudentScoreDto studentScoreDto){
        boolean result = studentScoreService.saveStudentScore(studentScoreDto);
        return new ApiResponse(result);
    }

    @ApiOperation("更新学年学科分数")
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("nhsoft.scoreManageDemo.studentScore.update")
    public ApiResponse updateStudentScore(StudentScoreDto studentScoreDto){
        boolean result = studentScoreService.updateStudentScore(studentScoreDto);
        return new ApiResponse(result);
    }

    @ApiOperation("删除学年学科分数")
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("nhsoft.scoreManageDemo.studentScore.delete")
    public ApiResponse deleteStudentScore(Integer id){
        studentScoreService.deleteStudentScore(id);
        return new ApiResponse();
    }

}
