package com.roboticks.robobob.service;

import com.roboticks.robobob.helper.IScriptEngineManager;
import com.roboticks.robobob.model.Question;
import com.roboticks.robobob.repository.QuestionRepository;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.util.Optional;

@Service
@Cacheable
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final IScriptEngineManager scriptEngineManager;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, IScriptEngineManager scriptEngineManager) {
        this.questionRepository = questionRepository;
        this.scriptEngineManager = scriptEngineManager;
    }

    public String getAnswer(String question) throws ScriptException {
        Optional<Question> basicQuestion = questionRepository.findByQuestion(question);
        if (basicQuestion.isPresent()) {
            return basicQuestion.get().getAnswer();
        } else {
                String result = scriptEngineManager.getJavaScriptEngine().eval(question).toString();
                return String.valueOf(result);
        }
    }
}
