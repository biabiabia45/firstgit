package spring.data.jpa.mydatabase.MyDataBase.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "course")
@Builder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity implements Serializable {
    @Column(name = "course_id")
    private String courseId;
    @Column(name = "course_name")
    private String courseName;
    private String teacher;
}

