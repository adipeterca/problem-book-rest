package xyz.problembook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.problembook.entities.StudentEntity;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    @Modifying
    @Query("update StudentEntity s set s.avatarId = ?2 where s.id = ?1")
    void updateAvatar(Integer studentId, Integer newAvatarId);

    @Query("select s from StudentEntity s where s.name = ?1")
    StudentEntity findFirstByName(String name);

    @Query("select s from StudentEntity s where s.email = ?1")
    StudentEntity findFirstByRegistrationNumber(String email);
}
