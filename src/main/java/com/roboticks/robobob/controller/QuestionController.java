package com.roboticks.robobob.controller;

import com.roboticks.robobob.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;

@RestController
@RequestMapping("/robobob")
public class QuestionController {

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    private final QuestionService questionService;

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody String question) throws ScriptException {
        String answer = String.valueOf(questionService.getAnswer(question));
        return ResponseEntity.ok(answer);
    }
}
