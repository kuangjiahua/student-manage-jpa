package com.demo.studentmanage.service.impl;

import com.demo.studentmanage.dto.TeacherSubjectDto;
import com.demo.studentmanage.dto.converter.TeacherScoreConverter;
import com.demo.studentmanage.model.TeacherSubject;
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

    private static final String AVG = "avg";
    private static final String MAX = "max";
    private static final String MIN = "min";

    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;

    @Autowired
    public EntityManager entityManager;

    @Override
    public List<TeacherSubjectDto> listAvgScorePage(TeacherSubjectDto teacherSubjectDto) {
        return listScoreByType(teacherSubjectDto, AVG);
    }

    @Override
    public List<TeacherSubjectDto> listMaxScorePage(TeacherSubjectDto teacherSubjectDto) {
        return listScoreByType(teacherSubjectDto, MAX);
    }

    @Override
    public List<TeacherSubjectDto> listMinScorePage(TeacherSubjectDto teacherSubjectDto) {
        return listScoreByType(teacherSubjectDto, MIN);
    }

    public List<TeacherSubjectDto> listScoreByType(TeacherSubjectDto teacherSubjectDto, String type) {
        StringBuffer sb =  new StringBuffer(" select ");
        if(AVG.equals(type)){
            sb.append(" avg(score) ");
        } else if( MAX.equals(type)) {
            sb.append(" max(score) ");
        } else if( MIN.equals(type)){
            sb.append(" min(score) ");
        }
        sb.append(" as score , a.subject_id as subjectId ");
        sb.append(" from score a inner join teacher_subject b  on a.subject_id = b.subject_id ");
        sb.append(" and a.school_year = b.school_year ");
        sb.append(" where b.teacher_id = ").append(teacherSubjectDto.getTeacherId());
        sb.append(" and b.school_year = ").append(teacherSubjectDto.getSchoolYear());
        sb.append(" group by a.subject_id limit ").append(teacherSubjectDto.getStart()).append(teacherSubjectDto.getSize());
        List<Object[]> list = entityManager.createNativeQuery(sb.toString()).getResultList();
        return list.stream().map(TeacherScoreConverter::converterObjectToDto).collect(Collectors.toList());
    }


    @Override
    public boolean saveTeacherSubject(TeacherSubjectDto teacherSubjectDto) {
        //校验教师学科关系是否已经存在
        if(findExistTeacherSubject(teacherSubjectDto) != null){
            return false;
        }
        TeacherSubject teacherSubject = TeacherScoreConverter.convertDtoToModel(teacherSubjectDto);
        teacherSubjectRepository.save(teacherSubject);
        return true;
    }

    @Override
    public boolean updateTeacherSubject(TeacherSubjectDto teacherSubjectDto) {
        //校验当前id对应数据是否存在
        TeacherSubject teacherSubjectExist = teacherSubjectRepository.findById(teacherSubjectDto.getId()).orElse(null);
        if(teacherSubjectExist == null) {
            return false;
        }
        //校验更新信息是否导致重复
        TeacherSubject teacherSubjectRepeat = findExistTeacherSubject(teacherSubjectDto);
        if(teacherSubjectRepeat != null && !teacherSubjectRepeat.getId().equals(teacherSubjectDto.getId())){
            return false;
        }
        teacherSubjectExist.setTeacherId(teacherSubjectDto.getTeacherId());
        teacherSubjectExist.setSchoolYear(teacherSubjectDto.getSchoolYear());
        teacherSubjectExist.setSubjectId(teacherSubjectDto.getSubjectId());
        teacherSubjectRepository.save(teacherSubjectExist);
        return false;
    }

    private TeacherSubject findExistTeacherSubject(TeacherSubjectDto teacherSubjectDto){
        Specification<TeacherSubject> specification = (Specification<TeacherSubject>) (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("teacherId"), teacherSubjectDto.getTeacherId()));
            list.add(criteriaBuilder.equal(root.get("subjectId"), teacherSubjectDto.getSubjectId()));
            list.add(criteriaBuilder.equal(root.get("schoolYear"), teacherSubjectDto.getSchoolYear()));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        return teacherSubjectRepository.findOne(specification).orElse(null);
    }

    @Override
    public void deleteTeacherSubject(int id) {
        teacherSubjectRepository.deleteById(id);
    }
}
