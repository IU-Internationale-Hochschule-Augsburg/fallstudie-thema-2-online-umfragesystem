package com.surveymaster.repository;

import com.surveymaster.entity.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
    /*@Modifying
    @Transactional
    @Query("DELETE FROM Answer a WHERE a.questionId =: question_id")
    public void deleteByQuestionId(@Param("question_id") Long questionId);*/

    @Transactional
    public void deleteByQuestionId(Long questionId);
}
