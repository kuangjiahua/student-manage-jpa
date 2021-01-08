package com.demo.studentmanage.controller;

import com.demo.studentmanage.dto.ScoreQueryDTO;
import com.demo.studentmanage.dto.ScoreResultDTO;
import com.demo.studentmanage.dto.TeacherSubjectDTO;
import com.demo.studentmanage.dto.common.ApiResponse;
import com.demo.studentmanage.dto.converter.ScoreConverter;
import com.demo.studentmanage.dto.converter.TeacherConverter;
import com.demo.studentmanage.model.TeacherSubject;
import com.demo.studentmanage.query.ScoreQuery;
import com.demo.studentmanage.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 教师管理：
 * 查询科目分数
 * 新建、更新、删除教师课程关联关系
 * @author kuangjiahua
 * @date   2021/01/08
 */
@Api(tags = "教师管理")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查询学校教师各科平均分/最高分/最低分(支持多个学年)")
    @PostMapping("nhsoft.scoreManageDemo.schoolTeacherScore.list")
    public ApiResponse listSchoolTeacherScore(@RequestBody ScoreQueryDTO scoreQueryDTO){
        ScoreQuery scoreQuery = ScoreConverter.convertDtoToModel(scoreQueryDTO);
        List<ScoreResultDTO> results = teacherService.listSchoolTeacherScore(scoreQuery)
                .stream().map(ScoreConverter::convertModelToDTO).collect(Collectors.toList());
        return new ApiResponse(results);
    }


    @ApiOperation("查询学年教师教授各科平均分/最高分/最低分(支持多个学年，多个教师, 多个学科)")
    @RequestMapping("nhsoft.scoreManageDemo.teacherScore.list")
    public ApiResponse listTeacherScore(@RequestBody ScoreQueryDTO scoreQueryDTO){
        ScoreQuery scoreQuery = ScoreConverter.convertDtoToModel(scoreQueryDTO);
        List<ScoreResultDTO> results = teacherService.listTeacherScore(scoreQuery)
                .stream().map(ScoreConverter::convertModelToDTO).collect(Collectors.toList());
        return new ApiResponse(results);
    }


    @ApiOperation("新增教师学科关联关系")
    @PostMapping("nhsoft.scoreManageDemo.teacherSubject.save")
    public ApiResponse saveTeacherSubject(@RequestBody TeacherSubjectDTO teacherSubjectDTO){
        TeacherSubject teacherSubject = TeacherConverter.converterDtoToModel(teacherSubjectDTO);
        teacherService.saveTeacherSubject(teacherSubject);
        return new ApiResponse();
    }

    @ApiOperation("更新教师学科关联关系")
    @PostMapping("nhsoft.scoreManageDemo.teacherSubject.update")
    public ApiResponse updateTeacherSubject(@RequestBody TeacherSubjectDTO teacherSubjectDto){
        TeacherSubject teacherSubject = TeacherConverter.converterDtoToModel(teacherSubjectDto);
        teacherService.updateTeacherSubject(teacherSubject);
        return new ApiResponse();
    }

    @ApiOperation("删除教师学科关联关系")
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("nhsoft.scoreManageDemo.teacherSubject.delete")
    public ApiResponse deleteTeacherSubject(Integer id){
        teacherService.deleteTeacherSubject(id);
        return new ApiResponse();
    }

}
