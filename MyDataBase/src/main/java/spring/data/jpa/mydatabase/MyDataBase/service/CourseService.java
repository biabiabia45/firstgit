package spring.data.jpa.mydatabase.MyDataBase.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.data.jpa.mydatabase.MyDataBase.model.Course;
import spring.data.jpa.mydatabase.MyDataBase.repository.CourseRepository;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "CourseCache")
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course saveCourse(String courseId, String courseName, String teacher){
        return courseRepository.save(Course.builder().courseId(courseId).courseName(courseName).teacher(teacher).build());
    }

    @Cacheable
    public List<Course> getAllCourse(){
        return courseRepository.findAll(Sort.by("id"));
    }

    public Course getCours(Long id){
        return courseRepository.getOne(id);
    }

    public Course getCourse(String courseName){
        return courseRepository.findByName(courseName);
    }

    public List<Course> getCourseByName(List<String> courseNames){
        return courseRepository.findByCourseNameInOrderById(courseNames);
    }
}
