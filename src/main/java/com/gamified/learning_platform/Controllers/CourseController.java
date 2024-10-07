package com.gamified.learning_platform.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamified.learning_platform.Models.Course;
import com.gamified.learning_platform.Services.CourseService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses")
	public List<Course> getAllCourse(){
		return courseService.getAllCourses();
	}
	
	@GetMapping("/course/{courseId}")
	public Course getCourseById(@PathVariable long courseId){
		return courseService.getCourseById(courseId);
	}
}

