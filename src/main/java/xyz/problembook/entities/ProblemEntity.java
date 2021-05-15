package xyz.problembook.entities;

import javax.persistence.*;

/**
 * Class describing the 'problem' table from the database.
 */
@Entity
@Table(name = "problem")
public class ProblemEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "teacherId")
    private Integer teacherId;

    @Column(name = "hint1")
    private String hint1;

    @Column(name = "hint2")
    private String hint2;

    @Column(name = "content")
    private String content;

    public ProblemEntity() {
    }

    public ProblemEntity(Integer teacherId, String hint1, String hint2, String content) {
        this.teacherId = teacherId;
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getHint1() {
        return hint1;
    }

    public void setHint1(String hint1) {
        this.hint1 = hint1;
    }

    public String getHint2() {
        return hint2;
    }

    public void setHint2(String hint2) {
        this.hint2 = hint2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
