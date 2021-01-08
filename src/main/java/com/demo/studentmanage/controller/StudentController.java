package com.demo.studentmanage.controller;

import com.demo.studentmanage.dto.*;
import com.demo.studentmanage.dto.common.ApiResponse;
import com.demo.studentmanage.dto.converter.ScoreConverter;
import com.demo.studentmanage.dto.converter.StudentSubjectConverter;
import com.demo.studentmanage.model.Score;
import com.demo.studentmanage.model.StudentSubject;
import com.demo.studentmanage.query.ScoreQuery;
import com.demo.studentmanage.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 学生管理：
 * 查询、更新分数
 * 选课、退课
 * @author kuangjiahua
 * @date   2021/01/08
 */
@Api(tags = "学生管理")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("查询学年各科分数平均分/最高分/最低分(支持多个学年)")
    @PostMapping("nhsoft.scoreManageDemo.schoolscore.list")
    public ApiResponse listSchoolScore(@RequestBody ScoreQueryDTO scoreQueryDTO){
        ScoreQuery scoreQuery = ScoreConverter.convertDtoToModel(scoreQueryDTO);
        List<ScoreResultDTO> result = studentService.listSchoolScore(scoreQuery)
                .stream().map(ScoreConverter::convertModelToDTO).collect(Collectors.toList());
        return new ApiResponse(result);

    }

    @ApiOperation("查询学生各科分数(支持多个学生)")
    @PostMapping("nhsoft.scoreManageDemo.studentScore.list")
    public ApiResponse listStudentScore(@RequestBody ScoreQueryDTO scoreQueryDTO){
        ScoreQuery scoreQuery = ScoreConverter.convertDtoToModel(scoreQueryDTO);
        List<ScoreResultDTO> result = studentService.listStudentScore(scoreQuery)
                .stream().map(ScoreConverter::convertModelToDTO).collect(Collectors.toList());
        return new ApiResponse(result);
    }

    @ApiOperation("学生选课")
    @PostMapping("nhsoft.scoreManageDemo.studentSubject.save")
    public ApiResponse saveStudentSubject(@RequestBody StudentSubjectDTO studentSubjectDTO){
        StudentSubject studentSubject = StudentSubjectConverter.convertDtoToModel(studentSubjectDTO);
        studentService.saveStudentSubject(studentSubject);
        return new ApiResponse();
    }

    @ApiOperation("更新学生分数")
    @PutMapping("nhsoft.scoreManageDemo.studentScore.update")
    public ApiResponse updateStudentScore(ScoreDTO scoreDTO){
        Score score = ScoreConverter.convertDtoToModel(scoreDTO);
        studentService.updateScore(score);
        return new ApiResponse();
    }

    @ApiOperation("退选课程")
    @DeleteMapping("nhsoft.scoreManageDemo.studentSubject.delete")
    public ApiResponse deleteStudentScore(Integer id){
        studentService.deleteStudentSubject(id);
        return new ApiResponse();
    }

}
