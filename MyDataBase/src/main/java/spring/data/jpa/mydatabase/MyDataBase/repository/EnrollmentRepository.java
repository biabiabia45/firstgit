package spring.data.jpa.mydatabase.MyDataBase.repository;

import spring.data.jpa.mydatabase.MyDataBase.model.Enrollment;

import java.util.List;

public interface EnrollmentRepository extends BaseRepository<Enrollment, Long> {
    List<Enrollment> findByStudentOrderById(String studentName);
    List<Enrollment> findByCourseOrderByCourseId(String courseName);
}
