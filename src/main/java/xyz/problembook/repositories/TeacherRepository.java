package xyz.problembook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.problembook.entities.TeacherEntity;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {
    @Modifying
    @Query("update TeacherEntity t set t.avatarId = ?2 where t.id = ?1")
    void updateAvatar(Integer teacherId, Integer newAvatarId);

    @Query("select t from TeacherEntity t where t.name = ?1")
    TeacherEntity findFirstByName(String name);
}
