package com.gamified.learning_platform.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamified.learning_platform.Models.QuizQuestion;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long>{
	List<QuizQuestion> findByQuiz_Id(Long quizId);
}
