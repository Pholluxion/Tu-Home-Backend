package com.phollux.tuhome.network_image.service;

import com.phollux.tuhome.model.SimplePage;
import com.phollux.tuhome.network_image.model.NetworkImageDTO;
import java.util.UUID;
import org.springframework.data.domain.Pageable;


public interface NetworkImageService {

    SimplePage<NetworkImageDTO> findAll(String filter, Pageable pageable);

    NetworkImageDTO get(UUID id);

    UUID create(NetworkImageDTO networkImageDTO);

    void update(UUID id, NetworkImageDTO networkImageDTO);

    void delete(UUID id);

}
