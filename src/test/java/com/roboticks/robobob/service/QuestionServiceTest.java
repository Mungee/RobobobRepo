package com.roboticks.robobob.service;

import com.roboticks.robobob.helper.IScriptEngineManager;
import com.roboticks.robobob.model.Question;
import com.roboticks.robobob.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private IScriptEngineManager scriptEngineManager;

    @Mock
    private ScriptEngine scriptEngine;

    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(scriptEngineManager.getJavaScriptEngine()).thenReturn(scriptEngine);
    }

    @Test
    public void testGetAnswer_withExistingQuestion() throws ScriptException {
        Question question = new Question();
        question.setQuestion("What is Java?");
        question.setAnswer("Java is a programming language.");
        when(questionRepository.findByQuestion(anyString())).thenReturn(Optional.of(question));

        String result = questionService.getAnswer("What is Java?");
        assertEquals("Java is a programming language.", result);
    }

    @Test
    public void testGetAnswer_withNonExistingQuestion() throws ScriptException {
        when(questionRepository.findByQuestion(anyString())).thenReturn(Optional.empty());
        when(scriptEngine.eval(anyString())).thenReturn("42");

        String result = questionService.getAnswer("6 * 7");
        assertEquals("42", result);
    }

    @Test
    public void testGetAnswer_withScriptException() throws ScriptException {
        when(questionRepository.findByQuestion(anyString())).thenReturn(Optional.empty());
        when(scriptEngine.eval(anyString())).thenThrow(new ScriptException("Error"));

        assertThrows(ScriptException.class, ()->questionService.getAnswer("invalid script"));
    }
}
