package xyz.problembook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import xyz.problembook.entities.ProblemEntity;

@Repository
public interface ProblemRepository extends JpaRepository<ProblemEntity, Integer> {

    @Query("select p from ProblemEntity p where p.teacherId = ?1 and p.hint1 = ?2 and p.hint2 = ?3 and p.content = ?4")
    ProblemEntity getIdOf(Integer teacherId, String hint1, String hint2, String content);
}
