package com.roboticks.robobob;

import com.roboticks.robobob.model.Question;
import com.roboticks.robobob.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final QuestionRepository questionRepository;

    @Autowired
    public DataLoader(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        questionRepository.save(new Question("What is your name?", "RoboBob"));
        // Add more predefined questions and answers
    }
}
