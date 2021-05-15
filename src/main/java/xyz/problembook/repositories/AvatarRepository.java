package xyz.problembook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.problembook.entities.AvatarEntity;

@Repository
public interface AvatarRepository extends JpaRepository<AvatarEntity, Integer> {
}
