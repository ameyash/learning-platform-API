package com.gamified.learning_platform.Models;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int acceptance; // Acceptance percentage

    private String difficulty; // Difficulty level

    private String status; // "Pending" or "Completed"

    // One-to-many relationship with QuizQuestion
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<QuizQuestion> questions;
    
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserQuiz> userQuizzes;


	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(int acceptance) {
        this.acceptance = acceptance;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }

    public List<UserQuiz> getUserQuizzes() {
		return userQuizzes;
	}

	public void setUserQuizzes(List<UserQuiz> userQuizzes) {
		this.userQuizzes = userQuizzes;
	}
}
