package spring.data.jpa.mydatabase.MyDataBase.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "enrollments")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment extends BaseEntity implements Serializable {
    @JoinColumn(name = "student_name")
    private String studentName;

    @ManyToMany
    @JoinColumn(name = "course_name")
    private List<Course> courseName;
}
