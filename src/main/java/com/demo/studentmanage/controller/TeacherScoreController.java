package com.demo.studentmanage.controller;

import com.demo.studentmanage.dto.TeacherSubjectDto;
import com.demo.studentmanage.dto.common.ApiResponse;
import com.demo.studentmanage.service.TeacherScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 查询教师本人每学年各科平均成绩，最高分，最低分
 * 新增/修改/删除教师学科关系
 * @author kuangjiahua
 * @date   2021/01/05
 */
@Api(tags = "教师学科分数")
@RestController
public class TeacherScoreController {

    @Autowired
    private TeacherScoreService teacherScoreService;

    @ApiOperation("查询学年各科平均分")
    @RequestMapping("nhsoft.scoreManageDemo.teacherSubjectAvgScore.list")
    public ApiResponse countAvgSubjectScore(TeacherSubjectDto teacherSubjectDto){
        List<TeacherSubjectDto> scoreList = teacherScoreService.listAvgScorePage(teacherSubjectDto);
        return new ApiResponse(scoreList);
    }

    @ApiOperation("查询学年各科最大分")
    @RequestMapping("nhsoft.scoreManageDemo.teacherSubjectMaxScore.list")
    public ApiResponse countMaxSubjectScore(TeacherSubjectDto teacherSubjectDto){
        List<TeacherSubjectDto> scoreList = teacherScoreService.listMaxScorePage(teacherSubjectDto);
        return new ApiResponse(scoreList);
    }

    @ApiOperation("查询学年各科最低分")
    @RequestMapping("nhsoft.scoreManageDemo.teacherSubjectMinScore.list")
    public ApiResponse countMinSubjectScore(TeacherSubjectDto teacherSubjectDto){
        List<TeacherSubjectDto> scoreList = teacherScoreService.listMinScorePage(teacherSubjectDto);
        return new ApiResponse(scoreList);
    }

    @ApiOperation("新增教师学科关系")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("nhsoft.scoreManageDemo.teacherSubject.save")
    public ApiResponse saveTeacherSubject(TeacherSubjectDto teacherSubjectDto){
        boolean result = teacherScoreService.saveTeacherSubject(teacherSubjectDto);
        return new ApiResponse(result);
    }

    @ApiOperation("更新教师学科关系")
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("nhsoft.scoreManageDemo.teacherSubject.update")
    public ApiResponse updateTeacherSubject(TeacherSubjectDto teacherSubjectDto){
        boolean result = teacherScoreService.updateTeacherSubject(teacherSubjectDto);
        return new ApiResponse(result);
    }

    @ApiOperation("删除教师学科关系")
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("nhsoft.scoreManageDemo.teacherSubject.delete")
    public ApiResponse deleteTeacherSubject(Integer id){
        teacherScoreService.deleteTeacherSubject(id);
        return new ApiResponse();
    }

}
