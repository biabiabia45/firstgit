package spring.data.jpa.mydatabase.MyDataBase.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "student")
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity implements Serializable {
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "student_name")
    private String name;
    @Column(updatable = false)
    private String sex;
}