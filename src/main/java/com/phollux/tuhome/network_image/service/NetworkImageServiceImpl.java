package com.phollux.tuhome.network_image.service;

import com.phollux.tuhome.model.SimplePage;
import com.phollux.tuhome.network_image.domain.NetworkImage;
import com.phollux.tuhome.network_image.model.NetworkImageDTO;
import com.phollux.tuhome.network_image.repos.NetworkImageRepository;
import com.phollux.tuhome.property.domain.Property;
import com.phollux.tuhome.property.repos.PropertyRepository;
import com.phollux.tuhome.util.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class NetworkImageServiceImpl implements NetworkImageService {

    private final NetworkImageRepository networkImageRepository;
    private final PropertyRepository propertyRepository;

    public NetworkImageServiceImpl(final NetworkImageRepository networkImageRepository,
                                   final PropertyRepository propertyRepository) {
        this.networkImageRepository = networkImageRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public SimplePage<NetworkImageDTO> findAll(final String filter, final Pageable pageable) {
        Page<NetworkImage> page;
        if (filter != null) {
            UUID uuidFilter = null;
            try {
                uuidFilter = UUID.fromString(filter);
            } catch (final IllegalArgumentException illegalArgumentException) {
                // ignore
            }
            page = networkImageRepository.findAllById(uuidFilter, pageable);
        } else {
            page = networkImageRepository.findAll(pageable);
        }
        return new SimplePage<>(page.getContent()
                .stream()
                .map(networkImage -> mapToDTO(networkImage, new NetworkImageDTO()))
                .toList(),
                page.getTotalElements(), pageable);
    }

    @Override
    public List<NetworkImageDTO> findAllByPropertyId(Integer propertyId) {

        List<NetworkImageDTO> networkImageDTOList = new ArrayList<>();

        if (propertyId != null) {
            List<NetworkImage> networkImageList = networkImageRepository.findAllByPropertyId(propertyId);
            networkImageDTOList = networkImageList.stream()
                .map(networkImage -> mapToDTO(networkImage, new NetworkImageDTO()))
                .toList();
        }

        return networkImageDTOList;
    }


    @Override
    public NetworkImageDTO get(final UUID id) {
        return networkImageRepository.findById(id)
                .map(networkImage -> mapToDTO(networkImage, new NetworkImageDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public UUID create(final NetworkImageDTO networkImageDTO) {
        final NetworkImage networkImage = new NetworkImage();
        mapToEntity(networkImageDTO, networkImage);
        return networkImageRepository.save(networkImage).getId();
    }

    @Override
    public void update(final UUID id, final NetworkImageDTO networkImageDTO) {
        final NetworkImage networkImage = networkImageRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(networkImageDTO, networkImage);
        networkImageRepository.save(networkImage);
    }

    @Override
    public void delete(final UUID id) {
        networkImageRepository.deleteById(id);
    }

    private NetworkImageDTO mapToDTO(final NetworkImage networkImage,
                                     final NetworkImageDTO networkImageDTO) {
        networkImageDTO.setId(networkImage.getId());
        networkImageDTO.setName(networkImage.getName());
        networkImageDTO.setUrl(networkImage.getUrl());
        networkImageDTO.setProperty(networkImage.getProperty() == null ? null : networkImage.getProperty().getId());
        return networkImageDTO;
    }

    private NetworkImage mapToEntity(final NetworkImageDTO networkImageDTO,
                                     final NetworkImage networkImage) {
        networkImage.setName(networkImageDTO.getName());
        networkImage.setUrl(networkImageDTO.getUrl());
        final Property property = networkImageDTO.getProperty() == null ? null : propertyRepository.findById(networkImageDTO.getProperty())
                .orElseThrow(() -> new NotFoundException("property not found"));
        networkImage.setProperty(property);
        return networkImage;
    }

}
