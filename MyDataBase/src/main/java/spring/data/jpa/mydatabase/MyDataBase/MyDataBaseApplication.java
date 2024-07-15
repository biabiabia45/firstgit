package spring.data.jpa.mydatabase.MyDataBase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import spring.data.jpa.mydatabase.MyDataBase.model.Course;
import spring.data.jpa.mydatabase.MyDataBase.model.Enrollment;
import spring.data.jpa.mydatabase.MyDataBase.model.Student;
import spring.data.jpa.mydatabase.MyDataBase.repository.CourseRepository;
import spring.data.jpa.mydatabase.MyDataBase.repository.EnrollmentRepository;
import spring.data.jpa.mydatabase.MyDataBase.repository.StudentRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class MyDataBaseApplication implements ApplicationRunner {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyDataBaseApplication.class, args);
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        initOrders();
        findOrders();
    }

    private void initOrders() {
        Student yangyi = Student.builder().studentId("20030122").name("杨义").sex("男").build();
        studentRepository.save(yangyi);
        log.info("Student:{}", yangyi);

        Student wangqi = Student.builder().studentId("18050305").name("王琦").sex("女").build();
        studentRepository.save(wangqi);
        log.info("Student:{}", wangqi);

        Course math = Course.builder().courseId("100").courseName("数学").teacher("张三").build();
        courseRepository.save(math);
        log.info("Course:{}", math);

        Course english = Course.builder().courseId("200").courseName("英语").teacher("李四").build();
        courseRepository.save(english);
        log.info("Course:{}", english);

        Enrollment enroll = Enrollment.builder().studentName(yangyi.getName()).courseName(Collections.singletonList(math)).build();
        enrollmentRepository.save(enroll);
        log.info("Enroll:{}", enroll);

        enroll = Enrollment.builder().studentName(wangqi.getName()).courseName(Arrays.asList(english, math)).build();
        enrollmentRepository.save(enroll);
        log.info("Enroll:{}", enroll);
    }

    public void findOrders() {
        studentRepository
                .findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(c -> log.info("Loading {}", c));

        List<Enrollment> list = enrollmentRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info("findTop3ByOrderByUpdateTimeDescIdAsc: {}", getJoinedOrderId(list));

        list = enrollmentRepository.findByStudentOrderById("杨义");
        log.info("findByStudentOrderById: {}", getJoinedOrderId(list));

        list = enrollmentRepository.findByCourseOrderByCourseId("math");
        log.info("findCourseOrderByCourseId: {}", list);
    }

    private String getJoinedOrderId(List<Enrollment> list) {
        return list.stream().map(o -> o.getId().toString())
                .collect(Collectors.joining(","));
    }
}
