package com.surveymaster.repository;

import com.surveymaster.entity.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

    @Transactional
    void deleteByQuestionId(Long questionId);
}
