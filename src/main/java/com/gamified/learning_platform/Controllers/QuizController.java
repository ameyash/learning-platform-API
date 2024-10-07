package com.gamified.learning_platform.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamified.learning_platform.Models.Quiz;
import com.gamified.learning_platform.Services.QuizService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@GetMapping("/quizzes")
	public List<Quiz> getAllQuiz(){
		return quizService.getAllQuiz();
	}
}
