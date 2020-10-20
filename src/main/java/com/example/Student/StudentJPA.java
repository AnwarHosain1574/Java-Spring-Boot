package com.example.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJPA extends JpaRepository<StudentInfo, Long>{

}
