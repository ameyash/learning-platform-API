package com.gamified.learning_platform.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamified.learning_platform.Models.Quiz;
import com.gamified.learning_platform.Repositories.QuizRepository;

@Service
public class QuizService {
	@Autowired
	private QuizRepository quizRepository;
	
	public List<Quiz> getAllQuiz(){
		return quizRepository.findAll();
	}
}
