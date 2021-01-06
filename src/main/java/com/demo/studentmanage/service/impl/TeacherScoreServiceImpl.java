package com.demo.studentmanage.service.impl;

import com.demo.studentmanage.dto.TeacherSubjectDto;
import com.demo.studentmanage.dto.converter.TeacherScoreConverter;
import com.demo.studentmanage.model.TeacherSubject;
import com.demo.studentmanage.repository.TeacherSubjectMapper;
import com.demo.studentmanage.repository.TeacherSubjectRepository;
import com.demo.studentmanage.service.TeacherScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查询教师本人每学年，学科平均成绩，最高分，最低分
 * @author kuangjiahua
 * @date   2021/01/04
 */
@Service
public class TeacherScoreServiceImpl implements TeacherScoreService {

    @Autowired
    private TeacherSubjectMapper teacherSubjectMapper;


    @Override
    public List<TeacherSubjectDto> listAvgScorePage(TeacherSubjectDto teacherSubjectDto) {
        return teacherSubjectMapper.listAvgScore(teacherSubjectDto);
    }

    @Override
    public List<TeacherSubjectDto> listMaxScorePage(TeacherSubjectDto teacherSubjectDto) {
        return teacherSubjectMapper.listMaxScore(teacherSubjectDto);
    }

    @Override
    public List<TeacherSubjectDto> listMinScorePage(TeacherSubjectDto teacherSubjectDto) {
        return teacherSubjectMapper.listMinScore(teacherSubjectDto);
    }


    @Override
    public boolean saveTeacherSubject(TeacherSubjectDto teacherSubjectDto) {
        //校验教师学科关系是否已经存在
        if(teacherSubjectMapper.findByCondition(teacherSubjectDto) != null){
            return false;
        }
        TeacherSubject teacherSubject = TeacherScoreConverter.convertDtoToModel(teacherSubjectDto);
        teacherSubjectMapper.saveTeacherSubject(teacherSubject);
        return true;
    }

    @Override
    public boolean updateTeacherSubject(TeacherSubjectDto teacherSubjectDto) {
        //校验当前id对应数据是否存在
        TeacherSubject teacherSubjectExist = teacherSubjectMapper.findById(teacherSubjectDto.getId());
        if(teacherSubjectExist == null) {
            return false;
        }
        //校验更新信息是否导致重复
        TeacherSubject teacherSubjectRepeat = teacherSubjectMapper.findByCondition(teacherSubjectDto);
        if(teacherSubjectRepeat != null && !teacherSubjectRepeat.getId().equals(teacherSubjectDto.getId())){
            return false;
        }
        teacherSubjectMapper.updateTeacherSubject(teacherSubjectDto);
        return false;
    }


    @Override
    public void deleteTeacherSubject(int id) {
        teacherSubjectMapper.deleteById(id);
    }
}
