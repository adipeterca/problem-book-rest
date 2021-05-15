package xyz.problembook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.problembook.dtos.AvatarDTO;
import xyz.problembook.entities.AvatarEntity;
import xyz.problembook.repositories.AvatarRepository;

import java.util.Optional;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public void add(String url) {
        avatarRepository.save(new AvatarEntity(url));
    }

    public AvatarDTO get(Integer id) {
        Optional<AvatarEntity> avatarEntity = avatarRepository.findById(id);
        if (avatarEntity.isEmpty()) return null;
        return new AvatarDTO(avatarEntity.get().getUrl());
    }
}
