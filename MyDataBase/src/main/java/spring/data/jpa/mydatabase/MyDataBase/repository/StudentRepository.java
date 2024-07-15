package spring.data.jpa.mydatabase.MyDataBase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.data.jpa.mydatabase.MyDataBase.model.Student;

import javax.swing.*;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameOrderByStudentId(List<String> list);
    Student findByName(String studentName);
}
