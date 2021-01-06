package com.demo.studentmanage.service.impl;

import com.demo.studentmanage.dto.StudentScoreDto;
import com.demo.studentmanage.dto.converter.StudentScoreConverter;
import com.demo.studentmanage.model.StudentScore;
import com.demo.studentmanage.repository.StudentScoreMapper;
import com.demo.studentmanage.service.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查询学生本人每学年各学科成绩
 * 新增/修改/删除学生学年学科成绩
 * @author kuangjiahua
 * @date   2021/01/04
 */
@Service
public class StudentScoreServiceImpl implements StudentScoreService {

    @Autowired
    private StudentScoreMapper studentScoreMapper;

    @Override
    public List<StudentScoreDto> listScorePage(StudentScoreDto studentScoreDto) {
        Page<StudentScore> page = studentScoreMapper.listStudentScorePage(studentScoreDto);
        return page.stream().map(StudentScoreConverter::convertModelToDto).collect(Collectors.toList());
    }

    @Override
    public boolean saveStudentScore(StudentScoreDto studentScoreDto) {
        //校验当前学生分数信息是否已经存在
        StudentScore studentExist = studentScoreMapper.findByCondition(studentScoreDto);
        if(studentExist != null){
            return false;
        }
        StudentScore studentScore = StudentScoreConverter.convertDtoToModel(studentScoreDto);
        studentScoreMapper.saveStudentScore(studentScore);
        return true;
    }

    @Override
    public boolean updateStudentScore(StudentScoreDto studentScoreDto) {
        //校验当前id对应记录是否存在
        StudentScore scoreExist = studentScoreMapper.findById(studentScoreDto.getId());
        if(scoreExist == null){
            return false;
        }
        //校验当前学生信息是否重复
        StudentScore scoreRepeat = studentScoreMapper.findByCondition(studentScoreDto);
        if(scoreRepeat != null && !scoreRepeat.getId().equals(studentScoreDto.getId())){
            return false;
        }
        studentScoreMapper.updateStudentScore(studentScoreDto);
        return false;
    }


    @Override
    public void deleteStudentScore(int id) {
        studentScoreMapper.deleteById(id);
    }
}
