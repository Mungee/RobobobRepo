package com.roboticks.robobob.repository;

import com.roboticks.robobob.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByQuestion(String question);
}
