package xyz.problembook.entities;

import javax.persistence.*;

/**
 * Class describing the table 'student' in the database.
 * The ID is autogenerated.
 * The 'avatar_id' field can take a finite amount of values (depending on how many available avatars are in the server's database).
 */
@Entity
@Table(name = "student")
@SequenceGenerator(name = "studentSeq", initialValue = 1, allocationSize = 100)
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSeq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "hashPassword")
    private String hashPassword;

    @Column(name = "avatar_id")
    private Integer avatarId;

    public StudentEntity() {
    }

    public StudentEntity(String name, String email, String hashPassword, Integer avatarId) {
        this.name = name;
        this.email = email;
        this.hashPassword = hashPassword;
        this.avatarId = avatarId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }
}
