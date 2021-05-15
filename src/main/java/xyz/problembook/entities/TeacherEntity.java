package xyz.problembook.entities;

import javax.persistence.*;

/**
 * Class describing the 'teacher' table in the database.
 * The email is guaranteed to be a valid one.
 * The 'avatar_id' can take a finite amount of values, depending on how many avatars are in the database.
 */
@Entity
@Table(name = "teacher")
public class TeacherEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "hash_password")
    private String hashPassword;

    @Column(name = "avatar_id")
    private Integer avatarId;

    public TeacherEntity() {
    }

    public TeacherEntity(String name, String email, String hashPassword, Integer avatarId) {
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
