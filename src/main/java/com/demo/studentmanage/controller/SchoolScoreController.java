package com.demo.studentmanage.controller;
import com.demo.studentmanage.dto.SchoolScoreDto;
import com.demo.studentmanage.dto.common.ApiResponse;
import com.demo.studentmanage.service.SchoolScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 查询每学年，学科平均成绩，最高分，最低分
 * 查询每学年，老师-学科平均成绩，最高分，最低分
 * @author kuangjiahua
 * @date   2021/01/05
 */
@Api(tags = "教导主任查询分数")
@RestController
public class SchoolScoreController {

    @Autowired
    private SchoolScoreService schoolScoreService;

    @ApiOperation("查询学年各科平均分")
    @GetMapping("nhsoft.scoreManageDemo.schoolSubjectAvgScore.list")
    public ApiResponse listSubjectAvgScore(SchoolScoreDto schoolScoreDto){
        List<SchoolScoreDto> studentScoreRespList = schoolScoreService.listSubjectAvgScorePage(schoolScoreDto);
        return new ApiResponse(studentScoreRespList);
    }

    @ApiOperation("查询学年各科最大分")
    @GetMapping("nhsoft.scoreManageDemo.schoolSubjectMaxScore.list")
    public ApiResponse listSubjectMaxScore(SchoolScoreDto schoolScoreDto){
        List<SchoolScoreDto> studentScoreRespList = schoolScoreService.listSubjectMaxScorePage(schoolScoreDto);
        return new ApiResponse(studentScoreRespList);
    }

    @ApiOperation("查询学年各科最小分")
    @GetMapping("nhsoft.scoreManageDemo.schoolSubjectMinScore.list")
    public ApiResponse listSubjectMinScore(SchoolScoreDto schoolScoreDto){
        List<SchoolScoreDto> studentScoreRespList = schoolScoreService.listSubjectMinScorePage(schoolScoreDto);
        return new ApiResponse(studentScoreRespList);
    }

    @ApiOperation("查询学年各教师各科平均分")
    @GetMapping("nhsoft.scoreManageDemo.schoolTeacherSubjectAvgScore.list")
    public ApiResponse listTeacherSubjectAvgScore(SchoolScoreDto schoolScoreDto){
        List<SchoolScoreDto> studentScoreRespList = schoolScoreService.listTeacherSubjectAvgScorePage(schoolScoreDto);
        return new ApiResponse(studentScoreRespList);
    }

    @ApiOperation("查询学年各教师各科最大分")
    @GetMapping("nhsoft.scoreManageDemo.schoolTeacherSubjectMaxScore.list")
    public ApiResponse listTeacherSubjectMaxScore(SchoolScoreDto schoolScoreDto){
        List<SchoolScoreDto> studentScoreRespList = schoolScoreService.listTeacherSubjectMaxScorePage(schoolScoreDto);
        return new ApiResponse(studentScoreRespList);
    }

    @ApiOperation("查询学年各教师各科最小分")
    @GetMapping("nhsoft.scoreManageDemo.schoolTeacherSubjectMinScore.list")
    public ApiResponse listTeacherSubjectMinScore(SchoolScoreDto schoolScoreDto){
        List<SchoolScoreDto> studentScoreRespList = schoolScoreService.listTeacherSubjectMinScorePage(schoolScoreDto);
        return new ApiResponse(studentScoreRespList);
    }



}
