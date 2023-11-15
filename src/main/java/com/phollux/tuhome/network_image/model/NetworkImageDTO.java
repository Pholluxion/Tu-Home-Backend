package com.phollux.tuhome.network_image.model;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NetworkImageDTO {

    private UUID id;

    private String name;

    @NotNull
    private String url;

    @NotNull
    private Integer property;

}
