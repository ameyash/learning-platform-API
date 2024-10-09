package com.gamified.learning_platform.Services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamified.learning_platform.Models.Course;
import com.gamified.learning_platform.Models.Quiz;
import com.gamified.learning_platform.Models.QuizQuestion;
import com.gamified.learning_platform.Models.User;
import com.gamified.learning_platform.Models.UserQuiz;
import com.gamified.learning_platform.Repositories.QuizQuestionRepository;
import com.gamified.learning_platform.Repositories.QuizRepository;
import com.gamified.learning_platform.Repositories.UserQuizRepository;
import com.gamified.learning_platform.Repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class QuizService {
	@Autowired
	private QuizRepository quizRepository;
	
	@Autowired
    private QuizQuestionRepository quizQuestionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserQuizRepository userQuizRepository;
	
	
	public List<Quiz> getAllQuiz(){
		return quizRepository.findAll();
	}

	public Quiz getQuizById(long quizId) {
		return quizRepository.findById(quizId).orElse(null);
	}
	
	public List<QuizQuestion> getQuestionsByQuizId(Long quizId) {
		List<QuizQuestion> listQuestion = quizQuestionRepository.findByQuiz_Id(quizId);
		for(QuizQuestion i: listQuestion) {
			System.out.println(i);
		}
        return listQuestion;
    }
 
	// Method to handle the logic when a user takes a quiz
    @Transactional
    public String takeQuiz(Long userId, Long quizId) {
        // Fetch user and quiz entities
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);

        if (userOptional.isPresent() && quizOptional.isPresent()) {
            User user = userOptional.get();
            Quiz quiz = quizOptional.get();

            // Check if there's already a UserQuiz entry for this user and quiz
            Optional<UserQuiz> userQuizOptional = userQuizRepository.findByUserAndQuiz(user, quiz);
            
            UserQuiz userQuiz;
            
            if (userQuizOptional.isPresent()) {
                // If the entry exists, update the status
                userQuiz = userQuizOptional.get();
                if (!userQuiz.getStatus().equals("Completed")) {
                    userQuiz.setStatus("Completed");
                    userQuizRepository.save(userQuiz);
                    return "Quiz status updated to 'Completed' for user: " + user.getUsername();
                } else {
                    return "User has already completed this quiz.";
                }
            } else {
                // If no entry exists, create a new one and mark it as 'Completed'
                userQuiz = new UserQuiz();
                userQuiz.setUser(user);
                userQuiz.setQuiz(quiz);
                userQuiz.setStatus("Completed");
                userQuizRepository.save(userQuiz);
                
                return "Quiz taken by user: " + user.getUsername() + ", status set to 'Completed'.";
            }
        } else {
            return "User or Quiz not found.";
        }
    }

    // Method to check quiz status for a user
    public boolean checkQuizStatus(String email, Long quizId) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);

        if (userOptional.isPresent() && quizOptional.isPresent()) {
            User user = userOptional.get();
            Quiz quiz = quizOptional.get();

            // Check the status in UserQuiz
            Optional<UserQuiz> userQuizOptional = userQuizRepository.findByUserAndQuiz(user, quiz);

            if (userQuizOptional.isPresent()) {
                return true;
            } 
        
        }
        return false;
    }

	public List<UserQuiz> getUserQuizByUserId(Long userId) {
		
		return userQuizRepository.findByUserId(userId);
	}
}
