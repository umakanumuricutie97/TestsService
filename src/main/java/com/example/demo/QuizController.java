package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
@CrossOrigin(origins = "*")
public class QuizController {
    private final QuestionService questionService;

    @Autowired
    public QuizController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getQuestionTextById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        if (question != null) {
            return ResponseEntity.ok(question.getQuestionText());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/answer")
    public ResponseEntity<String> answerQuestion(@PathVariable Long id, @RequestBody String answer) {
        Question question = questionService.getQuestionById(id);
        if (question != null && answer.equals(question.getCorrectAnswer())) {
            return ResponseEntity.ok("Congratulations! Your answer is correct.");
        } else {
            return ResponseEntity.ok("Sorry, your answer is incorrect. Please try again.");
        }
    }
}
