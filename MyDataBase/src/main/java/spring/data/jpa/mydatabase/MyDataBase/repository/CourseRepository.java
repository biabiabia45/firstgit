package spring.data.jpa.mydatabase.MyDataBase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.data.jpa.mydatabase.MyDataBase.model.Course;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseNameInOrderById(List<String> list);
    Course findByName(String courseName);
}
