package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class QuestionServiceTest {

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Mock repository behavior
        List<Question> mockQuestions = Arrays.asList(
                new Question(1L, "What is the national animal of India?", "Tiger")
        );
        when(questionRepository.findAll()).thenReturn(mockQuestions);
    }

    @Test
    public void testGetAllQuestions() {
        // Call the service method
        List<Question> retrievedQuestions = questionService.getAllQuestions();

        // Assertions
        assertEquals(1, retrievedQuestions.size());
        assertEquals("What is the national animal of India?", retrievedQuestions.get(0).getQuestionText());
        assertEquals("Tiger", retrievedQuestions.get(0).getCorrectAnswer());
    }
}
