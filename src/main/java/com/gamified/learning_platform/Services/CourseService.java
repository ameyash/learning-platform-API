package com.gamified.learning_platform.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gamified.learning_platform.Models.Course;
import com.gamified.learning_platform.Repositories.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> getAllCourses(){
		return courseRepository.findAll();
	}

	public Course getCourseById(long id) {
		
		return courseRepository.findById(id).orElse(new Course());
	}
}
