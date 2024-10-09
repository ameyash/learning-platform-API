package com.gamified.learning_platform.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz_question")
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String questionText;

    private String option1;       // Option 1
    private String option2;       // Option 2
    private String option3;       // Option 3
    private String option4; 


	@Column(name = "correct_answer", nullable = false)
    private String correctAnswer;

    // Many-to-one relationship with Quiz
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    @JsonBackReference
    private Quiz quiz;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
    public String getOption1() {
    	return option1;
    }
    
    public void setOption1(String option1) {
    	this.option1 = option1;
    }
    
    public String getOption2() {
    	return option2;
    }
    
    public void setOption2(String option2) {
    	this.option2 = option2;
    }
    
    public String getOption3() {
    	return option3;
    }
    
    public void setOption3(String option3) {
    	this.option3 = option3;
    }
    
    public String getOption4() {
    	return option4;
    }
    
    public void setOption4(String option4) {
    	this.option4 = option4;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
