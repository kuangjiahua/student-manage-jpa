package com.demo.studentmanage.dto.converter;

import com.demo.studentmanage.dto.ScoreDTO;
import com.demo.studentmanage.dto.ScoreQueryDTO;
import com.demo.studentmanage.dto.ScoreResultDTO;
import com.demo.studentmanage.model.Score;
import com.demo.studentmanage.model.ScoreResult;
import com.demo.studentmanage.query.ScoreQuery;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ScoreConverter {

    public static ScoreResultDTO convertModelToDTO(ScoreResult scoreResult){
        ScoreResultDTO scoreResultDTO = new ScoreResultDTO();

        BeanUtils.copyProperties(scoreResult, scoreResultDTO);
        return scoreResultDTO;
    }

    public static ScoreQuery convertDtoToModel(ScoreQueryDTO scoreQueryDTO){
        ScoreQuery scoreQuery = new ScoreQuery();
        BeanUtils.copyProperties(scoreQueryDTO, scoreQuery);
        return scoreQuery;
    }

    public static Score convertDtoToModel(ScoreDTO scoreDTO){
        Score score = new Score();
        BeanUtils.copyProperties(scoreDTO, score);
        return score;
    }

    public static ScoreResult convertSchoolScore(Object[] object){
        ScoreResult scoreResult = new ScoreResult();
        scoreResult.setScore(new BigDecimal(object[0].toString()).doubleValue());
        scoreResult.setSubjectId((Integer)object[1]);
        scoreResult.setSchoolYear((String)object[2]);
        return scoreResult;
    }

    public static ScoreResult convertStudentScore(Object[] object){
        ScoreResult scoreResult = new ScoreResult();
        scoreResult.setScore(new BigDecimal(object[0].toString()).doubleValue());
        scoreResult.setSubjectId((Integer)object[1]);
        scoreResult.setStudentId((Integer)object[2]);
        scoreResult.setSchoolYear((String)object[3]);
        return scoreResult;
    }

    public static ScoreResult convertSchoolTeacherScore(Object[] object){
        ScoreResult scoreResult = new ScoreResult();
        scoreResult.setScore(new BigDecimal(object[0].toString()).doubleValue());
        scoreResult.setSubjectId((Integer)object[1]);
        scoreResult.setTeacherId((Integer)object[2]);
        return scoreResult;
    }

    public static ScoreResult convertTeacherScore(Object[] object){
        ScoreResult scoreResult = new ScoreResult();
        scoreResult.setScore(new BigDecimal(object[0].toString()).doubleValue());
        scoreResult.setSubjectId((Integer)object[1]);
        scoreResult.setSchoolYear((String)object[2]);
        scoreResult.setTeacherId((Integer)object[3]);
        return scoreResult;
    }
}
