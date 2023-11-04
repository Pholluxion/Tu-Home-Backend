package com.phollux.tuhome.image.repos;


import com.phollux.tuhome.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
