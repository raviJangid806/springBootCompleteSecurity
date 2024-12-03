package com.spring.practiceSpring.repository;

import com.spring.practiceSpring.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByName(String username);
}
