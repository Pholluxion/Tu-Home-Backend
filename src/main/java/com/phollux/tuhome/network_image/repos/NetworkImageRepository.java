package com.phollux.tuhome.network_image.repos;

import com.phollux.tuhome.network_image.domain.NetworkImage;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NetworkImageRepository extends JpaRepository<NetworkImage, UUID> {

    Page<NetworkImage> findAllById(UUID id, Pageable pageable);

}
