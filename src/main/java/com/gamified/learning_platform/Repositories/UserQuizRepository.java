package com.gamified.learning_platform.Repositories;

import com.gamified.learning_platform.Models.Quiz;
import com.gamified.learning_platform.Models.User;
import com.gamified.learning_platform.Models.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {
	// Fetch UserQuiz by userId and quizId
	Optional<UserQuiz> findByUserAndQuiz(User user, Quiz quiz);
	
	UserQuiz findByUserIdAndQuizId(Long userId, Long quizId);
	
	UserQuiz findByUserEmailAndQuizId(String email, Long quizId);

	// Fetch UserQuiz by userId and quizId
	List<UserQuiz> findByUserId(Long userId);
}
