package com.roboticks.robobob.service;

import com.roboticks.robobob.model.Question;
import com.roboticks.robobob.repository.QuestionRepository;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Cacheable
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Async
    public CompletableFuture<String> getAnswerAsync(String question) throws ScriptException {
        return CompletableFuture.completedFuture(getAnswer(question));
    }
    public String getAnswer(String question) throws ScriptException {
        Optional<Question> basicQuestion = questionRepository.findByQuestion(question);
        if (basicQuestion.isPresent()) {
            return basicQuestion.get().getAnswer();
        } else {
                String result = new ScriptEngineManager().getEngineByName("JavaScript").eval(question).toString();
                return String.valueOf(result);
        }
    }
}
