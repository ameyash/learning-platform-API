package com.gamified.learning_platform.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamified.learning_platform.Models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
